package com.g7tianyi.lintcode.string.match;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 23, 2019
 *
 * @link https://www.lintcode.com/problem/string-to-integer-atoi/description
 */
public class StringToIntegerAtoi {

  private static final Logger log = Logger.getInstance();

  public class Solution {
    public int atoi(String s) {
      if (s == null) {
        return 0;
      }
      s = s.trim();
      if (s.length() == 0) {
        return 0;
      }

      char[] chars = s.toCharArray();
      boolean negative = false;
      if (chars[0] == '-') {
        negative = true;
      }

      long num = 0;
      int i = chars[0] == '-' || chars[0] == '+' ? 1 : 0;
      while (i < chars.length && chars[i] >= '0' && chars[i] <= '9') {
        num = num * 10 + (chars[i] - '0');
        if (num > Integer.MAX_VALUE) {
          break;
        }
        ++i;
      }

      if (negative) {
        num = -num;
      }

      if (num >= Integer.MAX_VALUE) {
        return Integer.MAX_VALUE;
      }
      if (num < Integer.MIN_VALUE) {
        return Integer.MIN_VALUE;
      }
      return (int) num;
    }
  }

  private final Solution s = new Solution();

  private final Consumer<String> c = str -> log.info("%s => %s", str, s.atoi(str));

  @Test
  public void test() {
    c.accept("18");
    c.accept("123123123123123");
    c.accept("-156465313156465");
    c.accept("2147483647");
    c.accept("2147483648");
    c.accept("-2147483647");
    c.accept("-2147483648");
    c.accept("-2147483649");
    c.accept("-156465313");
    c.accept("+156465313");
    c.accept("1.0");
    c.accept("1");
    c.accept("");
    c.accept(null);
  }
}
