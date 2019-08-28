package com.g7tianyi.lintcode.bitops;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 28, 2019
 *
 * @link https://www.lintcode.com/problem/counting-bits/description
 */
public class CountingBits {

  private static final Logger log = new Logger();

  public class Solution {

    // 奇数: 减去1成偶数，二进制形式中只有最后一位不同，原来的1变为0
    // 偶数: 减去1成奇数，二进制形式中偶数尾巴上的所有0，直到倒数第一个1为止全部变为1，且倒数第一个1变为0
    // (5) 101 => (4) 100
    // (176) 10110000 => (133) 10101111
    // 减1前后的两个数字求与操作，新的数字总是会减少一个1
    //
    // 负数的二进制表示：
    // 1.取反：对这个负数的绝对值按位取反，得到的叫做反码
    // 2.加1：取反后，对这个二进制数加1，得到的叫做补码
    public int countOnes(int num) {
      // write your code here

      int value = num;
      if (value < 0) {
        value = Math.abs(value + 1);
      }

      int result = 0;
      while (value != 0) {
        value &= (value - 1);
        ++result;
      }

      if (num >= 0) {
        return result;
      } else {
        return 32 - result;
      }
    }
  }

  @AllArgsConstructor
  private static class Case {

    private int num;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c = aCase -> log.info(s.countOnes(aCase.num));

    c.accept(new Case(-8));
    c.accept(new Case(32));
    c.accept(new Case(5));
  }
}
