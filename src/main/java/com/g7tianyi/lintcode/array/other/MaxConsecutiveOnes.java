package com.g7tianyi.lintcode.array.other;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Oct 15, 2019
 *
 * @link https://www.lintcode.com/problem/max-consecutive-ones/description
 */
public class MaxConsecutiveOnes {

  private static final Logger log = Logger.getInstance();

  public class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
      if (nums == null || nums.length == 0) {
        return 0;
      }

      int result = 0, len = 0;
      for (int num : nums) {
        if (num == 1) {
          ++len;
        } else {
          result = Math.max(result, len);
          len = 0;
        }
      }
      return Math.max(result, len);
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.findMaxConsecutiveOnes(Arrays.from(1, 1, 0, 1, 1, 1)));
  }
}
