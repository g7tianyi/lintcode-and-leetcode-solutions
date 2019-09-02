package com.g7tianyi.lintcode.array.matrix;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 01, 2019
 *
 * @link https://www.lintcode.com/problem/toeplitz-matrix/description
 */
public class ToeplitzMatrix {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public boolean isToeplitzMatrix(int[][] matrix) {
      // Write your code here
      // 1 2 3 4
      // 5 1 2 3
      // 9 5 1 2
      for (int i = 1; i < matrix.length; ++i) {
        for (int j = 1; j < matrix[i].length; ++j) {
          if (matrix[i][j] != matrix[i - 1][j - 1]) {
            return false;
          }
        }
      }
      return true;
    }
  }

  @AllArgsConstructor
  private class Case {
    int[][] matrix;
    boolean result;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c = aCase -> Assert.assertEquals(aCase.result, s.isToeplitzMatrix(aCase.matrix));

    c.accept(
        new Case(
            new int[][] {
              {1, 2, 3, 4},
              {5, 1, 2, 3},
              {9, 5, 1, 2},
            },
            true));
    c.accept(
        new Case(
            new int[][] {
              {1, 2},
              {2, 2},
            },
            false));
  }
}
