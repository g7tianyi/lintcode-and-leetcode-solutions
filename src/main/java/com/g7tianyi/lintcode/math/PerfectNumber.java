package com.g7tianyi.lintcode.math;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by g7tianyi on Aug 31, 2019
 *
 * @link https://www.lintcode.com/problem/perfect-number/description
 */
public class PerfectNumber {

  public class Solution {

    public boolean checkPerfectNumber(int num) {
      if (num == 1) {
        return false;
      }

      int sum = 1;
      for (int i = 2; i <= (num >> 1); ++i) {
        if (num % i == 0) {
          sum += i;
        }
      }
      return sum == num;
    }
  }

  @Test
  public void test() {
    Solution s = new Solution();
    Assert.assertFalse(s.checkPerfectNumber(2));
    Assert.assertTrue(s.checkPerfectNumber(28));
  }
}
