package com.g7tianyi.lintcode.dp;

import com.g7tianyi.util.Logger;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

/**
 * Created by g7tianyi on Oct 27, 2019
 *
 * @link https://www.lintcode.com/problem/maximum-subarray-ii/description
 */
public class MaximumSubArray2 {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int maxTwoSubArrays(List<Integer> nums) {
      if (nums == null || nums.size() < 2) {
        return 0;
      }

      int len = nums.size();
      int[] F = new int[len]; // F[i]表示从[0, i]之间的最大子数组和
      F[0] = nums.get(0);
      int prev = F[0];
      for (int i = 1; i < len; ++i) {
        if (prev > 0) {
          prev = prev + nums.get(i);
        } else {
          prev = nums.get(i);
        }
        F[i] = Math.max(F[i - 1], prev);
      }

      int[] H = new int[len]; // H[i]表示从[i, len)之间最大的子数组和
      H[len - 1] = nums.get(nums.size() - 1);
      prev = H[len - 1];
      for (int i = len - 2; i >= 0; --i) {
        if (prev > 0) {
          prev = prev + nums.get(i);
        } else {
          prev = nums.get(i);
        }
        H[i] = Math.max(H[i + 1], prev);
      }

      int result = Integer.MIN_VALUE;
      for (int i = 0; i < len - 1; ++i) {
        if (result < F[i] + H[i + 1]) {
          result = F[i] + H[i + 1];
        }
      }
      return result;
    }
  }

  // MLE，囧
  public class MLESolution {

    public int maxTwoSubArrays(List<Integer> nums) {

      if (nums == null || nums.size() < 2) {
        return 0;
      }

      int len = nums.size();
      // F(i,j)表示数组nums从i到j的子集中，最大子数组和
      int[][] F = new int[len][len];

      for (int i = 0; i < len; ++i) {
        F[i][i] = nums.get(i);
        int prev = F[i][i]; // H(i)表示以i结尾的子数组的最大和，优化为一维变量prev
        for (int j = i + 1; j < len; ++j) {
          if (prev > 0) {
            prev = prev + nums.get(j);
          } else {
            prev = nums.get(j);
          }
          F[i][j] = Math.max(F[i][j - 1], prev);
        }
      }

      int result = Integer.MIN_VALUE;
      for (int i = 0; i < len - 1; ++i) {
        if (result < F[0][i] + F[i + 1][len - 1]) {
          result = F[0][i] + F[i + 1][len - 1];
        }
      }
      return result;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.maxTwoSubArrays(Lists.newArrayList(1, 3, -1, 2, -1, 2)));
    log.info(s.maxTwoSubArrays(Lists.newArrayList(5, 4)));
    log.info(s.maxTwoSubArrays(Lists.newArrayList(0, -1)));
  }
}
