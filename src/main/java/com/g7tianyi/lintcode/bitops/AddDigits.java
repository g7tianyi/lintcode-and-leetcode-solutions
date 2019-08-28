package com.g7tianyi.lintcode.bitops;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 28, 2019
 *
 * @link https://www.lintcode.com/problem/add-digits/description
 */
public class AddDigits {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    // Could you do it without any loop/recursion in O(1) runtime?
    // 😉😉😉
    //
    // Example:
    //
    // 438 = 40 * 10 + 3 * 10 + 8
    // 4 + 3 + 8 == 4 * (10 % 9) * (10 % 9) + 3 * (10 % 9) + 8 % 9 = 15
    //
    // 15 = 1 * 10 + 5
    // 1 + 5 = 1 * (10 % 9) + 5 % 9 = 6
    //
    public int addDigits(int num) {
      if (num == 0) {
        return 0;
      }
      num %= 9;
      if (num == 0) {
        return 9;
      } else {
        return num;
      }
    }
  }

  @AllArgsConstructor
  private static class Case {

    private int num;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c = aCase -> log.info(s.addDigits(aCase.num));

    c.accept(new Case(38));
    c.accept(new Case(9));
  }
}
