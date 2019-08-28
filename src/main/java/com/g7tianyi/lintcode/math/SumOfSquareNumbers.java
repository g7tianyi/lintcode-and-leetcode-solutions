package com.g7tianyi.lintcode.math;

import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Aug 25, 2019
 *
 * @link https://www.lintcode.com/problem/sum-of-square-numbers/description
 */
public class SumOfSquareNumbers {

  private static final Logger log = new Logger();

  public class Solution {

    public boolean checkSumOfSquareNumbers(int num) {
      // write your code here
      if (num < 0) {
        return false;
      } else if (num == 0) {
        return true;
      }

      int upper = (int) Math.ceil(Math.sqrt((double) num));

      int a, b;
      for (int i = 0; i < upper; i++) {
        a = i * i;
        for (int j = i + 1; j <= upper; j++) {
          b = a + j * j;
          if (b == num) {
            return true;
          }
          if (b > num) {
            break;
          }
        }
      }

      return false;
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    log.info(s.checkSumOfSquareNumbers(1));
    log.info(s.checkSumOfSquareNumbers(5));
    log.info(s.checkSumOfSquareNumbers(-5));
    log.info(s.checkSumOfSquareNumbers(1546112212));
    log.info(s.checkSumOfSquareNumbers(Integer.MAX_VALUE));
  }
}
