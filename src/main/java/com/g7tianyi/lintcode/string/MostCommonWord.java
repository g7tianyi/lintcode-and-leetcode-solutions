package com.g7tianyi.lintcode.string;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 03, 2019
 *
 * @link https://www.lintcode.com/problem/most-common-word/description
 */
public class MostCommonWord {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public String mostCommonWord(String paragraph, String[] banned) {

      Set<String> bannedWords = new HashSet<>();
      Collections.addAll(bannedWords, banned);

      Map<String, Integer> map = new HashMap<>();

      String[] words = paragraph.split("\\s+");
      for (String word : words) {
        word = word.replaceAll("[!?',;.]+", "").toLowerCase();
        if (!bannedWords.contains(word)) {
          map.put(word, map.getOrDefault(word, 0) + 1);
        }
      }

      String result = null;
      Integer maxOccurrence = null;
      for (Map.Entry<String, Integer> entry : map.entrySet()) {
        if (maxOccurrence == null || entry.getValue() > maxOccurrence) {
          maxOccurrence = entry.getValue();
          result = entry.getKey();
        }
      }
      return result;
    }
  }

  @AllArgsConstructor
  private class Case {
    String paragraph;
    String[] banned;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c = aCase -> log.info(s.mostCommonWord(aCase.paragraph, aCase.banned));

    c.accept(
        new Case("Bob hit a ball, the hit BALL flew far after it was hit.", new String[] {"hit"}));

    c.accept(new Case("a a a b b c c d", new String[] {"a", "b"}));
  }
}
