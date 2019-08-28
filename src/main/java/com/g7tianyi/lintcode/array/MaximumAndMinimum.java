package com.g7tianyi.lintcode.array;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 28, 2019
 *
 * @link https://www.lintcode.com/problem/maximum-and-minimum/description
 */
public class MaximumAndMinimum {

  private static final Logger log = new Logger();

  public class Solution {

    public int[] maxAndMin(int[][] values) {

      if (values == null || values.length == 0 || values[0].length == 0) {
        return new int[] {};
      }

      int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
      for (int[] row : values) {
        for (int elem : row) {
          if (max < elem) {
            max = elem;
          }
          if (min > elem) {
            min = elem;
          }
        }
      }

      return new int[] {max, min};
    }
  }

  @AllArgsConstructor
  public static class Case {

    private int[][] values;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> runner =
        aCase -> {
          log.info(s.maxAndMin(aCase.values));
          log.info();
        };

    runner.accept(
        new Case(
            new int[][] {
              {1, 2, 3},
              {4, 3, 2},
              {6, 4, 4}
            }));
  }
}
