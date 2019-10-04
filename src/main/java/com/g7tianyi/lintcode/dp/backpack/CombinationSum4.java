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
