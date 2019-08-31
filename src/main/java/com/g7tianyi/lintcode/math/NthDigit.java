package com.g7tianyi.lintcode.math;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 31, 2019
 *
 * @link https://www.lintcode.com/problem/nth-digit/description
 */
public class NthDigit {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    // 1 ~ 9 => 9 * 1
    // 10 ~ 99 => 90 * 2
    // 100 ~ 999 => 900 * 3
    // 1000 ~ 9999 => 9000 * 4
    public int findNthDigit(int num) {
      long lower = 0, upper = 9, pow = 1, pos = 1;
      while (upper <= num) {
        ++pos;
        pow *= 10;
        lower = upper;
        upper = lower + 9 * pow * pos;
      }

      // 433 = 9 + 180 + 244
      //
      // 100
      // 101
      // ...
      // 180
      // 181
      // | |________________
      // |                  |
      // The 244th digit    The 246th digit

      num -= lower;
      long remain = pow + (num - 1) / pos;
      long index = num % pos;
      if (index == 0) {
        index = pos;
      }
      index = pos - index;

      while (index > 0) {
        remain /= 10;
        --index;
      }

      return (int) (remain % 10);
    }
  }

  @AllArgsConstructor
  private static class Case {

    private int num;

    private int res;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c =
        aCase -> {
          int res = s.findNthDigit(aCase.num);
          log.info("%d => %d", aCase.num, res);
          Assert.assertEquals(aCase.res, res);
        };

    c.accept(new Case(3, 3));
    c.accept(new Case(11, 0));
    c.accept(new Case(13, 1));
    c.accept(new Case(432, 0));
    c.accept(new Case(433, 1));
    c.accept(new Case(434, 8));
    c.accept(new Case(435, 1));
    c.accept(new Case(971335639, 1));
  }
}
