package com.g7tianyi.lintcode.dp.backpack;

import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Oct 03, 2019
 *
 * @link https://www.lintcode.com/problem/backpack/description
 */
public class Backpack {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int backPack(int capacity, int[] values) {

      // F[i][j] 表示前i件物品放入容量为j的背包可以获得的最大价值
      // if j >= values[i], F[i][j] = MAX(F[i][j], F[i][j-values[i]] + values[i])
      // if j > values[i], F[i][j] = F[i][j-1], 放不进去，那么直接等于j-1的容量的背包的价值

      int[] F = new int[capacity + 1];
      for (int value : values) {
        // 倒过来遍历，小于j的背包都还存在与F中，一个很Tricky的空间优化技巧
        for (int j = capacity; j > 0; --j) {
          if (j >= value) { // 能放进去
            F[j] = Math.max(F[j], F[j - value] + value);
          }
        }
      }

      return F[capacity];
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.backPack(10, com.g7tianyi.common.Arrays.from(3, 4, 8, 5)));
    log.info(s.backPack(12, com.g7tianyi.common.Arrays.from(2, 3, 5, 7)));
    log.info(
        s.backPack(
            10,
            com.g7tianyi.common.Arrays.from(
                20, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 7, 2, 15, 15, 15, 15)));
  }
}
