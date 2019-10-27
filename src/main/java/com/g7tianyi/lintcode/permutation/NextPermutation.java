package com.g7tianyi.lintcode.permutation;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.Arrays;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Oct 27, 2019
 *
 * @link https://www.lintcode.com/problem/next-permutation/description
 */
public class NextPermutation {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int[] nextPermutation(int[] nums) {
      if (nums == null || nums.length < 2) {
        return nums;
      }

      // 1 3 5 4 2
      // 1 4 2 3 5
      int prev = nums[nums.length - 1];
      for (int i = nums.length - 2; i >= 0; --i) {
        if (nums[i] < prev) {
          int j = i + 1;
          for (int k = j + 1; k < nums.length; ++k) {
            if (nums[k] > nums[i] && nums[k] < nums[j]) {
              j = k;
            }
          }
          swap(nums, i, j);
          Arrays.sort(nums, i + 1, nums.length);
          return nums;
        }
        prev = nums[i];
      }

      reverse(nums, 0, nums.length - 1);

      return nums;
    }

    private void reverse(int[] values, int i, int j) {
      int temp;
      while (i < j) {
        temp = values[i];
        values[i] = values[j];
        values[j] = temp;
        ++i;
        --j;
      }
    }

    private void swap(int[] values, int i, int j) {
      int temp = values[i];
      values[i] = values[j];
      values[j] = temp;
    }
  }

  private final Solution s = new Solution();

  private final Consumer<int[]> c =
      values -> {
        log.info(values);
        log.info(s.nextPermutation(values));
        log.info();
      };

  @Test
  public void test() {
    c.accept(new int[] {1, 3, 2, 3});
    c.accept(new int[] {4, 3, 2, 1});
    c.accept(new int[] {1, 3, 5, 4, 2});
  }
}
