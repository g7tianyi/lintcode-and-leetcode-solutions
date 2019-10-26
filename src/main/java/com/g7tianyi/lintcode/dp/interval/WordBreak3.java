package com.g7tianyi.lintcode.dp.interval;

import com.g7tianyi.util.Logger;
import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by g7tianyi on Oct 26, 2019
 *
 * @link https://www.lintcode.com/problem/word-break-iii/description
 */
public class WordBreak3 {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int wordBreak3(String s, Set<String> dict) {
      Set<String> set = new HashSet<>();
      for (String str : dict) {
        set.add(str.toLowerCase());
      }

      s = s.toLowerCase();
      int len = s.length();
      int[][] F = new int[len][len];
      for (int i = 0; i < len; ++i) {
        for (int j = i; j < len; j++) {
          if (set.contains(s.substring(i, j + 1))) {
            F[i][j] = 1;
          }
        }
      }

      for (int i = 0; i < len; i++) {
        for (int j = i; j < len; j++) {
          for (int k = i; k < j; k++) {
            F[i][j] += (F[i][k] * F[k + 1][j]);
          }
        }
      }

      return F[0][len - 1];
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.wordBreak3("a", new HashSet<>()));
    log.info(
        s.wordBreak3(
            "CatMat", Sets.newHashSet("Cat", "Mat", "Ca", "tM", "at", "C", "Dog", "og", "Do")));
  }
}
