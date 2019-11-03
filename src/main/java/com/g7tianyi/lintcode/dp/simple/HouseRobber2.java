package com.g7tianyi.lintcode.dp.simple;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Nov 03, 2019
 *
 * @link https://www.lintcode.com/problem/house-robber-ii/description
 */
public class HouseRobber2 {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int houseRobber2(int[] values) {
      if (values == null || values.length == 0) {
        return 0;
      }
      if (values.length < 4) {
        return max(values);
      }

      int F0, F1, F2, F;
      int result = Integer.MIN_VALUE;

      // 必取第一家，且最后一家必定不取，循环中止条件是 i < values.length - 1
      F0 = values[0];
      F1 = 0;
      F2 = values[0] + values[2];
      for (int i = 3; i < values.length - 1; ++i) {
        F = Math.max(F0, F1) + values[i];
        result = Math.max(result, Math.max(F, F2));
        F0 = F1;
        F1 = F2;
        F2 = F;
      }

      // 必不取第一家，且最后一家可取
      F0 = 0;
      F1 = values[1];
      F2 = Math.max(values[1], values[2]);
      for (int i = 3; i < values.length; ++i) {
        F = Math.max(F0, F1) + values[i];
        result = Math.max(result, Math.max(F, F2));
        F0 = F1;
        F1 = F2;
        F2 = F;
      }

      return result;
    }

    private int max(int[] values) {
      int ret = values[0];
      for (int i = 1; i < values.length; ++i) {
        ret = Math.max(ret, values[i]);
      }
      return ret;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.houseRobber2(Arrays.from(3, 6, 4)));
    log.info(s.houseRobber2(Arrays.from(2, 3, 2, 3)));
    log.info(s.houseRobber2(Arrays.from(3, 1, 4, 4, 3, 2, 4)));
  }
}
