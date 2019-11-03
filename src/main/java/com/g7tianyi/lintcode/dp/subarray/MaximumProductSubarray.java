package com.g7tianyi.lintcode.dp.subarray;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Nov 03, 2019
 *
 * @link https://www.lintcode.com/problem/maximum-product-subarray/description
 */
public class MaximumProductSubarray {

  private static final Logger log = Logger.getInstance();

public class Solution {

  public int maxProduct(int[] nums) {

    int ret = nums[0];
    int min = nums[0]; // 以前面一个数字结尾的子序列的最小乘积
    int max = nums[0]; // 以前面一个数字结尾的子序列的最大乘积
    int[] temp = new int[3];
    for (int i = 1; i < nums.length; ++i) {
      temp[0] = nums[i];
      temp[1] = nums[i] * min;
      temp[2] = nums[i] * max;
      min = max = nums[i];
      for (int val : temp) {
        min = Math.min(min, val);
        max = Math.max(max, val);
      }
      ret = Math.max(ret, max);
    }
    return ret;
  }
}

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.maxProduct(Arrays.from(2, 3, -2, 4)));
    log.info(s.maxProduct(Arrays.from(-1, 2, 4, 1)));
    log.info(s.maxProduct(Arrays.from(-2, 3, -4, -2)));
    log.info(s.maxProduct(Arrays.from(2, 3, -4, -2)));
    log.info(s.maxProduct(Arrays.from(2, 3, -4, 2)));
  }
}
