package com.g7tianyi.lintcode.math;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 28, 2019
 *
 * @link https://www.lintcode.com/problem/sqrtx/description
 */
public class Base7 {

  private static final Logger log = Logger.getInstance();

  public static class Solution {

    public String convertToBase7(int num) {

      StringBuilder sb = new StringBuilder();
      String sign = "";
      if (num < 0) {
        sign = "-";
        num = -num;
      }

      while (num != 0) {
        sb.append(num % 7);
        num /= 7;
      }

      sb.reverse();

      return sign + sb.toString();
    }

    public String convertToBase7Resursively(int num) {
      if (num < 0) {
        return "-" + convertToBase7Resursively(-num);
      }
      if (num < 7) {
        return num + "";
      }
      return convertToBase7Resursively(num / 7) + num % 7;
    }
  }

  @AllArgsConstructor
  private static class TestCase {

    private int number;

    private String expected;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<TestCase> c =
        testCase -> {
          String result = s.convertToBase7(testCase.number);
          log.info("%d => %s", testCase.number, result);
          Assert.assertEquals(result, testCase.expected);
        };

    c.accept(new TestCase(100, "202"));
    c.accept(new TestCase(-7, "-10"));
  }
}
