package com.g7tianyi.lintcode.string.match;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 04, 2019
 *
 * @link https://www.lintcode.com/problem/remove-duplicate-letters/description
 */
public class RemoveDuplicateLetters {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public String removeDuplicateLetters(String s) {

      int[] count = new int[26];
      char[] stack = new char[26];
      boolean[] inStack = new boolean[26];

      for (char ch : s.toCharArray()) {
        ++count[ch - 'a'];
      }

      int index = 0;
      for (char ch : s.toCharArray()) {
        --count[ch - 'a'];
        if (!inStack[ch - 'a']) {
          while (index > 0 && stack[index - 1] > ch && count[stack[index - 1] - 'a'] > 0) {
            index--;
            inStack[stack[index] - 'a'] = false;
          }
          stack[index++] = ch;
          inStack[ch - 'a'] = true;
        }
      }
      return new String(stack, 0, index);
    }
  }

  // DFS search, TLE
  public class TLESolution {

    public String removeDuplicateLetters(String s) {

      int[] letters = new int[26];
      for (int i = 0; i < 26; ++i) {
        letters[i] = -1;
      }
      for (int i = 0; i < s.length(); ++i) {
        letters[s.charAt(i) - 'a'] = 1;
      }

      int length = 0;
      for (int appear : letters) {
        if (appear == 1) {
          ++length;
        }
      }

      StringBuilder stringBuilder = new StringBuilder();
      return construct(stringBuilder, s, letters, null, 0, length);
    }

    // DFS
    private String construct(
        StringBuilder stringBuilder,
        String source,
        int[] letters,
        String result,
        int maxIndex,
        int length) {

      if (stringBuilder.length() == length) {
        String s = stringBuilder.toString();
        if (result == null || s.compareTo(result) < 0) {
          return s;
        } else {
          return result;
        }
      }

      for (int index = maxIndex; index < source.length(); ++index) {
        char ch = source.charAt(index);
        if (letters[ch - 'a'] == 1) {
          stringBuilder.append(ch);
          letters[ch - 'a'] = 0;
          result = construct(stringBuilder, source, letters, result, index + 1, length);
          letters[ch - 'a'] = 1;
          stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
      }

      return result;
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<String> c =
        str -> {
          log.info(str);
          log.info(s.removeDuplicateLetters(str));
          log.info();
        };

    c.accept("bcabc");
    c.accept("cbacdcbc");
  }
}
