package com.g7tianyi.lintcode.greedy;

import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Sep 20, 2019
 *
 * @link https://www.lintcode.com/problem/jump-game/description
 */
public class JumpGame {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public boolean canJump(int[] values) {
      if (values.length <= 1) {
        return true;
      }

      int j = values.length - 2;
      int P = values.length - 1; // 至少需要跳到位置P，才能完成整个跳跃过程
      int D; // Distance
      while (j >= 0) {
        D = P - j;
        if (values[j] >= D) {
          P = j;
        }
        --j;
      }
      return P == 0;
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    log.info(s.canJump(new int[] {2, 3, 1, 1, 4}));
    log.info(s.canJump(new int[] {3, 2, 1, 0, 4}));
  }
}
