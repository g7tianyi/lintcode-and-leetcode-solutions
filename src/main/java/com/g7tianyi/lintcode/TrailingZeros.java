package com.g7tianyi.lintcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by g7tianyi on Aug 23, 2019
 *
 * <p>https://www.lintcode.com/problem/trailing-zeros/description
 */
public class TrailingZeros {

  public class Solution {

    private List<Long> POWERS = new ArrayList<Long>();

    public long trailingZeros(long num) {
      if (num < 5) {
        return 0;
      }

      init();

      int index = 0;
      long power = 1;
      while (POWERS.get(index) <= num) {
        index++;
        power *= 5;
      }

      long result = 0;
      long fives = index;
      long prev = 0;
      while (power > 1) {

        long curr = num / power;

        result += (curr - prev) * fives;
        fives--;

        prev = curr;
        power /= 5;
      }

      return result;
    }

    private void init() {

      POWERS.clear();

      long num = 5;
      while (num > 0 && num < Long.MAX_VALUE) {
        POWERS.add(num);
        num *= 5;
      }
    }
  }

  public class SolutionTLE {

    public long trailingZeros(long n) {
      if (n < 5) {
        return 0;
      }

      long result = 0;
      for (long a = 5; a <= n; a += 5) {
        result += countFactor5(a);
      }
      return result;
    }

    // TODO: 这里的计算可以缓存起来
    private long countFactor5(long n) {
      long count = 0;
      while (n % 5 == 0) {
        count++;
        n /= 5;
      }
      return count;
    }
  }

  @Test
  public void test() {
    Solution s = new Solution();

    Assert.assertEquals(0, s.trailingZeros(-1));
    Assert.assertEquals(0, s.trailingZeros(4));
    Assert.assertEquals(1, s.trailingZeros(5));
    Assert.assertEquals(2, s.trailingZeros(11));
    Assert.assertEquals(7, s.trailingZeros(31));
    Assert.assertEquals(1388887499996L, s.trailingZeros(5555550000000L));
  }
}
