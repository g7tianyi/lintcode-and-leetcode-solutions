package com.g7tianyi.lintcode.array.matrix;

import com.g7tianyi.common.Strings;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 02, 2019
 *
 * @link https://www.lintcode.com/problem/reshape-the-matrix/description
 */
public class ReshapeTheMatrix {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int[][] matrixReshape(int[][] nums, int r, int c) {
      int m = nums.length, n = nums[0].length;
      if (m * n != r * c) {
        return nums;
      }

      int[][] result = new int[r][c];
      for (int i = 0; i < r; ++i) {
        result[i] = new int[c];
        for (int j = 0; j < c; ++j) {
          int k = i * c + j;
          int oi = k / n, oj = k % n;
          result[i][j] = nums[oi][oj];
        }
      }

      return result;
    }
  }

  @AllArgsConstructor
  private class Case {
    int[][] nums;
    int r;
    int c;
  }

  private void logMatrix(int[][] nums) {
    for (int[] row : nums) {
      log.info(Strings.format(row));
    }
    log.info("----------");
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c =
        aCase -> {
          log.info("----------");
          logMatrix(aCase.nums);
          logMatrix(s.matrixReshape(aCase.nums, aCase.r, aCase.c));
          log.info();
        };

    c.accept(
        new Case(
            new int[][] {
              {1, 2},
              {3, 4},
            },
            1,
            4));

    c.accept(
        new Case(
            new int[][] {
              {1, 2},
              {3, 4},
            },
            4,
            1));

    c.accept(
        new Case(
            new int[][] {
              {1, 2, 3},
              {4, 5, 6},
              {7, 8, 9},
              {10, 11, 12},
            },
            2,
            6));

    c.accept(
        new Case(
            new int[][] {
              {1, 2, 3},
            },
            3,
            1));

    c.accept(
        new Case(
            new int[][] {
              {1, 2, 3, 4},
            },
            2,
            2));

    c.accept(
        new Case(
            new int[][] {
              {1}, {2}, {3}, {4},
            },
            2,
            2));
  }
}
