package com.g7tianyi.lintcode.string;

import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 26, 2019
 *
 * <p>Problem link: https://www.lintcode.com/problem/palindromic-substrings/description
 */
public class PalindromeSubstrings {

  public class Solution {

    public int countPalindromicSubstrings(String s) {
      // write your code here
      int len = s.length();
      if (len == 0) {
        return 0;
      }
      if (len == 1) {
        return 1;
      }

      boolean[][] F = new boolean[len][len];
      for (int i = 0; i < len; i++) {
        F[i] = new boolean[len];
      }
      for (int i = 0; i < len; i++) {
        for (int j = 0; j < len; j++) {
          F[i][j] = i == j;
        }
      }

      int result = len;
      for (int l = 2; l <= len; l++) { // (#)行的DP公式意味着，一定要先解决长度更短的回文子串问题
        for (int i = 0, j; i + l <= len; i++) {
          j = i + l - 1;
          if (s.charAt(i) != s.charAt(j)) {
            continue;
          }

          if (l == 2 || F[i + 1][j - 1]) { // (#)
            F[i][j] = true;
            ++result;
          }
        }
      }

      return result;
    }
  }

  @AllArgsConstructor
  private static class Input {

    private String str;

    private int result;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Input> c =
        input -> Assert.assertEquals(input.result, s.countPalindromicSubstrings(input.str));

    c.accept(new Input("abc", 3));
    c.accept(new Input("aba", 4));
    c.accept(new Input("aaaaa", 15));
    c.accept(new Input("aabaa", 9));
  }
}
