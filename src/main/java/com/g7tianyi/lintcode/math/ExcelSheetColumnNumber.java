package com.g7tianyi.lintcode.math;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 02, 2019
 *
 * @link https://www.lintcode.com/problem/excel-sheet-column-number/description
 */
public class ExcelSheetColumnNumber {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int titleToNumber(String s) {
      int result = 0;
      for (int i = 0; i < s.length(); ++i) {
        result = result * 26 + (s.charAt(i) - 'A' + 1);
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
          int result = s.titleToNumber(aCase.s);
          log.info("%s => %d", aCase.s, result);
          Assert.assertEquals(aCase.expect, result);
        };

    c.accept(new Case("A", 1));
    c.accept(new Case("Z", 26));
    c.accept(new Case("AB", 28));
  }
}
