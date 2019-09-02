package com.g7tianyi.lintcode.string;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 29, 2019
 *
 * @link https://www.lintcode.com/problem/palindrome-permutation/description
 */
public class PalindromePermutation {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public boolean canPermutePalindrome(String s) {

      int[] pos = new int[256];
      for (int i = 0; i < 256; ++i) {
        pos[i] = 0;
      }

      for (int i = 0; i < s.length(); ++i) {
        ++pos[s.charAt(i)];
      }

      int odd = 0;
      for (int i = 0; i < 256; ++i) {
        if ((pos[i] & 1) == 1) {
          ++odd;
        }
      }

      return odd < 2;
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
          boolean result = s.canPermutePalindrome(aCase.string);
          log.info("%s => %s", aCase.string, result);
          Assert.assertEquals(result, aCase.result);
        };

    c.accept(new Case("aab", true));
    c.accept(new Case("carerac", true));
    c.accept(new Case("code", false));
    c.accept(new Case("AABBCC", true));
  }
}
