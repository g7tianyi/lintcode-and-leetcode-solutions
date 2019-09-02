package com.g7tianyi.lintcode.string;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 02, 2019
 *
 * @link https://www.lintcode.com/problem/repeated-substring-pattern/description
 */
public class RepeatedSubstringPattern {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public boolean repeatedSubstringPattern(String s) {
      char[] chars = s.toCharArray();
      int len = chars.length;
      for (int subLen = len >> 1; subLen >= 1; --subLen) {
        if (len % subLen != 0) {
          continue;
        }
        int partitions = len / subLen;
        boolean ok = true;
        for (int partition = 1; partition < partitions; ++partition) {
          if (!equals(chars, subLen, partition * subLen)) {
            ok = false;
            break;
          }
        }
        if (ok) {
          return true;
        }
      }
      return false;
    }

    private boolean equals(char[] chars, int sublen, int i) {
      for (int j = 0; j < sublen; ++j) {
        if (chars[j] != chars[i + j]) {
          return false;
        }
      }
      return true;
    }
  }

  @AllArgsConstructor
  private class Case {
    String s;
    boolean expect;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c =
        aCase -> {
          boolean result = s.repeatedSubstringPattern(aCase.s);
          log.info("%s => %s", aCase.s, result);
          Assert.assertEquals(aCase.expect, result);
        };

    c.accept(new Case("ababab", true));
    c.accept(new Case("aba", false));
    c.accept(new Case("abcabcabcabc", true));
  }
}
