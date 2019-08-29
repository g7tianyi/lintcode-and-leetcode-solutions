package com.g7tianyi.lintcode.math;

import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Aug 29, 2019
 *
 * @link https://www.lintcode.com/problem/greatest-common-divisor/note/191206
 */
public class GreatestCommonDivisor {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int gcd(int a, int b) {
      int large = a, small = b;
      if (a < b) {
        large = b;
        small = a;
      }

      int temp;
      while (small != 0) {
        temp = large % small;
        large = small;
        small = temp;
      }

      return large;
    }
  }

  @Test
  public void test() {
    Solution s = new Solution();
    log.info(s.gcd(10, 15));
    log.info(s.gcd(15, 30));
  }
}
