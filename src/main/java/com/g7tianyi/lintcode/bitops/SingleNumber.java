package com.g7tianyi.lintcode.bitops;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

/**
 * Created by g7tianyi on Aug 25, 2019
 *
 * @link https://www.lintcode.com/problem/single-number/description
 */
public class SingleNumber {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int singleNumber(int[] arr) {
      int num = 0;
      for (int elem : arr) {
        num ^= elem;
      }
      return num;
    }
  }

  @AllArgsConstructor
  public static class Input {}

  @Test
  public void test() {

    Solution s = new Solution();

    log.info("%d", s.singleNumber(new int[] {1, 1, 2, 2, 3, 4, 4}));
    log.info("%d", s.singleNumber(new int[] {0, 0, 1}));
  }
}
