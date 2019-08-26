package com.g7tianyi.lintcode.string;

import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 26, 2019
 *
 * <p>Problem link: https://www.lintcode.com/problem/rotate-words/description
 */
public class RotateWord {

  public class Solution {

    public int countRotateWords(List<String> words) {

      List<String> uniqueWords = new ArrayList<>();

      for (String word : words) {

        boolean isNewWord = true;
        for (String uniqueWord : uniqueWords) {
          if (isRotateWords(word, uniqueWord)) {
            isNewWord = false;
            break;
          }
        }

        if (isNewWord) {
          uniqueWords.add(word);
        }
      }

      return uniqueWords.size();
    }

    private boolean isRotateWords(String word1, String word2) {
      int len = word1.length();
      if (len != word2.length()) {
        return false;
      }

      char ch = word2.charAt(0);
      for (int i = 0; i < len; i++) {
        if (word1.charAt(i) == ch) { // 可以是从任何位置开始旋转
          boolean result = true;
          for (int j2 = 0; j2 < len; j2++) {
            int j1 = (i + j2) % len;
            if (word1.charAt(j1) != word2.charAt(j2)) {
              result = false;
              break;
            }
          }
          if (result) {
            return true;
          }
        }
      }
      return false;
    }
  }

  @AllArgsConstructor
  private static class Input {

    private List<String> words;

    private int result;
  }

  @Test
  public void test_isRotateWords() {

    Solution s = new Solution();

    Assert.assertTrue(s.isRotateWords("picture", "turepic"));
    Assert.assertTrue(s.isRotateWords("word", "ordw"));
    Assert.assertFalse(s.isRotateWords("word", "orwd"));
    Assert.assertTrue(s.isRotateWords("a", "a"));
    Assert.assertTrue(s.isRotateWords("ab", "ab"));
    Assert.assertTrue(s.isRotateWords("ab", "ba"));
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Input> c = input -> Assert.assertEquals(s.countRotateWords(input.words), input.result);

    List<String> words = new ArrayList<>();
    words.add("picture");
    words.add("turepic");
    words.add("icturep");
    words.add("word");
    words.add("ordw");
    words.add("lint");
    c.accept(new Input(words, 3));

    words.clear();
    words.add("abba");
    words.add("bbaa");
    words.add("baab");
    words.add("aabb");
    words.add("abba");
    c.accept(new Input(words, 1));
  }
}
