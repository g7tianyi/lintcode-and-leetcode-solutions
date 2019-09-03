package com.g7tianyi.lintcode.string;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 03, 2019
 *
 * @link https://www.lintcode.com/problem/goat-latin/description
 */
public class GoatLatin {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    private final String[] vowels =
        new String[] {
          "a", "e", "i", "o", "u",
          "A", "E", "I", "O", "U"
        };

    public String toGoatLatin(String S) {
      if (S == null || S.length() == 0) {
        return S;
      }

      String[] words = S.split("\\s+");
      StringBuilder sb = new StringBuilder();
      StringBuilder trailing = new StringBuilder();
      for (String word : words) {
        trailing.append("a");
        if (startsWithVowels(word)) {
          sb.append(word);
        } else {
          sb.append(word.substring(1)).append(word.charAt(0));
        }
        sb.append("ma").append(trailing).append(" ");
      }

      if (sb.length() > 0 && sb.charAt(sb.length() - 1) == ' ') {
        sb.deleteCharAt(sb.length() - 1);
      }
      return sb.toString();
    }

    private boolean startsWithVowels(String word) {
      for (String vowel : vowels) {
        if (word.startsWith(vowel)) {
          return true;
        }
      }
      return false;
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<String> c =
        S -> {
          log.info(S);
          log.info(s.toGoatLatin(S));
          log.info();
        };

    c.accept("I speak Goat Latin");
    c.accept("The quick brown fox jumped over the lazy dog");
  }
}
