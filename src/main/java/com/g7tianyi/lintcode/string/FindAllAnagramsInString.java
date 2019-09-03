package com.g7tianyi.lintcode.string;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 03, 2019
 *
 * @link https://www.lintcode.com/problem/find-all-anagrams-in-a-string/description
 */
public class FindAllAnagramsInString {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public List<Integer> findAnagrams(String s, String p) {

      List<Integer> result = new ArrayList<>();
      if (s.length() < p.length()) {
        return result;
      }

      int[] pos = new int[26];
      int[] win = new int[26];
      for (int i = 0; i < 26; ++i) {
        pos[i] = 0;
        win[i] = 0;
      }

      int pLen = p.length();
      for (int i = 0; i < pLen; ++i) {
        ++pos[p.charAt(i) - 'a'];
        ++win[s.charAt(i) - 'a'];
      }

      for (int i = pLen; i < s.length(); ++i) {
        if (equals(pos, win)) {
          result.add(i - pLen);
        }
        --win[s.charAt(i - pLen) - 'a'];
        ++win[s.charAt(i) - 'a'];
      }
      if (equals(pos, win)) {
        result.add(s.length() - pLen);
      }
      return result;
    }

    private boolean equals(int[] pos, int[] win) {
      for (int i = 0; i < pos.length; ++i) {
        if (pos[i] != win[i]) {
          return false;
        }
      }
      return true;
    }
  }

  @AllArgsConstructor
  private class Case {
    String s, p;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c = aCase -> log.info("%s", s.findAnagrams(aCase.s, aCase.p));

    c.accept(new Case("", "a"));
    c.accept(new Case("cbaebabacd", "abc"));
    c.accept(new Case("cbaebabacb", "abc"));
  }
}
