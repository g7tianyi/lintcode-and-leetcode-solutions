package com.g7tianyi.lintcode.search;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by g7tianyi on Oct 03, 2019
 *
 * @link https://www.lintcode.com/problem/letter-combinations-of-a-phone-number/description
 */
public class LetterCombinationsOfPhoneNumber {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    private char[][] map = {
      {'a', 'b', 'c'},
      {'d', 'e', 'f'},
      {'g', 'h', 'i'},
      {'j', 'k', 'l'},
      {'m', 'n', 'o'},
      {'p', 'q', 'r', 's'},
      {'t', 'u', 'v'},
      {'w', 'x', 'y', 'z'},
    };

    public List<String> letterCombinations(String digits) {

      List<String> result = new ArrayList<>();

      if (digits == null || digits.length() == 0) {
        return result;
      }

      char[] chars = new char[digits.length()];

      search(digits, 0, chars, result);

      return result;
    }

    private void search(String str, int pos, char[] chars, List<String> result) {
      if (pos == str.length()) {
        result.add(new String(chars));
        return;
      }

      int index = str.charAt(pos) - '2';
      for (int i = 0; i < map[index].length; ++i) {
        chars[pos] = map[index][i];
        search(str, pos + 1, chars, result);
      }
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.letterCombinations("23"));
  }
}
