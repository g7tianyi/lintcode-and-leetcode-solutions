package com.g7tianyi.lintcode.array;

import com.g7tianyi.common.Strings;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 31, 2019
 *
 * @link https://www.lintcode.com/problem/longest-harmonious-subsequence/description
 */
public class LongestHarmoniousSubSequence {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int findLHS(int[] elems) {

      int res = 0;
      if (elems == null || elems.length < 2) {
        return res;
      }

      int len = elems.length;
      for (int i = 0, lower, upper, max; i < len; ++i) {
        lower = 0;
        upper = 0;
        for (int j = i + 1; j < len; ++j) {
          if (elems[j] == elems[i]) {
            ++lower;
            ++upper;
          }
          if (elems[j] == elems[i] - 1) {
            ++upper;
          }
          if (elems[j] == elems[i] + 1) {
            ++lower;
          }
        }
        max = Math.max(lower, upper);
        if (max > 0) {
          res = Math.max(res, max + 1);
        }
      }

      return res;
    }
  }

  // WA: The sub-sequence doesn't have to be consecutive, FK..
  public class WASolution {

    public int findLHS(int[] elems) {
      if (elems == null || elems.length < 2) {
        return 0;
      }

      int len = elems.length;

      // F(i, L), the maximum value in the sub-array which starting from index i and is of length L
      // G(i, L), the minimum value in the sub-array which starting from index i and is of length L
      // F(i, L) = MAX( F(i, L-1), elems(i+L) )
      int F[] = new int[len], G[] = new int[len];
      System.arraycopy(elems, 0, F, 0, len);
      System.arraycopy(elems, 0, G, 0, len);

      int result = 0;
      for (int L = 2; L <= len; ++L) {
        log.info("L: %d", L);
        log.info("F: %s", Strings.format(F));
        log.info("G: %s", Strings.format(G));
        for (int i = 0; i + L <= len; ++i) {
          if (F[i] < elems[i + L - 1]) {
            F[i] = elems[i + L - 1];
          }
          if (G[i] > elems[i + L - 1]) {
            G[i] = elems[i + L - 1];
          }
          if (F[i] - G[i] == 1) {
            result = L;
          }
        }
      }

      return result;
    }
  }

  @AllArgsConstructor
  public static class Case {

    private int[] elems;

    private int result;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> runner =
        aCase -> {
          log.info(Strings.format(aCase.elems));
          int result = s.findLHS(aCase.elems);
          log.info(result);
          log.info();
          Assert.assertEquals(aCase.result, result);
        };

    runner.accept(new Case(new int[] {1}, 0));
    runner.accept(new Case(new int[] {1, 3}, 0));
    runner.accept(new Case(new int[] {1, 3, 2}, 2));
    runner.accept(new Case(new int[] {1, 3, 3}, 2));
    runner.accept(new Case(new int[] {1, 3, 2, 2, 5, 2, 3, 7}, 5));
  }
}
