package com.g7tianyi.lintcode.bitops;

import com.g7tianyi.common.Numbers;
import com.g7tianyi.common.Strings;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 28, 2019
 *
 * @link https://www.lintcode.com/problem/flip-bits/description
 */
public class FlipBits {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int bitSwapRequired(int a, int b) {
      int mask = 1, n = 0;
      int result = 0;
      while (n++ < 32) {
        if (((a & mask) ^ (b & mask)) != 0) {
          ++result;
        }
        mask <<= 1;
      }
      return result;
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

    Consumer<Case> c = aCase -> {
      int len = Math.max(aCase.s1.length(), aCase.s2.length());
      log.info(Strings.prePad(aCase.s1, len, ' '));
      log.info(Strings.prePad(aCase.s2, len, ' '));
      int num1 = Numbers.fromBinaryString(aCase.s1);
      int num2 = Numbers.fromBinaryString(aCase.s2);
      log.info(s.bitSwapRequired(num1, num2));
      log.info();
    };

   c.accept(new Case("11111", "01110"));
   c.accept(new Case("001", "111"));
   c.accept(new Case("1010111", "1011"));
   c.accept(new Case("0", "101010111"));
  }
}
