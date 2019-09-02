package com.g7tianyi.lintcode.math.decimal;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 02, 2019
 *
 * @link https://www.lintcode.com/problem/convert-a-number-to-hexadecimal/description
 */
public class ConvertNumberToHexadecimal {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public String toHex(int num) {
      if (num == 0) {
        return "0";
      }

      char[] hex = "0123456789abcdef".toCharArray();
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < 32 && num != 0; i += 4, num >>= 4) {
        sb.append(hex[num & 0xF]);
      }
      sb.reverse();
      return sb.toString();
    }
  }

  @AllArgsConstructor
  private class Case {
    int num;
    String expect;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c =
        aCase -> {
          String result = s.toHex(aCase.num);
          log.info("%sd => %s", aCase.num, result);
          Assert.assertEquals(aCase.expect, result);
        };

    c.accept(new Case(26, "1a"));
    c.accept(new Case(-1, "ffffffff"));
  }
}
