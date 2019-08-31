package com.g7tianyi.lintcode.bitops;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 31, 2019
 *
 * @link https://www.lintcode.com/problem/number-complement/description
 */
public class NumberComplement {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int findComplement(int num) {
      // num  :  00001101
      // mask :  11111111
      // mask :  11110000
      // ~mask:  00001111
      // ~num :  11110010
      int mask = Integer.MAX_VALUE;
      while ((mask & num) != 0) {
        mask <<= 1;
      }
      return (~mask) & (~num);
    }
  }

  @AllArgsConstructor
  private static class Case {

    private int num;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c = aCase -> log.info(s.findComplement(aCase.num));

    c.accept(new Case(5));
    c.accept(new Case(1));
  }
}
