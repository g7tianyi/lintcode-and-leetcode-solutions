package com.g7tianyi.lintcode.others;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 23, 2019
 *
 * @link https://www.lintcode.com/problem/leap-year/description
 */
public class LeapYear {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public boolean isLeapYear(int n) {
      // n % 4 == 0 && n % 100 != 0 or n % 400 == 0
      return (n & 3) == 0 && n % 100 != 0 || n % 400 == 0;
    }
  }

  @AllArgsConstructor
  public static class Input {

    private int num;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Input> runner =
        input -> {
          log.info("%d => %s", input.num, s.isLeapYear(input.num));
          log.info();
        };

    runner.accept(new Input(2019));
    runner.accept(new Input(2020));
  }
}
