package com.g7tianyi.lintcode.dp;

import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Aug 25, 2019
 *
 * @link https://www.lintcode.com/problem/unique-paths-ii/description
 */
public class UniquePaths2 {

  private static final Logger log = new Logger();

  public class Solution {

    // F(n, m) = F(n-1, m) + F(m, m-1)
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

      int m = obstacleGrid.length;
      int n = obstacleGrid[0].length;

      int[][] values = new int[m][n];
      for (int i = 0; i < m; i++) {
        values[i] = new int[n];
        for (int j = 0; j < n; j++) {
          values[i][j] = 0;
        }
      }

      values[0][0] = obstacleGrid[0][0] == 0 ? 1 : 0;
      for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
          if (i == 0 && j == 0) {
            continue;
          }

          if (obstacleGrid[i][j] == 1) {
            values[i][j] = 0;
          } else if (i == 0) {
            values[i][j] = values[i][j - 1];
          } else if (j == 0) {
            values[i][j] = values[i - 1][j];
          } else {
            values[i][j] = values[i][j - 1] + values[i - 1][j];
          }
        }
      }

      return values[m - 1][n - 1];
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    int[][] obstacleGrid = new int[3][3];
    obstacleGrid[0] = new int[] {0, 0, 0};
    obstacleGrid[1] = new int[] {0, 1, 0};
    obstacleGrid[2] = new int[] {0, 0, 0};
    log.info(s.uniquePathsWithObstacles(obstacleGrid));

    obstacleGrid = new int[1][1];
    obstacleGrid[0] = new int[] {0};
    log.info(s.uniquePathsWithObstacles(obstacleGrid));

    obstacleGrid = new int[5][2];
    obstacleGrid[0] = new int[] {0, 0};
    obstacleGrid[1] = new int[] {0, 0};
    obstacleGrid[2] = new int[] {0, 0};
    obstacleGrid[3] = new int[] {1, 0};
    obstacleGrid[4] = new int[] {0, 0};
    log.info(s.uniquePathsWithObstacles(obstacleGrid));
  }
}
