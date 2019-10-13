package com.g7tianyi.lintcode.array.mutation;

import com.g7tianyi.util.Logger;

/**
 * Created by g7tianyi on Sep 03, 2019
 *
 * @link https://www.lintcode.com/problem/reverse-array/description
 */
public class ReverseArray {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public void reverseArray(int[] nums) {

      if (nums == null || nums.length < 2) {
        return;
      }

      int i = 0, j = nums.length - 1, temp;
      while (i < j) {
        temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;

        ++i;
        --j;
      }
    }
  }
}
