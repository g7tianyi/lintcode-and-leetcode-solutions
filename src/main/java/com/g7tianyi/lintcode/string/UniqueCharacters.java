package com.g7tianyi.lintcode.string;

import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 25, 2019
 *
 * @link https://www.lintcode.com/problem/unique-characters/description
 */
public class UniqueCharacters {

  public class Solution {

    public boolean isUnique(String s) {
      int[] occ = new int[256];
      for (int i = 0; i < 256; i++) {
        occ[i] = 0;
      }

      for (int i = 0; i < s.length(); i++) {
        int ch = s.charAt(i);
        if (++occ[ch] > 1) {
          return false;
        }
      }
      return true;
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

    Consumer<Input> c = input -> Assert.assertEquals(input.expected, s.isUnique(input.s));

    c.accept(new Input("abc_____", false));
    c.accept(new Input("abc", true));
  }
}
