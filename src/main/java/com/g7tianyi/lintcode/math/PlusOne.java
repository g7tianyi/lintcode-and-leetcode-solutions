package com.g7tianyi.lintcode.math;

import com.g7tianyi.lintcode.util.Console;
import com.g7tianyi.lintcode.util.Log;
import org.junit.Test;

/**
 * Created by g7tianyi on Aug 23, 2019
 *
 * <p>Problem link: https://www.lintcode.com/problem/plus-one/description
 */
public class PlusOne {

  private static final Log log = new Log();

  public class Solution {

    public int[] plusOne(int[] arr) {
      int len = arr.length;
      int carry = 1;
      for (int i = len - 1; i >= 0; i--) {
        arr[i] += carry;
        if (arr[i] > 9) {
          carry = arr[i] / 10;
          arr[i] = arr[i] % 10;
        } else {
          carry = 0;
        }
      }

      if (carry == 0) {
        return arr;
      }

      int[] result = new int[len + 1];
      result[0] = carry;
      System.arraycopy(arr, 0, result, 1, len);
      return result;
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    log.info(Console.stringify(s.plusOne(new int[] {8})));
    log.info(Console.stringify(s.plusOne(new int[] {9})));
    log.info(Console.stringify(s.plusOne(new int[] {1, 2, 3})));
    log.info(Console.stringify(s.plusOne(new int[] {9, 9, 9, 9, 9})));
  }
}