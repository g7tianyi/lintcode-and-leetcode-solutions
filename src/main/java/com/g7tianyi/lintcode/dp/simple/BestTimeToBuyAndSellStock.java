package com.g7tianyi.lintcode.dp.simple;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Oct 12, 2019
 *
 * @link https://www.lintcode.com/problem/best-time-to-buy-and-sell-stock/description
 */
public class BestTimeToBuyAndSellStock {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int maxProfit(int[] prices) {

      if (prices == null || prices.length < 2) {
        return 0;
      }

      int min = prices[0], result = 0;
      for (int i = 1; i < prices.length; ++i) {
        if (prices[i] > min) {
          result = Math.max(prices[i] - min, result);
        } else {
          min = prices[i];
        }
      }
      return result;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.maxProfit(Arrays.from(3, 2, 3, 1, 2)));
    log.info(s.maxProfit(Arrays.from(1, 2, 3, 4, 5)));
    log.info(s.maxProfit(Arrays.from(5, 4, 3, 2, 1)));
  }
}
