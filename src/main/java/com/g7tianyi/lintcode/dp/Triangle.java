package com.g7tianyi.lintcode.dp;

import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Sep 20, 2019
 *
 * @link https://www.lintcode.com/problem/triangle/description
 */
public class Triangle {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int minimumTotal(int[][] triangle) {

      int size = triangle.length;
      int[][] F = new int[2][size];
      for (int i = 0; i < 2; ++i) {
        F[i] = new int[size];
        for (int j = 0; j < size; ++j) {
          F[i][j] = Integer.MAX_VALUE;
        }
      }
      F[0][0] = triangle[0][0];

      int prev = 0, curr = 1;
      for (int i = 1; i < size; ++i) {
        for (int j = 0; j < i + 1; ++j) {
          if (j > 0) {
            F[curr][j] = Math.min(F[prev][j - 1], F[prev][j]) + triangle[i][j];
          } else {
            F[curr][j] = F[prev][j] + triangle[i][j];
          }
        }
        prev ^= 1;
        curr ^= 1;
      }

      int result = Integer.MAX_VALUE;
      for (int i = 0; i < size; ++i) {
        result = Math.min(F[prev][i], result);
      }
      return result;
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    int[][] triangle1 = {
      {2}, {3, 4}, {6, 5, 7}, {4, 1, 8, 3},
    };
    log.info(s.minimumTotal(triangle1));

    int[][] triangle2 = {
      {2}, {3, 2}, {6, 5, 7}, {4, 4, 8, 1},
    };
    log.info(s.minimumTotal(triangle2));

    int[][] triangle3 = {
      {5},
    };
    log.info(s.minimumTotal(triangle3));
  }
}
