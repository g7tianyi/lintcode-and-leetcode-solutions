package com.g7tianyi.lintcode.string.palindrome;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 02, 2019
 *
 * @link https://www.lintcode.com/problem/longest-palindrome/description
 */
public class LongestPalindrome {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int longestPalindrome(String s) {
      int[] uppers = new int[26], lowers = new int[26];
      for (int i = 0; i < 26; ++i) {
        uppers[i] = 0;
        lowers[i] = 0;
      }

      char ch;
      for (int i = 0; i < s.length(); ++i) {
        ch = s.charAt(i);
        if (ch >= 'A' && ch <= 'Z') {
          ++uppers[ch - 'A'];
        }
        if (ch >= 'a' && ch <= 'z') {
          ++lowers[ch - 'a'];
        }
      }

      int evens = 0, odds = 0;
      boolean mid = false;
      for (int i = 0; i < 26; ++i) {
        if ((uppers[i] & 1) == 0) {
          evens += uppers[i];
        } else {
          mid = true;
          if (uppers[i] > 2) {
            odds += (uppers[i] - 1);
          }
        }
      }

      for (int i = 0; i < 26; ++i) {
        if ((lowers[i] & 1) == 0) {
          evens += lowers[i];
        } else {
          mid = true;
          if (lowers[i] > 2) {
            odds += (lowers[i] - 1);
          }
        }
      }

      return evens + odds + (mid ? 1 : 0);
    }
  }

  @AllArgsConstructor
  private class Case {
    String s;
    int expect;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c =
        aCase -> {
          int result = s.longestPalindrome(aCase.s);
          Assert.assertEquals(aCase.expect, result);
        };

    c.accept(new Case("abccccdd", 7));
    c.accept(new Case("ABBBCCDDCC", 9));
    c.accept(new Case("ABBBCCDDEEECC", 11));
  }
}
