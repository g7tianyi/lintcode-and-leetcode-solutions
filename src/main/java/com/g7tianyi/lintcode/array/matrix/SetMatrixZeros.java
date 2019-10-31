package com.g7tianyi.lintcode.array.matrix;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Oct 31, 2019
 *
 * @link https://www.lintcode.com/problem/set-matrix-zeroes/description
 */
public class SetMatrixZeros {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    // 如果使用O(M+N)的额外空间，解法就是记录一下哪些行和列需要被置为0
    // 下面的解法是找一个在matrix中没有出现过的数字，代替0，这个解法不具备通用性实在地说
    public void setZeroes(int[][] matrix) {

      if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
        return;
      }

      int sentry = Integer.MIN_VALUE;
      while (true) {
        boolean noChange = true;
        for (int[] row : matrix) {
          for (int elem : row) {
            if (elem == sentry) {
              noChange = false;
              if (++sentry == 0) {
                ++sentry;
              }
            }
          }
        }
        if (noChange) {
          break;
        }
      }

      for (int i = 0; i < matrix.length; ++i) {
        for (int j = 0; j < matrix[i].length; ++j) {
          if (matrix[i][j] == 0) {
            setValues(matrix, i, j, sentry);
          }
        }
      }

      for (int i = 0; i < matrix.length; ++i) {
        for (int j = 0; j < matrix[i].length; ++j) {
          if (matrix[i][j] == sentry) {
            matrix[i][j] = 0;
          }
        }
      }
    }

    private void setValues(int[][] matrix, int i, int j, int value) {

      matrix[i][j] = value;

      int k;

      k = i - 1;
      while (k >= 0 && matrix[k][j] != 0) { // 往上
        if (matrix[k][j] != 0) {
          matrix[k][j] = value;
        }
        --k;
      }

      k = i + 1;
      while (k < matrix.length) { // 往下
        if (matrix[k][j] != 0) {
          matrix[k][j] = value;
        }
        ++k;
      }

      k = j - 1;
      while (k >= 0 && matrix[i][k] != 0) { // 往左
        if (matrix[i][k] != 0) {
          matrix[i][k] = value;
        }
        --k;
      }

      k = j + 1;
      while (k < matrix[i].length) { // 往左
        if (matrix[i][k] != 0) {
          matrix[i][k] = value;
        }
        ++k;
      }
    }
  }

  private final Solution s = new Solution();

  private final Consumer<int[][]> printer =
      matrix -> {
        for (int[] row : matrix) {
          log.info(row);
        }
        log.info();
      };

  private final Consumer<int[][]> c =
      matrix -> {
        printer.accept(matrix);
        s.setZeroes(matrix);
        printer.accept(matrix);
        log.info();
      };

  @Test
  public void test() {
    c.accept(
        new int[][] {
          {1, 2},
          {0, 3},
        });
    c.accept(
        new int[][] {
          {1, 2, 3},
          {4, 0, 6},
          {7, 8, 9},
        });
    c.accept(
        new int[][] {
          {-4, -2147483648, 6, -7, 0},
          {-8, 6, -8, -6, 0},
          {2147483647, 2, -9, -6, -10},
        });
  }
}
