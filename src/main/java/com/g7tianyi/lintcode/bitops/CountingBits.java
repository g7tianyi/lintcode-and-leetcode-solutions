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

    public int[] countBits(int num) {
      // write your code here
      int[] result = new int[num + 1];
      result[0] = 0;
      for (int i = 1, j; i <= num; i++) {
        if ((i & 1) == 1) { // 当前数字是奇数，是由前面一个偶数+1得到，偶数总是将最后一位0变为1
          result[i] = result[i - 1] + 1;
        } else {
          // 举个例子：
          // i-1          => 1 01111
          // i            => 1 10000
          // k = i ^ i-1  => 0 11111
          // j = k & i-1  => 0 01111
          j = ((i - 1) ^ i) & (i - 1);
          result[i] = result[i - 1] - result[j] + 1;
        }
      }
      return result;
    }
  }

  @AllArgsConstructor
  private static class Case {

    private int num;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c = aCase -> log.info(s.countBits(aCase.num));

    c.accept(new Case(5));
    c.accept(new Case(3));
  }
}
