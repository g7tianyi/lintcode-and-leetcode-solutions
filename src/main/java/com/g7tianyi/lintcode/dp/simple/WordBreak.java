package com.g7tianyi.lintcode.dp.simple;

import com.g7tianyi.util.Logger;
import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.Set;

/**
 * Created by g7tianyi on Oct 27, 2019
 *
 * @link https://www.lintcode.com/problem/word-break/description
 */
public class WordBreak {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public boolean wordBreak(String s, Set<String> dict) {

      if (s == null) {
        return false;
      }

      if (s.isEmpty()) {
        return true;
      }

      int maxLen = 0;
      for (String word : dict) {
        maxLen = Math.max(maxLen, word.length());
      }

      boolean[] F = new boolean[s.length()];
      for (int i = 0; i < s.length(); ++i) {
        if (i < maxLen && dict.contains(s.substring(0, i + 1))) {
          F[i] = true;
        } else {
          for (int j = i - 1; j >= 0 && i - j <= maxLen; --j) {
            if (F[j] && dict.contains(s.substring(j + 1, i + 1))) {
              F[i] = true;
              break;
            }
          }
        }
      }
      return F[s.length() - 1];
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.wordBreak("lintcode", Sets.newHashSet("lint", "code")));
    log.info(s.wordBreak("a", Sets.newHashSet("a")));
    log.info(s.wordBreak("", Sets.newHashSet()));
  }
}
