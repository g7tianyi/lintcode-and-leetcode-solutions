package com.g7tianyi.lintcode.math;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by g7tianyi on Aug 25, 2019
 *
 * @link https://www.lintcode.com/problem/happy-number/description
 */
public class HappyNumber {

  public class Solution {

    public boolean isHappy(int n) {
      Set<Integer> set = new HashSet<>();
      while (true) {
        n = happySum(n);
        if (n == 1) {
          return true;
        }
        if (set.contains(n)) {
          return false;
        }
        set.add(n);
      }
    }

    private int happySum(int n) {
      int sum = 0, num;
      while (n != 0) {
        num = n % 10;
        sum += num * num;
        n /= 10;
      }
      return sum;
    }
  }

  @Test
  public void test() {
    Solution s = new Solution();
    Assert.assertTrue(s.isHappy(19));
    Assert.assertFalse(s.isHappy(5));
  }
}
