package com.g7tianyi.lintcode.math;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 28, 2019
 *
 * @link https://www.lintcode.com/problem/valid-perfect-square/description
 */
public class ValidPerfectSquare {

  private static final Logger log = new Logger();

  public class Solution {

    // 二分查找
    public boolean isPerfectSquare(int num) {
      if (num < 2) {
        return true;
      }

      int former = 2, latter = num >> 1, middle;
      long square, value = (long) num;
      while (former <= latter) {
        middle = former + ((latter - former) >> 1);
        square = (long) middle * (long) middle;
        if (square == value) {
          return true;
        }
        if (square > value) {
          latter = middle - 1;
        } else {
          former = middle + 1;
        }
      }
      return false;
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Integer> c = num -> log.info("%d => %s", num, s.isPerfectSquare(num));

    c.accept(1000000);

    //    c.accept(0);
    //    c.accept(1);
    //    c.accept(3);
    //    c.accept(9);
    //    c.accept(25);
    //    c.accept(Integer.MAX_VALUE);
  }
}
