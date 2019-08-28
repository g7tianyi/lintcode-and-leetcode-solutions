package com.g7tianyi.lintcode.dp;

import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Aug 25, 2019
 *
 * @link https://www.lintcode.com/problem/climbing-stairs/description
 */
public class ClimbingStairs {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    // 斐波那契问题，也是DP问题了
    // F(n) = F(n-1) + F(n-2)，要么在n-2时跨越两级台阶跨上来，要么在n-1时跨越一级台阶
    public int climbStairs(int n) {
      if (n == 1) {
        return 1;
      }
      if (n == 2) {
        return 2;
      }

      int f1 = 1, f2 = 2, f = 0;
      for (int i = 3; i <= n; i++) {
        f = f1 + f2;
        f1 = f2;
        f2 = f;
      }
      return f;
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    log.info(s.climbStairs(1));
    log.info(s.climbStairs(3));
  }
}
