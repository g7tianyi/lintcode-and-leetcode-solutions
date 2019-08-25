package com.g7tianyi.lintcode.string;

import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 25, 2019
 *
 * <p>Problem link: https://www.lintcode.com/problem/length-of-last-word/description
 */
public class LengthOfLastWord {

  public class Solution {

    public int lengthOfLastWord(String s) {
      // write your code here
      if (s == null || s.length() == 0) {
        return 0;
      }

      int j = s.length() - 1;
      while (j >= 0 && s.charAt(j) == ' ') {
        j--;
      }

      int end = j;
      while (j >= 0 && s.charAt(j) != ' ') {
        j--;
      }

      return end - j;
    }
  }

  @AllArgsConstructor
  private static class Input {

    private String s;
    private int expected;
  }

  @Test
  public void test() {
    Solution s = new Solution();
    Consumer<Input> c = input -> Assert.assertEquals(input.expected, s.lengthOfLastWord(input.s));
    c.accept(new Input("Hello World", 5));
    c.accept(new Input("Hello LintCode", 8));
    c.accept(new Input("HelloLintCode", 13));
    c.accept(new Input("b a ", 1));
    c.accept(new Input("  ", 0));
  }
}
