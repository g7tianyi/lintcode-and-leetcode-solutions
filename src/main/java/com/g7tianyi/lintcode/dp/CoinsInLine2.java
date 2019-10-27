package com.g7tianyi.lintcode.dp;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Oct 19, 2019
 *
 * @link https://www.lintcode.com/problem/coins-in-a-line/description
 */
public class CoinsInLine2 {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public boolean firstWillWin(int[] values) {
      if (values == null) {
        return false;
      }

      if (values.length < 3) {
        return true;
      }

      // F[i]还剩i个硬币的时候，先手能取得的最大价值
      // F[i] = Sum[i] - MIN { F[i-1], F[i-2] }
      for (int i = values.length - 2; i >= 0; --i) {
        values[i] += values[i + 1];
      }

      int F2 = values[values.length - 1], F1 = values[values.length - 2], F;
      for (int i = values.length - 3; i >= 0; --i) {
        F = values[i] - Math.min(F1, F2);
        F2 = F1;
        F1 = F;
      }
      return F1 > values[0] - F1;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.firstWillWin(Arrays.from(1, 2, 2)));
    log.info(s.firstWillWin(Arrays.from(1, 2, 4)));
    log.info(s.firstWillWin(Arrays.from(1, 2, 3, 5, 3)));
  }
}
