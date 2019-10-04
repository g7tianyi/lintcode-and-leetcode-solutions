package com.g7tianyi.lintcode.dp.backpack;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Oct 04, 2019
 *
 * @link https://www.lintcode.com/problem/backpack-ix/description
 */
public class Backpack9 {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public double backpackIX(int P, int[] prices, double[] probs) {
      double[] F = new double[P + 1];
      for (int i = 0; i < prices.length; ++i) {
        for (int j = P; j > 0; --j) {
          if (j >= prices[i]) {
            F[j] = Math.max(F[j], 1 - (1 - F[j - prices[i]]) * (1 - probs[i]));
          }
        }
      }
      return F[P];
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.backpackIX(10, Arrays.from(4, 4, 5), new double[] {0.1, 0.2, 0.3}));
    log.info(s.backpackIX(10, Arrays.from(4, 5, 6), new double[] {0.1, 0.2, 0.3}));
    log.info(s.backpackIX(15, Arrays.from(4, 5, 6, 6), new double[] {0.1, 0.2, 0.3, 0.2}));
  }
}
