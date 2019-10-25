package com.g7tianyi.lintcode.dp.simple;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Oct 25, 2019
 *
 * @link https://www.lintcode.com/problem/house-robber/description
 */
public class HouseRobber {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public long houseRobber(int[] values) {
      if (values == null || values.length == 0) {
        return 0;
      }
      if (values.length == 1) {
        return values[0];
      }
      if (values.length == 2) {
        return Math.max(values[0], values[1]);
      }

      // F[i]表示一定抢劫第i个房子时的最大收益
      // F[i] = MAX {
      //   MAX(F[i-2], F[i-3]) + V[i],
      //   F[i-1]
      // }
      long F0 = -1, F1 = values[0], F2 = values[1], F;
      long result = Long.MIN_VALUE;
      for (int i = 2; i < values.length; ++i) {
        F = Math.max(F0, F1) + values[i];
        result = Math.max(result, Math.max(F, F2));
        F0 = F1;
        F1 = F2;
        F2 = F;
      }
      return result;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.houseRobber(Arrays.from(1)));
    log.info(s.houseRobber(Arrays.from(1, 2)));
    log.info(s.houseRobber(Arrays.from(1, 2, 3)));

    log.info(s.houseRobber(Arrays.from(3, 8, 4)));
    log.info(s.houseRobber(Arrays.from(5, 2, 1, 3)));

    log.info(s.houseRobber(Arrays.from(5, 2, 2, 3, 2)));
  }
}
