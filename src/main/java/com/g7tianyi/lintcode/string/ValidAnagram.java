package com.g7tianyi.lintcode.string;

import com.g7tianyi.lintcode.util.Log;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 25, 2019
 *
 * <p>Problem link: https://www.lintcode.com/problem/valid-anagram/description
 */
public class ValidAnagram {

  private static final Log log = new Log();

  public class Solution {

    public boolean anagram(String s, String t) {
      if (s == null || t == null || s.length() != t.length()) {
        return false;
      }

      int[] sPos = countChars(s);
      int[] tPos = countChars(t);

      for (int i = 0; i < 256; i++) {
        if (sPos[i] != tPos[i]) {
          return false;
        }
      }
      return true;
    }

    private int[] countChars(String s) {
      int[] pos = new int[256];
      for (int i = 0; i < 256; i++) {
        pos[i] = 0;
      }
      for (int i = 0; i < s.length(); i++) {
        int ch = (int) s.charAt(i);
        pos[ch]++;
      }
      return pos;
    }
  }

  @AllArgsConstructor
  private static class Input {

    private String s;

    private String t;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Input> c =
        input -> log.info("(%s, %s) => %s", input.s, input.t, s.anagram(input.s, input.t));

    c.accept(new Input("ab", "ab"));
    c.accept(new Input("abcd", "dcba"));
    c.accept(new Input("ac", "ab"));
  }
}
