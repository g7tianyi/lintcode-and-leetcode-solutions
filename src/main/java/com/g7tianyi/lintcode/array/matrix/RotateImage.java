package com.g7tianyi.lintcode.array.matrix;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Oct 31, 2019
 *
 * @link https://www.lintcode.com/problem/rotate-image/description
 */
public class RotateImage {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    class Coordinate {
      int x, y;

      public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
      }
    }

    private final int[][] walks = {
      {0, 1}, // 往左
      {1, 0}, // 往下
      {0, -1}, // 往右
      {-1, 0}, // 往上
    };

    public void rotate(int[][] matrix) {

      if (matrix == null || matrix.length == 0 || matrix.length != matrix[0].length) {
        return;
      }

      int N = matrix.length;

      for (int K = 0; K < (N >> 1); ++K) {
        int len = N - K - 1;
        Coordinate[] coordinates = new Coordinate[4];
        coordinates[0] = new Coordinate(K, K);
        coordinates[1] = new Coordinate(K, len);
        coordinates[2] = new Coordinate(len, len);
        coordinates[3] = new Coordinate(len, K);

        for (int i = 0; i < N - K * 2 - 1; ++i) {
          int prev = matrix[coordinates[0].x][coordinates[0].y], curr;
          int j = 1, count = 0;
          while (true) {
            curr = matrix[coordinates[j].x][coordinates[j].y];
            matrix[coordinates[j].x][coordinates[j].y] = prev;
            prev = curr;

            j = (j + 1) % 4;
            if (++count == 4) {
              break;
            }
          }

          for (j = 0; j < 4; ++j) {
            coordinates[j].x += walks[j][0];
            coordinates[j].y += walks[j][1];
          }
        }
      }
    }
  }

  private final Solution s = new Solution();

  private final Consumer<int[][]> p =
      matrix -> {
        for (int[] row : matrix) {
          log.info(row);
        }
        log.info();
      };

  private final Consumer<int[][]> c =
      matrix -> {
        p.accept(matrix);
        s.rotate(matrix);
        p.accept(matrix);
        log.info();
      };

  @Test
  public void test() {
    c.accept(
        new int[][] {
          {1, 2},
          {3, 4},
        });

    c.accept(
        new int[][] {
          {1, 2, 3},
          {4, 5, 6},
          {7, 8, 9},
        });

    c.accept(
        new int[][] {
          {10, 11, 12, 13},
          {14, 15, 16, 17},
          {18, 19, 20, 21},
          {22, 23, 24, 25},
        });
    c.accept(
        new int[][] {
          {10, 11, 12, 13, 14},
          {15, 16, 17, 18, 19},
          {20, 21, 22, 23, 24},
          {25, 26, 27, 28, 29},
          {30, 31, 32, 33, 34},
        });
  }
}
