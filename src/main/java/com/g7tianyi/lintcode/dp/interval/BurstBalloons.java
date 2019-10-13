package com.g7tianyi.lintcode.dp.interval;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Sep 23, 2019
 *
 * @link https://www.lintcode.com/problem/burst-balloons/description
 */
public class BurstBalloons {

  private static final Logger log = Logger.getInstance();

  public static class Solution {

    public int maxCoins(int[] nums) {
      if (nums == null || nums.length == 0) {
        return 0;
      }

      int len = nums.length;
      int[] V = new int[len + 2];
      System.arraycopy(nums, 0, V, 1, len);
      V[0] = V[len + 1] = 1;

      // F(i,j)表示灭掉区间[i+1,j-1]的最大得分
      // 注意闭区间是[i+1, j-1]，相当于不扎破气球i和气球j，否者下面的乘法是不成立的
      // F(i,j) = MAX(F(i,k) + F(k, j) + V[i] * V[k] * V[j]), i < k < j，相当于目前吹灭气球k
      int[][] F = new int[len + 2][len + 2];
      for (int i = 0; i < len + 1; ++i) {
        F[i][i + 1] = 0;
      }

      for (int l = 3; l < V.length + 1; ++l) {
        for (int i = 0, j; i + l < V.length + 1; ++i) {
          j = i + l - 1;
          F[i][j] = 0;
          for (int k = i + 1; k < j; ++k) {
            F[i][j] = Math.max(F[i][j], F[i][k] + F[k][j] + V[i] * V[k] * V[j]);
          }
        }
      }
      return F[0][len + 1];
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    log.info(s.maxCoins(Arrays.from(4, 1, 5, 10)));
    log.info(s.maxCoins(Arrays.from(3, 1, 5)));
  }
}
