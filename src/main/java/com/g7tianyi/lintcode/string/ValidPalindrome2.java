package com.g7tianyi.lintcode.string;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 29, 2019
 *
 * @link https://www.lintcode.com/problem/valid-palindrome-ii/description
 */
public class ValidPalindrome2 {

  private static final Logger log = Logger.getInstance();

  public static class Solution {

    public boolean validPalindrome(String s) {
      // Write your code here
      if (s == null) {
        return false;
      }

      char[] chars = s.toCharArray();
      int i = 0, j = chars.length - 1;
      while (i < j && chars[i] == chars[j]) {
        ++i;
        --j;
      }

      if (i < j) {
        return isPalindrome(chars, i + 1, j) || isPalindrome(chars, i, j - 1);
      }
      return true;
    }

    private static boolean isPalindrome(char[] chars, int i, int j) {
      while (i < j && chars[i] == chars[j]) {
        ++i;
        --j;
      }
      return i >= j;
    }
  }

  @AllArgsConstructor
  private static class Case {

    private String string;

    private boolean result;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c =
        aCase -> {
          boolean result = s.validPalindrome(aCase.string);
          log.info("%s => %s", aCase.string, result);
          Assert.assertEquals(result, aCase.result);
        };

    c.accept(new Case("aba", true));
    c.accept(new Case("abca", true));
    c.accept(new Case("abc", false));
  }
}
