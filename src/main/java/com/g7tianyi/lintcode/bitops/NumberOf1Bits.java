package com.g7tianyi.lintcode.bitops;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 01, 2019
 *
 * @link https://www.lintcode.com/problem/number-of-1-bits/description
 */
public class NumberOf1Bits {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    // 奇数: 减去1成偶数，二进制形式中只有最后一位不同，原来的1变为0
    // 偶数: 减去1成奇数，二进制形式中偶数尾巴上的所有0，直到倒数第一个1为止全部变为1，且倒数第一个1变为0
    // (5) 101 => (4) 100, 101 & 100 = 100
    // (176) 10110000 => (175) 10101111, 10110000 & 10101111 = 10100000
    // 减1前后的两个数字求与操作，新的数字总是会减少一个1
    public int hammingWeight(int num) {
      int result = 0;
      while (num != 0) {
        num &= (num - 1);
        ++result;
      }
      return result;
    }
  }

  @AllArgsConstructor
  private class Case {
    private int num;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c = aCase -> log.info("%d => %d", aCase.num, s.hammingWeight(aCase.num));

    c.accept(new Case(11));
    c.accept(new Case(7));
  }
}
