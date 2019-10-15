package com.g7tianyi.lintcode.array.other;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by g7tianyi on Oct 15, 2019
 *
 * @link https://www.lintcode.com/problem/shortest-word-distance/description
 */
public class ShortestWordDistance {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int shortestDistance(String[] words, String word1, String word2) {
      List<Integer> l1 = new ArrayList<>(), l2 = new ArrayList<>();
      for (int i = 0; i < words.length; ++i) {
        if (words[i].equals(word1)) {
          l1.add(i);
        } else if (words[i].equals(word2)) {
          l2.add(i);
        }
      }

      // 您可以假定单词1不等于单词2，而单词1和单词2在列表中都存在

      int result = Integer.MAX_VALUE;
      int i = 0, j = 0;
      int a, b;
      while (i < l1.size() && j < l2.size()) {
        a = l1.get(i);
        b = l2.get(j);
        if (a > b) {
          result = Math.min(result, a - b);
          ++j;
        } else {
          result = Math.min(result, b - a);
          ++i;
        }
      }

      return result;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(
        s.shortestDistance(
            new String[] {"practice", "makes", "perfect", "coding", "makes"},
            "coding",
            "practice"));

    log.info(
        s.shortestDistance(
            new String[] {"practice", "makes", "perfect", "coding", "makes"}, "coding", "makes"));
  }
}
