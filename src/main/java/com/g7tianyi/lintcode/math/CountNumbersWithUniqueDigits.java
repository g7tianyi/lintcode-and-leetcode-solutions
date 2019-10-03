package com.g7tianyi.lintcode.math;

import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Oct 03, 2019
 *
 * @link https://www.lintcode.com/problem/count-numbers-with-unique-digits/description
 */
public class CountNumbersWithUniqueDigits {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int countNumbersWithUniqueDigits(int n) {
      if (n > 10) {
        n = 10;
      }

      // F[i]表示一个数必须由i个数字构成时，满足条件的数字有多少
      int[] F = new int[11];
      F[1] = 10;
      F[2] = 9 * 9;
      for (int i = 3; i <= n; ++i) {
        F[i] = (10 - i + 1) * F[i - 1];
      }

      int result = 0;
      for (int i = 1; i <= n; ++i) {
        result += F[i];
      }
      return result;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    for (int i = 1; i < 15; ++i) {
      log.info("%d %d", i, s.countNumbersWithUniqueDigits(i));
    }
  }
}
