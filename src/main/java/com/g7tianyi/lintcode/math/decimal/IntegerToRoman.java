package com.g7tianyi.lintcode.math.decimal;

import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Nov 03, 2019
 *
 * @link https://www.lintcode.com/problem/integer-to-roman/description
 */
public class IntegerToRoman {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    private final String[][] elems =
        new String[][] {
          {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"},
          {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"},
          {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"},
          {"", "M", "MM", "MMM", "", "", "", "", "", ""},
        };

    public String intToRoman(int n) {
      String s = "";
      s += elems[3][n / 1000 % 10];
      s += elems[2][n / 100 % 10];
      s += elems[1][n / 10 % 10];
      s += elems[0][n % 10];
      return s;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.intToRoman(1));
    log.info(s.intToRoman(99));
    log.info(s.intToRoman(200));
    log.info(s.intToRoman(128));
    log.info(s.intToRoman(3999));
  }
}
