package com.g7tianyi.lintcode.others;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Sep 30, 2019
 *
 * @link https://www.lintcode.com/problem/132-pattern/description
 */
public class Pattern132 {

  private static final Logger log = Logger.getInstance();

  public class Solution {
    public boolean find132pattern(int[] nums) {
      // 这个题目的本质是单调栈去找最大的ak
      // 因为ak最大的时候，aj一定也最大，如果在这种情况下都不能找到ai，说明ai不存在
      for (int i = nums.length - 1, ak = Integer.MIN_VALUE; i >= 0; --i) {
        if (nums[i] < ak) {
          return true;
        }
        // i < j < k => ai < ak < aj
        // 把自己当做aj，看后面能不能找到一个小于自己的数作为ak
        for (int k = i + 1; k < nums.length && nums[i] > nums[k]; ++k) {
          ak = nums[k];
        }
      }
      return false;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.find132pattern(Arrays.from(2, 5, 2, 4, 1, 3, 2)));
    log.info(s.find132pattern(Arrays.from(1, 2, 3, 4, 5)));
    log.info(s.find132pattern(Arrays.from(1, 4, 5, 3, 2)));
  }
}
