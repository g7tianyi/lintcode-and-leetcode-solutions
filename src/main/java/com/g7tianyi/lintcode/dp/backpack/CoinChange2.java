package com.g7tianyi.lintcode.dp.backpack;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Oct 04, 2019
 *
 * @link https://www.lintcode.com/problem/coin-change-2/description
 */
public class CoinChange2 {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    // 0-1背包问题的变形：物品可以无限取
    public int change(int amount, int[] coins) {
      int[] F = new int[amount + 1];
      F[0] = 1;
      for (int coin : coins) {
        for (int i = 0; i <= amount; ++i) {
          if (i >= coin) {
            F[i] += F[i - coin];
          }
        }
      }
      return F[amount];
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.change(8, Arrays.from(2, 3, 8)));
    log.info(s.change(7, Arrays.from(1, 2, 5)));
  }
}
