package com.g7tianyi.lintcode.string;

import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 29, 2019
 *
 * @link https://www.lintcode.com/problem/word-pattern/description
 */
public class WordPattern {

  public class Solution {

    public boolean wordPattern(String pattern, String s) {

      String[] words = s.split("\\s+");
      if (words.length != pattern.length()) {
        return false;
      }

      // 记录模式字符串中，"字符=>单词"的映射关系
      String[] pos = new String[256];
      for (int i = 0; i < 256; ++i) {
        pos[i] = null;
      }

      // abba
      // dog cat cat dog
      // dog cat cat fish
      for (int i = 0; i < pattern.length(); ++i) {
        char ch = pattern.charAt(i);
        if (pos[ch] == null) {
          for (int j = 0; j < 256; j++) {
            if (words[i].equals(pos[j])) {
              return false;
            }
          }
          pos[ch] = words[i];
        } else if (!pos[ch].equals(words[i])) {
          return false;
        }
      }

      return true;
    }
  }

  @AllArgsConstructor
  private static class Case {

    private boolean result;
    private String pattern;
    private String s;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c =
        aCase -> Assert.assertEquals(aCase.result, s.wordPattern(aCase.pattern, aCase.s));

    c.accept(new Case(true, "abba", "dog cat cat dog"));
    c.accept(new Case(false, "abba", "dog cat cat fish"));
    c.accept(new Case(false, "abba", "dog dog dog dog"));
  }
}
