package com.g7tianyi.lintcode.array;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 1, 2019
 *
 * @link https://www.lintcode.com/problem/reverse-3-digit-integer/description
 */
public class Reverse3DigitInteger {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int reverseInteger(int num) {
      return (num % 10) * 100 + (num / 10 % 10) * 10 + (num / 100);
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Integer> c = num -> log.info("%d => %d", num, s.reverseInteger(num));

    c.accept(912);
    c.accept(123);
    c.accept(200);
    c.accept(604);
  }
}
