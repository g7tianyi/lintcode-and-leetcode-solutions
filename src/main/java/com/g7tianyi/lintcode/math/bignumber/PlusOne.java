package com.g7tianyi.lintcode.math.bignumber;

import com.g7tianyi.common.Strings;
import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Aug 23, 2019
 *
 * @link https://www.lintcode.com/problem/plus-one/description
 */
public class PlusOne {

  private static final Logger log = Logger.getInstance();

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

    log.info(Strings.format(s.plusOne(new int[] {8})));
    log.info(Strings.format(s.plusOne(new int[] {9})));
    log.info(Strings.format(s.plusOne(new int[] {1, 2, 3})));
    log.info(Strings.format(s.plusOne(new int[] {9, 9, 9, 9, 9})));
  }
}
