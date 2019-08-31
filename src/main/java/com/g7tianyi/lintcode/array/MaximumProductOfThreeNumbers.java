package com.g7tianyi.lintcode.array;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by g7tianyi on Aug 30, 2019
 *
 * @link https://www.lintcode.com/problem/largest-number-at-least-twice-of-others/description
 */
public class MaximumProductOfThreeNumbers {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int maximumProduct(int[] elems) {

      int size = elems.length, len = Math.min(size, 6);
      int[] nums = new int[len];
      System.arraycopy(elems, 0, nums, 0, len);

      // The former 3 elements are the minimums, while the latter 3 elements are the maximums
      Arrays.sort(nums);
      if (len > 3) {
        int temp = nums[3];
        nums[3] = nums[len - 1];
        nums[len - 1] = temp;
      }

      for (int i = len, j, curr, temp; i < size; ++i) {
        curr = elems[i];
        for (j = 0; j < 3; ++j) { // simulate bubble-sort procedure
          if (nums[j] > curr) {
            temp = nums[j];
            nums[j] = curr;
            curr = temp;
          }
        }
        for (; j < 6; ++j) {
          if (nums[j] < curr) {
            temp = nums[j];
            nums[j] = curr;
            curr = temp;
          }
        }
      }

      // The array length is 6 in the worst case, we can consider the time-complexity to be O(1)
      return select(nums, 0, 0, 0, 1);
    }

    private int select(int[] nums, int index, int left, int right, int product) {

      if (index == nums.length) {
        if (left + right == 3) {
          return product;
        } else {
          return Integer.MIN_VALUE;
        }
      }

      int result = select(nums, index + 1, left, right, product);
      if (index < 3) {
        result = Math.max(result, select(nums, index + 1, left + 1, right, product * nums[index]));
      } else if (index < nums.length) {
        result = Math.max(result, select(nums, index + 1, left, right + 1, product * nums[index]));
      }

      return result;
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    log.info(s.maximumProduct(new int[] {1, 2, 3}));
    log.info(s.maximumProduct(new int[] {3, 6, 1, 0}));
    log.info(s.maximumProduct(new int[] {1, 2, 3, 4}));
    log.info(s.maximumProduct(new int[] {-8, -3, 4, 6}));
    log.info(s.maximumProduct(new int[] {-4, -3, -2, -1}));
    log.info(s.maximumProduct(new int[] {-3, -2, -1, 0, 3}));
    log.info(s.maximumProduct(new int[] {-3, -2, 1, 2, 3}));
    log.info(s.maximumProduct(new int[] {9, 6, 33, 5, 12, 1, 18, 21, 7}));
  }
}
