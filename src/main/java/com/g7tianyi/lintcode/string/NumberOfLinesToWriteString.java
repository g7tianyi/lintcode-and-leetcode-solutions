package com.g7tianyi.lintcode.string;

import com.g7tianyi.common.Strings;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 04, 2019
 *
 * @link https://www.lintcode.com/problem/number-of-lines-to-write-string/description
 */
public class NumberOfLinesToWriteString {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int[] numberOfLines(int[] widths, String S) {

      int lines = 0;
      int width = 0, curr;
      for (int i = 0; i < S.length(); ++i) {
        curr = widths[S.charAt(i) - 'a'];
        if (width + curr == 100) {
          width = 0;
          ++lines;
        } else if (width + curr < 100) {
          width += curr;
        } else {
          width = curr;
          ++lines;
        }
      }
      if (width > 0) {
        ++lines;
      }
      return new int[] {lines, width};
    }
  }

  @AllArgsConstructor
  private class Case {
    int[] widths;
    String S;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c = aCase -> log.info(Strings.format(s.numberOfLines(aCase.widths, aCase.S)));

    c.accept(
        new Case(
            new int[] {
              4, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10,
              10, 10, 10, 10
            },
            "bbbcccdddaaa"));

    c.accept(
        new Case(
            new int[] {
              10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10,
              10, 10, 10, 10, 10
            },
            "abcdefghijklmnopqrstuvwxyz"));
  }
}
