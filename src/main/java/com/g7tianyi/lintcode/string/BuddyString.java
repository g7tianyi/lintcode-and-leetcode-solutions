package com.g7tianyi.lintcode.string;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 03, 2019
 *
 * @link https://www.lintcode.com/problem/buddy-strings/description
 */
public class BuddyString {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public boolean buddyStrings(String A, String B) {
      if (A.length() != B.length()) {
        return false;
      }

      StringBuilder sa = new StringBuilder();
      StringBuilder sb = new StringBuilder();

      int[] pos = new int[26];
      for (int i = 0; i < pos.length; ++i) {
        pos[i] = 0;
      }

      char cha, chb;
      for (int i = 0; i < A.length(); ++i) {
        cha = A.charAt(i);
        chb = B.charAt(i);
        if (cha != chb) {
          sa.append(cha);
          sb.append(chb);
        }
        ++pos[cha - 'a'];
      }

      if (sb.length() == 0) {
        for (int po : pos) {
          if (po >= 2) {
            return true;
          }
        }
      } else if (sa.length() == 2) {
        sa.reverse();
        return sa.toString().equals(sb.toString());
      }
      return false;
    }
  }

  @AllArgsConstructor
  private class Case {
    String A;
    String B;
    boolean expect;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c =
        aCase -> {
          boolean result = s.buddyStrings(aCase.A, aCase.B);
          log.info("%s %s => %s", aCase.A, aCase.B, result);
          Assert.assertEquals(aCase.expect, result);
        };

    c.accept(new Case("ab", "ba", true));
    c.accept(new Case("ab", "ab", false));
    c.accept(new Case("aa", "aa", true));
    c.accept(new Case("aaaabc", "caaaab", false));
    c.accept(new Case("aaaabc", "aaaacb", true));
    c.accept(new Case("aaaabc", "aaacba", true));
  }
}
