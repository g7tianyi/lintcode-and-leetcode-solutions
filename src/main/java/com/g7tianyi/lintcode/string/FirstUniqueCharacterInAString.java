package com.g7tianyi.lintcode.string;

import com.g7tianyi.lintcode.util.Log;
import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 25, 2019
 *
 * <p>Problem link: https://www.lintcode.com/problem/first-unique-character-in-a-string/description
 */
public class FirstUniqueCharacterInAString {

  private static final Log log = new Log();

  public class Solution {

    public char firstUniqChar(String s) {
      int[] pos = new int[256];
      int[] occ = new int[256];
      for (int i = 0; i < 256; i++) {
        pos[i] = -1;
        occ[i] = 0;
      }

      for (int i = 0; i < s.length(); i++) {
        int ch = s.charAt(i);
        if (pos[ch] == -1) {
          pos[ch] = i;
        }
        ++occ[ch];
      }

      int minPos = s.length();
      int result = s.charAt(0);
      for (int i = 0; i < 256; i++) {
        if (occ[i] == 1 && pos[i] < minPos) {
          minPos = pos[i];
          result = i;
        }
      }

      return (char) result;
    }
  }

  @AllArgsConstructor
  private static class Input {

    private String s;

    private char ch;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Input> c = input -> Assert.assertEquals(input.ch, s.firstUniqChar(input.s));

    c.accept(new Input("abaccdeff", 'b'));
    c.accept(new Input("aabccd", 'b'));
  }
}
