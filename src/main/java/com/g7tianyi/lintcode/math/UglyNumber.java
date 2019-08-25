package com.g7tianyi.lintcode.math;

import com.g7tianyi.lintcode.util.Log;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 25, 2019
 *
 * <p>Problem link: https://www.lintcode.com/problem/ugly-number/description
 */
public class UglyNumber {

  private static final Log log = new Log();

  public class Solution {

    public boolean isUgly(int num) {
      if (num == 0) {
        return false;
      }

      int[] factors = {5 * 3 * 2, 5 * 3, 5 * 2, 3 * 2, 5, 3, 2};
      for (int factor : factors) {
        while (num % factor == 0) {
          num /= factor;
        }
      }
      return num == 1;
    }

    // 这也可以TLE...
    public boolean isUglyTLE(int num) {
      while (num % 2 == 0) {
        num >>= 1;
      }
      while (num % 3 == 0) {
        num /= 3;
      }
      while (num % 5 == 0) {
        num /= 5;
      }
      return num == 1;
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Integer> c = num -> log.info("%d => %s", num, s.isUgly(num));

    c.accept(0);
    c.accept(1);
    c.accept(8);
    c.accept(14);
    c.accept(-8);
  }
}
