package com.g7tianyi.lintcode.array;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.common.Strings;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Oct 01, 2019
 *
 * @link https://www.lintcode.com/problem/shortest-subarray-with-sum-at-least-k/description
 */
public class ShortestSubarrayWithSumAtLeastK {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int shortestSubarray(int[] values, int K) {

      long[] opts = new long[values.length];
      for (int i = 0; i < values.length; ++i) {
        if (values[i] >= K) {
          return 1;
        }
        if (i > 0) {
          opts[i] = values[i] + opts[i - 1];
        } else {
          opts[i] = values[i];
        }
      }

      for (int l = 2; l <= opts.length; ++l) {
        long prev = 0;
        for (int i = 0, j; i + l <= opts.length; ++i) {
          j = i + l - 1;
          if (opts[j] - prev >= K) {
            return l;
          }
          prev = opts[i];
        }
      }
      return -1;
    }
  }

  private final Solution s = new Solution();

  @AllArgsConstructor
  private class Case {
    int[] values;
    int K;
  }

  private final Consumer<Case> c =
      aCase -> {
        log.info("%s | %d", Strings.format(aCase.values), aCase.K);
        log.info(s.shortestSubarray(aCase.values, aCase.K));
      };

  @Test
  public void test() {
    c.accept(new Case(Arrays.from(1), 1));
    c.accept(new Case(Arrays.from(1, 2), 4));
    c.accept(new Case(Arrays.from(1, 2, 5), 4));
    c.accept(new Case(Arrays.from(1, 2, 5), 6));
  }
}
