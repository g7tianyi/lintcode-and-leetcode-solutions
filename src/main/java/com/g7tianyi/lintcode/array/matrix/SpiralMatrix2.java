package com.g7tianyi.lintcode.array.matrix;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Nov 13, 2019
 *
 * @link https://www.lintcode.com/problem/spiral-matrix-ii/description
 */
public class SpiralMatrix2 {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    private final int[][] move = {
      {0, 1}, // 左
      {1, 0}, // 下
      {0, -1}, // 右
      {-1, 0}, // 上
    };

    public int[][] generateMatrix(int n) {

      int[][] result = new int[n][n];
      for (int i = 0; i < n; ++i) {
        result[i] = new int[n];
      }

      int i = 0, j = 0, k = 0;
      for (int num = 1; num <= n * n; ++num) {
        result[i][j] = num;
        if (i + move[k][0] >= n
            || i + move[k][0] < 0
            || j + move[k][1] >= n
            || j + move[k][1] < 0
            || result[i + move[k][0]][j + move[k][1]] > 0) {
          k = (k + 1) % 4;
        }
        i += move[k][0];
        j += move[k][1];
      }

      return result;
    }

    private boolean outside(int i, int n) {
      return i < 0 || i >= n;
    }
  }

  private final Solution s = new Solution();

  private final Consumer<Integer> c =
      n -> {
        int[][] matrix = s.generateMatrix(n);
        for (int[] row : matrix) {
          log.info(row);
        }
        log.info();
      };

  @Test
  public void test() {
    c.accept(1);
    c.accept(2);
    c.accept(3);
    c.accept(4);
  }
}
