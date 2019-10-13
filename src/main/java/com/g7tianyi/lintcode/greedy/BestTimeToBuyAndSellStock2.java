package com.g7tianyi.lintcode.greedy;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Oct 13, 2019
 *
 * @link https://www.lintcode.com/problem/best-time-to-buy-and-sell-stock-ii/description
 */
public class BestTimeToBuyAndSellStock2 {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int maxProfit(int[] prices) {
      int result = 0;
      for (int i = 0; i < prices.length - 1; i++) {
        if (prices[i + 1] > prices[i]) {
          result += prices[i + 1] - prices[i];
        }
      }
      return result;
    }
  }

  public class O3Solution {

    public int maxProfit(int[] prices) {
      int len = prices.length;
      int[][] F = new int[len][len];
      for (int l = 1; l <= len; ++l) {
        for (int i = 0, j; i + l <= len; ++i) {
          j = i + l - 1;
          F[i][j] = Math.max(prices[j] - prices[i], 0);
          for (int k = i; k < j; ++k) {
            F[i][j] = Math.max(F[i][k] + F[k + 1][j], F[i][j]);
          }
        }
      }
      return F[0][len - 1];
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.maxProfit(Arrays.from(2, 1, 2, 0, 1)));
    log.info(s.maxProfit(Arrays.from(3, 4, 6)));
  }
}
