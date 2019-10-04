package com.g7tianyi.lintcode.dp.backpack;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Oct 04, 2019
 *
 * @link https://www.lintcode.com/problem/combination-sum-iv/description
 */
public class CombinationSum4 {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    // 爬梯子问题，但与完全背包十分相似，完全背包问题与爬梯子问题的差距在于：
    // 前者取背包的次序不重要，后者次序重要，不同的次序意味着不同的解决方案
    // 更进一步地：
    // 1) 完全背包问题：F[i,j]表示前i件物品构成价值j的方案总数，
    //    `F[i,j] = F(i-1, j-Vk) + F(i-1,j)`，循环第一层是物品；
    // 2) 爬梯子问题：F[i]表示爬到第i级的总方案数，`F[i] = Sum(F[i- Vk])`，
    //    循环第一层是梯级 (相当于背包大小)
    public int backPackVI(int[] values, int target) {
      int[] F = new int[target + 1];
      F[0] = 1;
      for (int i = 1; i <= target; ++i) {
        for (int value : values) { // 放在内层循环，意味着可以多次选
          if (i >= value) {
            F[i] += F[i - value];
          }
        }
      }
      return F[target];
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.backPackVI(Arrays.from(1, 2, 4), 4));
    log.info(s.backPackVI(Arrays.from(1, 2), 4));
  }
}
