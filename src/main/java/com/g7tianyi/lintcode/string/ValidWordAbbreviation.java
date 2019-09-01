package com.g7tianyi.lintcode.string;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 1, 2019
 *
 * @link https://www.lintcode.com/problem/valid-word-abbreviation/description
 */
public class ValidWordAbbreviation {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public boolean validWordAbbreviation(String word, String abbr) {

      int abbrLen = abbr.length(), wordLen = word.length();
      int abbrIndex = 0, wordIndex = 0;
      while (abbrIndex < abbrLen) {
        int num = 0;
        while (abbrIndex < abbrLen
            && abbr.charAt(abbrIndex) >= '0'
            && abbr.charAt(abbrIndex) <= '9') {
          if (num == 0 && abbr.charAt(abbrIndex) == '0') { // An easy-to-ignore corner case..
            return false;
          }
          num = num * 10 + (abbr.charAt(abbrIndex) - '0');
          ++abbrIndex;
        }

        wordIndex += num;

        if (abbrIndex == abbrLen) {
          break;
        }

        if (abbr.charAt(abbrIndex) != word.charAt(wordIndex)) {
          return false;
        }

        ++abbrIndex;
        ++wordIndex;
      }

      return wordIndex == wordLen;
    }
  }

  @AllArgsConstructor
  private static class Case {

    private String word;
    private String abbr;
    private boolean result;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c =
        aCase -> {
          boolean result = s.validWordAbbreviation(aCase.word, aCase.abbr);
          log.info("%s VS %s => %s", aCase.word, aCase.abbr, result);
          Assert.assertEquals(aCase.result, result);
        };

    c.accept(new Case("a", "01", false));

    c.accept(new Case("word", "2rd", true));
    c.accept(new Case("word", "w2d", true));
    c.accept(new Case("word", "w1rd", true));
    c.accept(new Case("word", "1o1d", true));
    c.accept(new Case("word", "4", true));
    c.accept(new Case("word", "3", false));

    c.accept(new Case("internationalization", "i19", true));
    c.accept(new Case("internationalization", "i20", false));
    c.accept(new Case("internationalization", "i18n", true));
    c.accept(new Case("internationalization", "i17n", false));
    c.accept(new Case("internationalization", "i12iz4n", true));
    c.accept(new Case("internationalization", "i12i5n", true));
  }
}
