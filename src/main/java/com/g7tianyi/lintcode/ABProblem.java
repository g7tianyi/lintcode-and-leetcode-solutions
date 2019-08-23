package com.g7tianyi.lintcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by g7tianyi on Aug 23, 2019
 *
 * <p>https://www.lintcode.com/problem/a-b-problem/
 */
public class ABProblem {

  public class Solution {
    public int aplusb(int a, int b) {
      return a + b;
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Assert.assertEquals(1, s.aplusb(0, 1));
    Assert.assertEquals(0, s.aplusb(0, 0));
    Assert.assertEquals(2, s.aplusb(1, 1));
    Assert.assertEquals(0, s.aplusb(-1, 1));
    Assert.assertEquals(-7, s.aplusb(-10, 3));
    Assert.assertEquals(7, s.aplusb(10, -3));
    Assert.assertEquals(20, s.aplusb(10, 10));
    Assert.assertEquals(-20, s.aplusb(-10, -10));
    Assert.assertEquals(Integer.MAX_VALUE - 10, s.aplusb(Integer.MAX_VALUE, -10));
    Assert.assertEquals(Integer.MIN_VALUE + 9, s.aplusb(Integer.MAX_VALUE, 10));
  }
}
