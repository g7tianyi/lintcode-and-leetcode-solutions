package com.g7tianyi.lintcode.array.boring;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.List;

/**
 * Created by g7tianyi on Oct 25, 2019
 *
 * @link https://www.lintcode.com/problem/find-the-number/description
 */
public class FindTheNumber {

  private static final Logger log = Logger.getInstance();

  public class Solution {
    public boolean findnNumber(int[] nums, int k) {
      if (nums == null) {
        return false;
      }
      for (int num: nums) {
        if (num == k) {
          return true;
        }
      }
      return false;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {


  }
}