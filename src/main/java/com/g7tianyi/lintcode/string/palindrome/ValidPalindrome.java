package com.g7tianyi.lintcode.string.palindrome;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 29, 2019
 *
 * @link https://www.lintcode.com/problem/valid-palindrome/description
 */
public class ValidPalindrome {

  private static final Logger log = Logger.getInstance();

  public static class Solution {

    // 只考虑字母和数字，忽略大小写
    public boolean isPalindrome(String s) {
      // write your code here
      s = s.trim().toUpperCase();
      int i = 0, j = s.length() - 1;
      while (i < j) {
        while (i < j
            && !(s.charAt(i) >= '0' && s.charAt(i) <= '9')
            && !(s.charAt(i) >= 'A' && s.charAt(i) <= 'Z')) {
          ++i;
        }
        if (i == j) {
          break;
        }

        while (j > i
            && !(s.charAt(j) >= '0' && s.charAt(j) <= '9')
            && !(s.charAt(j) >= 'A' && s.charAt(j) <= 'Z')) {
          --j;
        }
        if (i == j) {
          break;
        }

        if (s.charAt(i) != s.charAt(j)) {
          return false;
        }

        ++i;
        --j;
      }
      return true;
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
          boolean result = s.isPalindrome(aCase.string);
          log.info("%s => %s", aCase.string, result);
          Assert.assertEquals(result, aCase.result);
        };

    c.accept(new Case("A man, a plan, a canal: Panama", true));
    c.accept(new Case("race a car", false));
  }
}
