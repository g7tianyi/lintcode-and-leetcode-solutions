package com.g7tianyi.lintcode.string;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 02, 2019
 *
 * @link https://www.lintcode.com/problem/number-of-segments-in-a-string/description
 */
public class NumberOfSegmentsInString {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int countSegments(String s) {
      // The number of words, equals to the number of boundaries, i.e., an space
      //
      // ...as asa
      //      ||
      //    --  --
      //   |      |
      // i - 1    i
      //
      int result = 0;
      char[] chars = s.toCharArray();
      for (int i = 0; i < chars.length; ++i) {
        if (chars[i] != ' ' && (i == 0 || chars[i - 1] == ' ')) {
          ++result;
        }
      }
      return result;
    }
  }

  @AllArgsConstructor
  private class Case {
    String s;
    int expect;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c =
        aCase -> {
          int result = s.countSegments(aCase.s);
          log.info("%s => %d", aCase.s, result);
          Assert.assertEquals(aCase.expect, result);
        };

    c.accept(new Case("Hello, my name is John", 5));
  }
}
