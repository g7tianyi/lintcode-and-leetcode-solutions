package com.g7tianyi.lintcode.string;

import com.g7tianyi.lintcode.util.Log;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 25, 2019
 *
 * @link https://www.lintcode.com/problem/longest-word/description
 */
public class LongestWord {

  private static final Log log = new Log();

  public class Solution {

    /** 挑战：遍历两次的办法很容易想到，如果只遍历一次你有没有什么好办法？ */
    public List<String> longestWords(String[] words) {

      List<String> result = new ArrayList<>();

      int maxLen = 0, wordLen;
      for (String word : words) {
        wordLen = word.length();
        if (maxLen < wordLen) {
          maxLen = wordLen;
          result.clear();
        }
        if (wordLen == maxLen) {
          result.add(word);
        }
      }

      return result;
    }
  }

  @AllArgsConstructor
  private static class Input {
    private String[] words;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Input> c = input -> log.info(s.longestWords(input.words));

    c.accept(
        new Input(new String[] {"dog", "google", "facebook", "internationalization", "blabla"}));
    c.accept(new Input(new String[] {"like", "love", "hate", "yes"}));
  }
}
