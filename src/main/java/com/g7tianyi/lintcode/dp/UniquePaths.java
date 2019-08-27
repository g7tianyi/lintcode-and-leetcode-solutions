package com.g7tianyi.lintcode.dp;

import com.g7tianyi.lintcode.util.Log;
import org.junit.Test;

/**
 * Created by g7tianyi on Aug 25, 2019
 *
 * @link https://www.lintcode.com/problem/unique-paths/description
 */
public class UniquePaths {

  private static final Log log = new Log();

  public class Solution {

    // F(n, m) = F(n-1, m) + F(m, m-1)
    public int uniquePaths(int m, int n) {
      int[][] values = new int[m][n];
      for (int i = 0; i < m; i++) {
        values[i] = new int[n];
        for (int j = 0; j < n; j++) {
          values[i][j] = 0;
        }
      }

      values[0][0] = 1;
      for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
          if (i == 0 || j == 0) {
            values[i][j] = 1;
          } else {
            values[i][j] = values[i - 1][j] + values[i][j - 1];
          }
        }
      }

      return values[m - 1][n - 1];
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    log.info(s.uniquePaths(3, 1));
    log.info(s.uniquePaths(3, 3));
  }
}
