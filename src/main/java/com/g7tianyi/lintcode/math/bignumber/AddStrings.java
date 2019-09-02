package com.g7tianyi.lintcode.math;

import com.g7tianyi.common.Strings;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 28, 2019
 *
 * @link https://www.lintcode.com/problem/add-strings/description
 */
public class AddStrings {

  private static final Logger log = Logger.getInstance();

  public static class Solution {

    public String addStrings(String num1, String num2) {

      num1 = trimToEmpty(num1);
      num2 = trimToEmpty(num2);

      if (num1.equals("") || num2.equals("")) {
        return "";
      }

      StringBuilder sb = new StringBuilder();

      int i = num1.length() - 1, j = num2.length() - 1;
      int carry = 0, sum;
      while (i >= 0 && j >= 0) {
        sum = (num1.charAt(i) - 48) + ((int) num2.charAt(j) - 48) + carry;
        if (sum >= 10) {
          sb.append(sum % 10);
          carry = 1;
        } else {
          sb.append(sum);
          carry = 0;
        }
        --i;
        --j;
      }

      while (i >= 0) {
        sum = ((int) num1.charAt(i) - 48) + carry;
        if (sum >= 10) {
          sb.append(sum % 10);
          carry = 1;
        } else {
          sb.append(sum);
          carry = 0;
        }
        --i;
      }

      while (j >= 0) {
        sum = ((int) num2.charAt(j) - 48) + carry;
        if (sum >= 10) {
          sb.append(sum % 10);
          carry = 1;
        } else {
          sb.append(sum);
          carry = 0;
        }
        --j;
      }

      if (carry > 0) {
        sb.append(carry);
      }

      sb.reverse();
      return sb.toString();
    }

    private static String trimToEmpty(String s) {
      return s != null ? s.trim() : "";
    }
  }

  @AllArgsConstructor
  private static class Case {
    private String s1;
    private String s2;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c =
        aCase -> {
          String r = s.addStrings(aCase.s1, aCase.s2);
          String s1 = Strings.prePad(aCase.s1, r.length(), ' ');
          String s2 = Strings.prePad(aCase.s2, r.length(), ' ');
          log.info(s1);
          log.info(s2);
          log.info(r);
          log.info();
        };

    c.accept(new Case("123", "45"));
    c.accept(new Case("3215", "1561"));
    c.accept(new Case("999999999999999", "1"));
  }
}
