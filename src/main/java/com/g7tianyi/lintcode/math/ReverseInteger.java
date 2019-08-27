package com.g7tianyi.lintcode.math;

import com.g7tianyi.lintcode.util.Log;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 25, 2019
 *
 * @link https://www.lintcode.com/problem/reverse-integer/description
 */
public class ReverseInteger {

  private static final Log log = new Log();

  public class Solution {

    public int reverseInteger(int n) {

      int sign = 1;
      if (n < 0) {
        sign = -1;
        n *= -1;
      }

      int num = n;
      long pow = 1;
      while (num != 0) {
        num /= 10;
        pow *= 10;
      }
      pow /= 10;

      long result = 0;
      num = n;
      while (num != 0) {
        result += (num % 10) * pow;
        num /= 10;
        pow /= 10;
      }

      result *= sign;
      if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
        result = 0;
      }

      return (int) result;
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Integer> c = num -> log.info("%d => %d", num, s.reverseInteger(num));

    c.accept(1);
    c.accept(-1);
    c.accept(19);
    c.accept(123);
    c.accept(-123);
    c.accept(Integer.MAX_VALUE);
    c.accept(Integer.MIN_VALUE);
  }
}
