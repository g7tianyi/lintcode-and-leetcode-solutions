package com.g7tianyi.lintcode.permutation;

import com.g7tianyi.common.Strings;
import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Oct 30, 2019
 *
 * @link https://www.lintcode.com/problem/permutation-index/description
 */
public class PermutationIndex {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    private List<Long> factorials = new ArrayList<>();

    // 没有重复
    public long permutationIndex(int[] values) {

      if (values == null || values.length == 0) {
        return 0;
      }

      int len = values.length;
      factorials.add(0L);
      for (long i = 1, f = 1; i <= len; ++i) {
        f *= i;
        factorials.add(f);
      }

      return getPermutationIndex(values);
    }

    private long getPermutationIndex(int[] values) {
      int len = values.length;
      if (len == 1) {
        return 1;
      }

      int[] sorted = new int[len];
      System.arraycopy(values, 0, sorted, 0, len);
      Arrays.sort(sorted);

      Map<Integer, Integer> ranks = new HashMap<>();
      for (int i = 0; i < len; ++i) {
        ranks.put(sorted[i], i);
      }

      for (int i = 0; i < len; ++i) {
        if (values[i] == sorted[i]) {
          continue;
        }
        long result = (ranks.get(values[i]) - i) * factorials.get(len - i - 1);
        return result + getPermutationIndex(Arrays.copyOfRange(values, i + 1, values.length));
      }

      return 1;
    }
  }

  private static class Verifier {

    private static int[] original;
    private static int[] sorted;
    private static int[] current;
    private static int index = 0;

    public static long verify(int[] values) {
      original = values;

      int len = values.length;
      current = new int[len];
      sorted = new int[len];
      System.arraycopy(values, 0, sorted, 0, len);
      Arrays.sort(sorted);

      index = 0;

      return run();
    }

    private static long run() {
      boolean[] accessed = new boolean[original.length];
      run(accessed, 0);
      return index;
    }

    private static boolean run(boolean[] accessed, int pos) {
      if (pos == original.length) {
        ++index;
        for (int i = 0; i < current.length; ++i) {
          if (current[i] != original[i]) {
            return false;
          }
        }
        return true;
      }

      for (int i = 0; i < original.length; ++i) {
        if (!accessed[i]) {
          accessed[i] = true;
          current[pos] = sorted[i];
          if (run(accessed, pos + 1)) {
            return true;
          }
          accessed[i] = false;
        }
      }
      return false;
    }
  }

  private final Solution s = new Solution();

  private final Consumer<int[]> c =
      values -> {
        log.info(values);
        log.info("%d VS %s", s.permutationIndex(values), Verifier.verify(values));
        log.info();
      };

  @Test
  public void test() {
    c.accept(new int[] {1, 2, 6, 4, 5, 3});
    c.accept(new int[] {1, 2, 3, 4, 5});
    c.accept(new int[] {4, 3, 2, 1});
    c.accept(new int[] {4, 5, 2, 1, 3});
  }
}
