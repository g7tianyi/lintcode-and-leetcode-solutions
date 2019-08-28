package com.g7tianyi.leetcode.string;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 28, 2019
 *
 * @link https://leetcode-cn.com/classic/problems/reverse-string/description/
 */
public class ReverseString {

  private static final Logger log = Logger.getInstance();

  class Solution {

    public void reverseString(char[] s) {

      if (s == null || s.length == 0) {
        return;
      }

      int i = 0, j = s.length - 1;
      char ch;
      while (i < j) {
        ch = s[i];
        s[i] = s[j];
        s[j] = ch;
        ++i;
        --j;
      }
    }
  }

  @AllArgsConstructor
  public static class Case {

    private char[] s;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> runner =
        aCase -> {
          log.info(aCase.s);
          s.reverseString(aCase.s);
          log.info(aCase.s);
          log.info();
        };

    runner.accept(new Case(Arrays.from('h', 'e', 'l', 'l', 'o')));
    runner.accept(new Case(Arrays.from('H', 'a', 'n', 'n', 'a', 'h')));
  }
}
