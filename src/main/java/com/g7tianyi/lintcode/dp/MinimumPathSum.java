package com.g7tianyi.lintcode.dp;

import com.g7tianyi.lintcode.util.Log;
import org.junit.Test;

/**
 * Created by g7tianyi on Aug 24, 2019
 *
 * <p>Problem link: https://www.lintcode.com/problem/minimum-path-sum/description
 */
public class MinimumPathSum {

  private static final Log log = new Log();

  public class Solution {

    // 最简单的DP问题
    // F(n, m) = Min(F(n-1, m), F(n, m-1)) + G(n, m)
    public int minPathSum(int[][] grid) {
      int h = grid.length, w = grid[0].length;
      for (int i = 0; i < h; i++) {
        for (int j = 0; j < w; j++) {
          if (i == 0 && j == 0) {
            continue;
          }

          int prevSum;
          if (i == 0) {
            prevSum = grid[i][j - 1];
          } else if (j == 0) {
            prevSum = grid[i - 1][j];
          } else {
            prevSum = Math.min(grid[i][j - 1], grid[i - 1][j]);
          }

          grid[i][j] += prevSum;
        }
      }
      return grid[h - 1][w - 1];
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    int[][] grid = new int[3][3];
    grid[0] = new int[] {1, 3, 1};
    grid[1] = new int[] {1, 5, 1};
    grid[2] = new int[] {4, 2, 1};

    log.info(s.minPathSum(grid));
  }
}
