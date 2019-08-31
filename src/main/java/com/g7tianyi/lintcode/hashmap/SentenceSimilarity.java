package com.g7tianyi.lintcode.hashmap;

import com.g7tianyi.util.Logger;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 30, 2019
 *
 * @link https://www.lintcode.com/problem/sentence-similarity/description
 */
public class SentenceSimilarity {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public boolean isSentenceSimilarity(
        String[] words1, String[] words2, List<List<String>> pairs) {

      if (words1.length != words2.length) {
        return false;
      }

      // 一开始没注意到一个单词会有多个相似的单词，囧
      Map<String, Set<String>> map = new HashMap<>();
      for (List<String> pair : pairs) {
        put(map, pair.get(0), pair.get(1));
      }

      Set<String> emptySet = new HashSet<>();
      for (int i = 0; i < words1.length; ++i) {
        String word1 = words1[i], word2 = words2[i];
        if (!word1.equals(word2) && !map.getOrDefault(word1, emptySet).contains(word2)) {
          return false;
        }
      }

      return true;
    }

    private void put(Map<String, Set<String>> map, String word1, String word2) {

      Set<String> set1 = map.getOrDefault(word1, new HashSet<>());
      set1.add(word2);
      map.put(word1, set1);

      Set<String> set2 = map.getOrDefault(word2, new HashSet<>());
      set2.add(word1);
      map.put(word2, set2);
    }
  }

  @AllArgsConstructor
  private static class Case {
    private String[] words1;
    private String[] words2;
    private List<List<String>> pairs;
    private boolean result;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c =
        aCase ->
            Assert.assertEquals(
                s.isSentenceSimilarity(aCase.words1, aCase.words2, aCase.pairs), aCase.result);

    c.accept(
        new Case(
            new String[] {"great", "acting", "skills"},
            new String[] {"fine", "drama", "talent"},
            Lists.newArrayList(
                Lists.newArrayList("great", "fine"),
                Lists.newArrayList("drama", "acting"),
                Lists.newArrayList("skills", "talent")),
            true));

    c.accept(
        new Case(
            new String[] {"fine", "skills", "acting"},
            new String[] {"fine", "drama", "talent"},
            Lists.newArrayList(
                Lists.newArrayList("great", "fine"),
                Lists.newArrayList("drama", "acting"),
                Lists.newArrayList("skills", "talent")),
            false));

    c.accept(
        new Case(
            new String[] {"A", "N", "C", "R", "D", "B", "Q", "T"},
            new String[] {"M", "A", "C", "D", "R", "P", "C", "F"},
            Lists.newArrayList(
                Lists.newArrayList("A", "M"),
                Lists.newArrayList("A", "N"),
                Lists.newArrayList("A", "O"),
                Lists.newArrayList("B", "P"),
                Lists.newArrayList("C", "Q"),
                Lists.newArrayList("D", "R"),
                Lists.newArrayList("E", "S"),
                Lists.newArrayList("F", "T")),
            true));
  }
}
