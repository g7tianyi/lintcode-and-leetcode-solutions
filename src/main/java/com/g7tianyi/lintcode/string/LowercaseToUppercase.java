package com.g7tianyi.lintcode.string;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 1, 2019
 *
 * @link https://www.lintcode.com/problem/lowercase-to-uppercase/description
 */
public class LowercaseToUppercase {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public char lowercaseToUppercase(char ch) {
      return (char) ('A' + ch - 'a');
    }
  }

  @AllArgsConstructor
  private static class Case {

    private char chi;
    private char cho;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c =
        aCase -> {
          char cho = s.lowercaseToUppercase(aCase.chi);
          log.info("%c => %c", aCase.chi, cho);
          log.info();
          Assert.assertEquals(aCase.cho, cho);
        };

    c.accept(new Case('a', 'A'));
    c.accept(new Case('q', 'Q'));
    c.accept(new Case('z', 'Z'));

  }
}
