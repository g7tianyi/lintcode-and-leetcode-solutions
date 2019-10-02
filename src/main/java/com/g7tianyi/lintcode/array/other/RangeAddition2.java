package com.g7tianyi.lintcode.array.other;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 31, 2019
 *
 * @link https://www.lintcode.com/problem/range-addition-ii/description
 */
public class RangeAddition2 {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    // Simulate the operation in your head~
    public int maxCount(int m, int n, int[][] operations) {
      for (int[] operation : operations) {
        m = Math.min(m, operation[0]);
        n = Math.min(n, operation[1]);
      }
      return m * n;
    }
  }

  @AllArgsConstructor
  public static class Case {

    private int m;
    private int n;
    private int[][] operations;
    private int result;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> runner =
        aCase -> {
          int result = s.maxCount(aCase.m, aCase.n, aCase.operations);
          log.info(result);
          log.info();
          Assert.assertEquals(result, aCase.result);
        };

    runner.accept(
        new Case(
            3,
            3,
            new int[][] {
              {2, 2},
              {3, 3},
            },
            4));
  }
}
