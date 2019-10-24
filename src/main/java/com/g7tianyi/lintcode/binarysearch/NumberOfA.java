package com.g7tianyi.lintcode.binarysearch;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.List;

/**
 * Created by g7tianyi on Oct 24, 2019
 *
 * @link https://www.lintcode.com/problem/number-of-a/description
 */
public class NumberOfA {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int countA(String s) {
      int i = 0, j = s.length() - 1, m;
      int a = -1, b = -1;
      char ch;
      while ((a < 0 || b < 0) && i <= j) {
        m = i + ((j - i) >> 1);
        ch = s.charAt(m);
        if (ch == 'B') {
          i = m + 1;
        } else if (ch == 'D' || ch == 'C') {
          j = m - 1;
        } else {
          if (s.charAt(i) == 'B') {
            ++i;
          } else {
            a = i;
          }

          if ( s.charAt(j) == 'C'  || s.charAt(j) == 'D') {
            --j;
          } else {
            b = j;
          }
        }
      }

      if (a == -1 || b == -1) {
        return 0;
      } else {
        return b - a + 1;
      }
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {

    log.info(s.countA("BBAACCDDD"));
    log.info(s.countA("BBCDD"));
    log.info(s.countA("BBBADDDDDD"));
    log.info(s.countA("AAAACCCC"));
    log.info(s.countA("AAA"));
    log.info(s.countA("A"));

  }
}