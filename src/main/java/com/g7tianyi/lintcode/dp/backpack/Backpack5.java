package com.g7tianyi.lintcode.dp.backpack;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Oct 03, 2019
 *
 * @link https://www.lintcode.com/problem/backpack-v/description
 */
public class Backpack5 {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int backPackV(int[] nums, int target) {
      // F[i, j]表示前i个物品得到价值j，一共有多少种方案
      int[] F = new int[target + 1];
      F[0] = 1; // 一个都不拿的目标价值当然为0
      for (int num : nums) {
        for (int j = target; j > 0; --j) {
          if (j >= num) {
            F[j] += F[j - num];
          }
        }
      }
      return F[target];
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.backPackV(Arrays.from(1, 2, 3, 3, 7), 7));
  }
}
