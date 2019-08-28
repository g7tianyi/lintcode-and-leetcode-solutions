package com.g7tianyi.lintcode.string;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 29, 2019
 *
 * @link https://www.lintcode.com/problem/rearrange-a-string-with-integers/description
 */
public class RearrangeAStringWithIntegers {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public String rearrange(String s) {

      if (s == null || s.length() == 0) {
        return s;
      }

      int[] pos = new int[26];
      for (int i = 0; i < 26; i++) {
        pos[i] = 0;
      }

      char ch;
      int sum = 0;
      for (int i = 0; i < s.length(); ++i) {
        ch = s.charAt(i);
        if (ch >= '0' && ch <= '9') {
          sum += ch - 48;
        } else {
          ++pos[ch - 'A'];
        }
      }

      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < 26; ++i) {
        for (int j = 0; j < pos[i]; ++j) {
          sb.append((char) ('A' + i));
        }
      }
      sb.append(sum);

      return sb.toString();
    }
  }

  @AllArgsConstructor
  private static class Case {

    private String string;
    private String result;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c =
        aCase -> {
          log.info("%s", aCase.string);
          String result = s.rearrange(aCase.string);
          log.info("%s", result);
          log.info();
          Assert.assertEquals(result, aCase.result);
        };

    c.accept(new Case("AC2BEW3", "ABCEW5"));
//    c.accept(new Case("AC2BE1W32", "ABCEW35"));
    c.accept(new Case("AC2BE1W32", "ABCEW8"));
    c.accept(new Case("ADJILS1D45S", "ADDIJLSS10"));
  }
}
