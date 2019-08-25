package com.g7tianyi.lintcode.math;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by g7tianyi on Aug 25, 2019
 *
 * <p>Problem link: https://www.lintcode.com/problem/second-max-of-array/description
 */
public class SecondMaxOfArray {

  public class Solution {
    public int secondMax(int[] arr) {
      int max1 = Math.max(arr[0], arr[1]);
      int max2 = Math.min(arr[0], arr[1]);
      for (int i = 2; i < arr.length; i++) {
        if (arr[i] > max1) {
          max2 = max1;
          max1 = arr[i];
        } else if (arr[i] > max2) {
          max2 = arr[i];
        }
      }
      return max2;
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Assert.assertEquals(3, s.secondMax(new int[] {1, 3, 2, 4}));
    Assert.assertEquals(2, s.secondMax(new int[] {1, 1, 2, 2}));
  }
}
