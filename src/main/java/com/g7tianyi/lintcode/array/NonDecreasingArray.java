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
 * @link https://www.lintcode.com/problem/non-decreasing-array/description
 */
public class NonDecreasingArray {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public boolean checkPossibility(int[] elems) {

      if (elems == null || elems.length == 0) {
        return false;
      }

      int illegals = 0;
      for (int i = 1; i < elems.length; ++i) {

        if (elems[i] >= elems[i - 1]) {
          continue;
        }

        ++illegals;
        if (illegals == 2) { // Two illegal number occurs, return false directly..
          return false;
        }

        // It's OK when the illegal number occurs in the beginning or end of the array
        // 3 1 4 6 9 ...
        //   |
        //   i => change 4 to 3 would suffice
        // ... 7 9 3
        //         |
        //         i => change 3 to 10 would suffice
        if (i == 1 || i == elems.length - 1) {
          continue;
        }

        // illegal case:
        // ... 3 6 1 2 7 ...
        //         i
        //
        // legal case
        // ... 3 5 4 7 ...
        //       i
        if (elems[i - 1] > elems[i + 1]) {
          return false;
        }
      }

      return true;
    }
  }

  @AllArgsConstructor
  public static class Case {

    private int[] elems;

    private boolean result;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> runner =
        aCase -> {
          log.info(Strings.format(aCase.elems));
          boolean result = s.checkPossibility(aCase.elems);
          log.info(result);
          log.info();
          Assert.assertEquals(aCase.result, result);
        };

    runner.accept(new Case(new int[] {4}, true));
    runner.accept(new Case(new int[] {4, 2, 3}, true));
    runner.accept(new Case(new int[] {4, 2, 1}, false));
    runner.accept(new Case(new int[] {3, 6, 1, 2}, false));
    runner.accept(new Case(new int[] {7, 6, 5, 8}, false));
    runner.accept(new Case(new int[] {2, 3, 6, 1}, true));
    runner.accept(new Case(new int[] {2, 3, 6, 1, 10, 13, 16, 14}, false));
  }
}
