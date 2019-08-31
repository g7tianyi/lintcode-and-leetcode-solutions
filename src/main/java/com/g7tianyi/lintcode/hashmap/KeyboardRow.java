package com.g7tianyi.lintcode.hashmap;

import com.g7tianyi.common.Strings;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 31, 2019
 *
 * @link https://www.lintcode.com/problem/keyboard-row/description
 */
public class KeyboardRow {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public String[] findWords(String[] words) {

      int[] tag = new int[26];
      tag['Q' - 'A'] = 1;
      tag['W' - 'A'] = 1;
      tag['E' - 'A'] = 1;
      tag['R' - 'A'] = 1;
      tag['T' - 'A'] = 1;
      tag['Y' - 'A'] = 1;
      tag['U' - 'A'] = 1;
      tag['I' - 'A'] = 1;
      tag['O' - 'A'] = 1;
      tag['P' - 'A'] = 1;

      tag[0] = 2; // 'A' - 'A'
      tag['S' - 'A'] = 2;
      tag['D' - 'A'] = 2;
      tag['F' - 'A'] = 2;
      tag['G' - 'A'] = 2;
      tag['H' - 'A'] = 2;
      tag['J' - 'A'] = 2;
      tag['K' - 'A'] = 2;
      tag['L' - 'A'] = 2;

      tag['Z' - 'A'] = 4;
      tag['X' - 'A'] = 4;
      tag['C' - 'A'] = 4;
      tag['V' - 'A'] = 4;
      tag['B' - 'A'] = 4;
      tag['N' - 'A'] = 4;
      tag['M' - 'A'] = 4;

      int num;
      char[] chars;
      List<String> result = new ArrayList<>();
      for (String word : words) {
        chars = word.toUpperCase().toCharArray();
        num = 0;
        for (char ch : chars) {
          num |= tag[ch - 'A'];
        }
        if ((num & (num - 1)) == 0) {
          result.add(word);
        }
      }

      return result.toArray(new String[0]);
    }
  }

  @AllArgsConstructor
  private static class Case {

    private String[] words;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c =
        aCase -> {
          log.info(Strings.format(aCase.words));
          log.info(Strings.format(s.findWords(aCase.words)));
          log.info();
        };

    c.accept(new Case(new String[] {"Hello", "Alaska", "Dad", "Peace"}));
  }
}
