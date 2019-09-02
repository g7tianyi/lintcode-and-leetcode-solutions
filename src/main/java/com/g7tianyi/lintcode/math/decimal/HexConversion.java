package com.g7tianyi.lintcode.math.decimal;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 29, 2019
 *
 * @link https://www.lintcode.com/problem/hex-conversion/description
 */
public class HexConversion {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public String hexConversion(int num, int k) {
      // write your code here

      if (num == 0) {
        return "0";
      }

      char[] map = {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
      };

      // a^b 表示a的b次方
      // num = An-1 * k^(n-1) + An-2 * k^(n-2) + ... + A1 * k^1 + A0 * k^0
      StringBuilder sb = new StringBuilder();
      int remain;
      while (num != 0) {
        remain = num % k;
        sb.append(map[remain]);
        num /= k;
      }

      sb.reverse();

      return sb.toString();
    }
  }

  @AllArgsConstructor
  private static class Case {

    private int num;
    private int k;
    private String result;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c =
        aCase -> {
          String result = s.hexConversion(aCase.num, aCase.k);
          log.info("(%d, %d) => %s", aCase.num, aCase.k, result);
          Assert.assertEquals(result, aCase.result);
        };

    c.accept(new Case(5, 2, "101"));
    c.accept(new Case(30, 16, "1E"));
    c.accept(new Case(1024, 16, "400"));
    c.accept(new Case(2019, 16, "7E3"));
  }
}
