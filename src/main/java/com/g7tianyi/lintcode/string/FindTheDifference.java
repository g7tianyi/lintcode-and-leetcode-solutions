package com.g7tianyi.lintcode.string;

import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 25, 2019
 *
 * <p>Problem link: https://www.lintcode.com/problem/find-the-difference/description
 */
public class FindTheDifference {

  public class Solution {

    public char findTheDifference(String s, String t) {
      int[] sCount = new int[256];
      int[] tCount = new int[256];
      for (int i = 0; i < 256; i++) {
        sCount[i] = 0;
        tCount[i] = 0;
      }

      char ch = t.charAt(t.length() - 1);
      ++tCount[ch];

      for (int i = 0; i < s.length(); i++) {
        ch = s.charAt(i);
        ++sCount[ch];

        ch = t.charAt(i);
        ++tCount[ch];
      }

      for (int i = 0; i < 256; i++) {
        if (sCount[i] != tCount[i]) {
          return (char) i;
        }
      }

      return 'a';
    }
  }

  @AllArgsConstructor
  private static class Input {

    private String s;
    private String t;
    private char ch;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Input> c =
        input -> Assert.assertEquals(input.ch, s.findTheDifference(input.s, input.t));

    c.accept(new Input("abcd", "abcde", 'e'));
  }
}
