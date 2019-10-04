package com.g7tianyi.lintcode.dp.backpack;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Oct 04, 2019
 *
 * @link https://www.lintcode.com/problem/coin-change/description
 */
public class CoinChange {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int coinChange(int[] coins, int amount) {
      // 完全背包问题
      // F[i,j]表示前i个硬币获得j的各种方案中，使用的硬币数最少的方案到底使用了多少个硬币——为什么说着这么拗口
      // F[i, j] = MIN( F[i-1][j-k*c[i]] + k | 0 <= k*c[i] <= j )
      int[] F = new int[amount + 1];
      for (int i = 1; i < amount + 1; ++i) { // F[0] = 0
        F[i] = -1;
      }
      for (int coin : coins) {
        // 与01背包问题相比，只是换了个次序，进行正常的遍历：
        // 因为完全背包的特点恰是每种物品可选无限件，所以在考虑"加选一件第i种物品"这种策略时，
        // 正需要一个可能已选入第i种物品的子结果F[i][j-c[i]]，F[i][j-c[i]]表示之前已经选了一次或n次c[i]
        for (int j = 1; j <= amount; ++j) {
          if (j >= coin && F[j - coin] != -1 && (F[j] == -1 || F[j] > F[j - coin] + 1)) {
            F[j] = F[j - coin] + 1;
          }
        }
      }
      return F[amount];
    }
  }

  // 没有任何优化的完全背包
  public class TLE_Solution {

    public int coinChange(int[] coins, int amount) {
      // 完全背包问题
      // F[i,j]表示前i个硬币获得j的各种方案中，使用的硬币数最少的方案到底使用了多少个硬币——为什么说着这么拗口
      // F[i, j] = MIN( F[i-1][j-k*c[i]] + k | 0 <= k*c[i] <= j )
      int[] F = new int[amount + 1];
      for (int i = 1; i < amount + 1; ++i) { // F[0] = 0
        F[i] = -1;
      }
      for (int coin : coins) {
        for (int j = amount; j >= 0; --j) {
          for (int k = 0; coin != 0 && k * coin <= j; ++k) { // 该面值取0, 1, ..., n个
            int solutions = F[j - k * coin] + k;
            if (F[j - k * coin] != -1 && (F[j] == -1 || F[j] > solutions)) {
              F[j] = solutions;
            }
          }
        }
      }
      return F[amount];
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.coinChange(Arrays.from(1, 2, 5), 11));
    log.info(s.coinChange(Arrays.from(2), 3));
    log.info(s.coinChange(Arrays.from(0, 1), 0));
    log.info(s.coinChange(Arrays.from(1, 2, 4), 32000));
  }
}
