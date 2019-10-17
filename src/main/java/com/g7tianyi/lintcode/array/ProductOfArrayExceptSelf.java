package com.g7tianyi.lintcode.array;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Oct 17, 2019
 *
 * @link https://www.lintcode.com/problem/product-of-array-except-self/description
 */
public class ProductOfArrayExceptSelf {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int[] productExceptSelf(int[] nums) {

      int[] result = new int[nums.length];
      result[0] = 1;
      for (int i = 1; i < nums.length; ++i) {
        result[i] = result[i - 1] * nums[i - 1];
      }

      int right = nums[nums.length - 1];
      for (int i = nums.length - 2; i >= 0; --i) {
        result[i] *= right;
        right *= nums[i];
      }

      return result;
    }
  }

  // O(N)时间与空间
  public class NSpaceSolution {

    public int[] productExceptSelf(int[] nums) {
      int[] L = new int[nums.length];
      L[0] = 1;
      for (int i = 1; i < nums.length; ++i) {
        L[i] = L[i - 1] * nums[i - 1];
      }

      int[] R = new int[nums.length];
      R[nums.length - 1] = 1;
      for (int i = nums.length - 2; i >= 0; --i) {
        R[i] = R[i + 1] * nums[i + 1];
      }

      int[] result = new int[nums.length];
      for (int i = 0; i < nums.length; ++i) {
        result[i] = L[i] * R[i];
      }
      return result;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.productExceptSelf(Arrays.from(1, 2, 3, 4)));
    log.info(s.productExceptSelf(Arrays.from(2, 3, 8)));
  }
}
