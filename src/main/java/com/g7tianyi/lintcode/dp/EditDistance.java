package com.g7tianyi.lintcode.dp;

import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Sep 25, 2019
 *
 * @link https://www.lintcode.com/problem/edit-distance/description
 */
public class EditDistance {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int minDistance(String word1, String word2) {

      int len1 = word1.length();
      int len2 = word2.length();
      int[] F = new int[len2 + 1];
      for (int j = 0; j <= len2; j++) {
        F[j] = j;
      }

      for (int i = 1, prev; i <= len1; i++) {
        F[0] = i;
        prev = i - 1;
        for (int j = 1, curr; j <= len2; j++) {
          curr = F[j];
          if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
            F[j] = prev;
          } else {
            F[j] = Math.min(Math.min(F[j], F[j - 1]), prev) + 1;
          }
          prev = curr;
        }
      }
      return F[len2];
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {

    log.info(s.minDistance("horse", "ros"));
    log.info(s.minDistance("intention", "execution"));
  }
}
