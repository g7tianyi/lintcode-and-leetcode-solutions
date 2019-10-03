package com.g7tianyi.lintcode.dp.backpack;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Oct 03, 2019
 *
 * @link https://www.lintcode.com/problem/backpack-ii/description
 */
public class Backpack2 {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    // A表示每个物品的大小
    // V表示每个物品的价值
    public int backPackII(int m, int[] A, int[] V) {
      int[] F = new int[m + 1];
      for (int i = 0; i < A.length; ++i) {
        for (int j = m; j > 0; --j) {
          if (j >= A[i]) {
            F[j] = Math.max(F[j], F[j - A[i]] + V[i]);
          }
        }
      }
      return F[m];
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.backPackII(10, Arrays.from(2, 3, 5, 7), Arrays.from(1, 5, 2, 4)));
    log.info(s.backPackII(10, Arrays.from(2, 3, 8), Arrays.from(2, 5, 8)));
  }
}
