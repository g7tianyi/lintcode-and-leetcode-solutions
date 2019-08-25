package com.g7tianyi.lintcode.string;

import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 25, 2019
 *
 * <p>Problem link: https://www.lintcode.com/problem/detect-capital/description
 */
public class DetectCapital {

  public class Solution {

    public boolean detectCapitalUse(String word) {

      int capitals = 0;
      for (int i = 0; i < word.length(); i++) {
        char ch = word.charAt(i);
        if (isCapital(ch)) {
          ++capitals;
        }
      }

      return capitals == 0
          || capitals == word.length()
          || (capitals == 1 && isCapital(word.charAt(0)));
    }

    private boolean isCapital(char ch) {
      return ch >= 'A' && ch <= 'Z';
    }
  }

  @AllArgsConstructor
  private static class Input {

    private String s;
    private boolean expected;
  }

  @Test
  public void test() {
    Solution s = new Solution();
    Consumer<Input> c = input -> Assert.assertEquals(input.expected, s.detectCapitalUse(input.s));
    c.accept(new Input("USA", true));
    c.accept(new Input("FlaG", false));
    c.accept(new Input("A", true));
    c.accept(new Input("code", true));
  }
}
