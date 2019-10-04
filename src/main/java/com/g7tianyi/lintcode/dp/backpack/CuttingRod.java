package com.g7tianyi.lintcode.dp.backpack;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Oct 04, 2019
 *
 * @link https://www.lintcode.com/problem/cutting-a-rod/description
 */
public class CuttingRod {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int cutting(int[] prices, int n) {
      if (prices == null || prices.length == 0) {
        return 0;
      }

      // F[i,j]表示长度为j的杆子，切割为前[1,2,...i]段时，所能获得的最大价值
      int[] F = new int[n + 1];
      for (int i = 1; i <= prices.length; ++i) {
        for (int j = i; j <= n; ++j) {
          F[j] = Math.max(F[j], F[j - i] + prices[i - 1]);
        }
      }
      return F[n];
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.cutting(Arrays.from(1, 5, 8, 9, 10, 17, 17, 20), 8));
    log.info(s.cutting(Arrays.from(3, 5, 8, 9, 10, 17, 17, 20), 8));
  }
}
