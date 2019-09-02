package com.g7tianyi.lintcode.string.palindrome;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 26, 2019
 *
 * @link https://www.lintcode.com/problem/palindrome-number/description
 */
public class PalindromeNumber {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public boolean isPalindrome(int num) {

      if (num < 10) {
        return true;
      }

      long pow = 1;
      int val = num;
      while (val != 0) {
        pow *= 10;
        val /= 10;
      }

      pow /= 10;
      long rev = 0;
      val = num;
      while (val != 0) {
        rev += (val % 10) * pow;
        val /= 10;
        pow /= 10;
      }

      return rev == (long) num;
    }
  }

  @AllArgsConstructor
  private static class Input {

    private int num;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Input> c = input -> log.info("[%d] => [%s]", input.num, s.isPalindrome(input.num));

    c.accept(new Input(8));
    c.accept(new Input(10));
    c.accept(new Input(11));
    c.accept(new Input(1232));
    c.accept(new Input(123321));
    c.accept(new Input(1234321));
  }
}
