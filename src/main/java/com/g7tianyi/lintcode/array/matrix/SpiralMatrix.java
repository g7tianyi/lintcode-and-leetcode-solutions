package com.g7tianyi.lintcode.array.matrix;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Oct 15, 2019
 *
 * @link https://www.lintcode.com/problem/spiral-matrix/description
 */
public class SpiralMatrix {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    private final int[][] wheel = {
      {0, 1}, // 向右走，j+1
      {1, 0}, // 向下走，i+1
      {0, -1}, // 向左走，j-1
      {-1, 0}, // 向上走，i-1
    };

    public List<Integer> spiralOrder(int[][] matrix) {

      List<Integer> result = new ArrayList<>();
      if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
        return result;
      }

      int dir = 0;
      int i = 0, j = 0;
      int m = matrix.length, n = matrix[0].length;
      boolean[][] visit = new boolean[m][n];
      while (i >= 0 && i < m && j >= 0 && j < n && !visit[i][j]) {
        visit[i][j] = true;
        result.add(matrix[i][j]);
        if (i + wheel[dir][0] == m
            || i + wheel[dir][0] < 0
            || j + wheel[dir][1] == n
            || j + wheel[dir][1] < 0
            || visit[i + wheel[dir][0]][j + wheel[dir][1]]) {
          dir = (dir + 1) % 4;
        }
        i += wheel[dir][0];
        j += wheel[dir][1];
      }
      return result;
    }
  }

  private final Solution s = new Solution();

  private final Consumer<int[][]> c = matrix -> log.info(s.spiralOrder(matrix));

  @Test
  public void test() {
    c.accept(
        new int[][] {
          {1, 2, 3},
          {4, 5, 6},
          {7, 8, 9},
        });

    c.accept(
        new int[][] {
          {1, 2, 3, 4, 5},
          {6, 7, 8, 9, 10},
          {11, 12, 13, 14, 15},
        });

    c.accept(
        new int[][] {
          {1, 2, 3},
        });

    c.accept(
        new int[][] {
          {1}, {2}, {3},
        });

    c.accept(
        new int[][] {
          {1},
        });
  }
}
