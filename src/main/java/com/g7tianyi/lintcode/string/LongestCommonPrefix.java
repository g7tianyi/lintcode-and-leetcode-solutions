package com.g7tianyi.lintcode.string;

import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Nov 13, 2019
 *
 * @link https://www.lintcode.com/problem/longest-common-prefix/description
 */
public class LongestCommonPrefix {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public String longestCommonPrefix(String[] words) {

      if (words == null || words.length == 0) {
        return "";
      }

      int minLength = words[0].length();
      for (int i = 1; i < words.length; ++i) {
        minLength = Math.min(words[i].length(), minLength);
      }

      int i = 0;
      while (i < minLength) {
        char ch = words[0].charAt(i);
        boolean allEquals = true;
        for (int j = 1; j < words.length; ++j) {
          if (words[j].charAt(i) != ch) {
            allEquals = false;
            break;
          }
        }
        if (!allEquals) {
          break;
        }
        ++i;
      }

      return words[0].substring(0, i);
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info("[%s]", s.longestCommonPrefix(new String[] {"ABCD", "ABEF", "ABCEF"}));
    log.info("[%s]", s.longestCommonPrefix(new String[] {"ABCDEFG", "ABCEFG", "ABCEFA"}));
    log.info("[%s]", s.longestCommonPrefix(new String[] {"AB", "AC", "AA"}));
    log.info("[%s]", s.longestCommonPrefix(new String[] {"A", "B", "C"}));
    log.info("[%s]", s.longestCommonPrefix(new String[] {"A", "B", ""}));
  }
}
