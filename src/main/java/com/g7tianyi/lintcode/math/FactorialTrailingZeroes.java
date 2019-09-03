package com.g7tianyi.lintcode.math;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 03, 2019
 *
 * @link https://www.lintcode.com/problem/factorial-trailing-zeroes/description
 */
public class FactorialTrailingZeroes {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int trailingZeroes(int num) {
      int result = 0;
      do {
        num /= 5;
        result += num;
      } while (num != 0);
      return result;
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Integer> c = aCase -> log.info("%d => %d", aCase, s.trailingZeroes(aCase));

    c.accept(5);
    c.accept(10);
    c.accept(101);
  }
}
