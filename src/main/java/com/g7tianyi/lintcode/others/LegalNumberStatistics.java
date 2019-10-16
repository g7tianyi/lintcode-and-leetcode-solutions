package com.g7tianyi.lintcode.others;

import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Oct 16, 2019
 *
 * @link https://www.lintcode.com/problem/legal-number-statistics/description
 */
public class LegalNumberStatistics {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int getNum(int[] nums, int L, int R) {

      if (nums == null || nums.length == 0) {
        return 0;
      }

      int result = 0;
      for (int num : nums) {
        if (num >= L && num <= R) {
          ++result;
        }
      }
      return result;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {}
}
