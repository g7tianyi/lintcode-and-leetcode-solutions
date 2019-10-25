package com.g7tianyi.lintcode.array.twopointers;

import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Oct 25, 2019
 *
 * @link https://www.lintcode.com/problem/minimum-window-substring/description
 */
public class MinimumWindowSubstring {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    class Info {
      int count;
      int[] hash;

      public Info() {
        count = 0;
        hash = new int[256];
      }

      void set(char ch) {
        ++hash[ch];
        ++count;
      }

      void unset(char ch) {
        --hash[ch];
        --count;
      }

      boolean contains(Info info) {
        if (count < info.count) {
          return false;
        }
        for (int i = 0; i < 256; ++i) {
          if (hash[i] < info.hash[i]) {
            return false;
          }
        }
        return true;
      }
    }

    public String minWindow(String source, String target) {

      Info targetInfo = new Info();
      int targetLength = target.length();
      for (int i = 0; i < targetLength; ++i) {
        targetInfo.set(target.charAt(i));
      }

      int sourceLength = source.length();
      int ri = -1, rj = -1;
      int i = 0, j = 0;
      Info currInfo = new Info();

      while (i < sourceLength) {

        while (j < sourceLength && !currInfo.contains(targetInfo)) {
          currInfo.set(source.charAt(j++));
        }

        int oi = i;
        while (currInfo.contains(targetInfo)) {
          if (ri < 0 || rj - ri > j - i) {
            rj = j;
            ri = i;
          }
          currInfo.unset(source.charAt(i++));
        }

        if (i == oi) {
          ++i;
        }
      }

      if (ri < 0 || rj < 0) {
        return "";
      }
      return source.substring(ri, rj);
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info("[%s]", s.minWindow("abc", "ac"));
    log.info("[%s]", s.minWindow("adobecodebanc", "abc"));
    log.info("[%s]", s.minWindow("abc", "aa"));

    log.info("[%s]", s.minWindow("absdfaabab", "adb"));
  }
}
