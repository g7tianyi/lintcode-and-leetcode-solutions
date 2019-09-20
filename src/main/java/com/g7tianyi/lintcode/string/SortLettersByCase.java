package com.g7tianyi.lintcode.string;

import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Sep 20, 2019
 *
 * @link https://www.lintcode.com/problem/sort-letters-by-case/description
 */
public class SortLettersByCase {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public void sortLetters(char[] chars) {
      int i = 0, j = chars.length - 1;
      while (i < j) {
        while (i < j && chars[i] >= 'a' && chars[i] <= 'z') {
          ++i;
        }
        if (i == j) {
          break;
        }

        while (j > i && chars[j] >= 'A' && chars[j] <= 'Z') {
          --j;
        }
        if (j == i) {
          break;
        }

        char ch = chars[i];
        chars[i] = chars[j];
        chars[j] = ch;
        ++i;
        --j;
      }
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    char[] chars;
    chars = "abAcD".toCharArray();
    log.info(chars);
    s.sortLetters(chars);
    log.info(chars);

    s.sortLetters("".toCharArray());
  }
}
