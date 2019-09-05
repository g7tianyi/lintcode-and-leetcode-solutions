package com.g7tianyi.lintcode.heap;

import com.g7tianyi.util.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 05, 2019
 *
 * @link https://www.lintcode.com/problem/ugly-number-ii/description
 */
public class UglyNumber2 {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    private final long[] multipliers = {2, 3, 5};

    public int nthUglyNumber(int n) {
      if (n == 1) {
        return 1;
      }

      PriorityQueue<Long> pQueue = new PriorityQueue<>();
      Set<Long> set = new HashSet<>();
      pQueue.offer(1L);
      set.add(1L);

      int count = 1;
      long num;
      while (count < n) {
        ++count;
        Long min = pQueue.poll();
        set.remove(min);
        for (long multiplier : multipliers) {
          num = min * multiplier;
          if (!set.contains(num)) {
            pQueue.offer(num);
            set.add(num);
          }
        }
      }

      return pQueue.peek().intValue();
    }
  }

  public class TLESolution {

    public int nthUglyNumber(int n) {

      if (n == 1) {
        return 1;
      }

      int num = 2, count = 1;
      while (count < n) {
        if (isUgly(num)) {
          ++count;
          if (count == n) {
            return num;
          }
        }
        ++num;
      }
      return 1;
    }

    private boolean isUgly(int num) {
      if (num == 0) {
        return false;
      }

      int[] factors = {5 * 3 * 2, 5 * 3, 5 * 2, 3 * 2, 5, 3, 2};
      for (int factor : factors) {
        while (num % factor == 0) {
          num /= factor;
        }
      }
      return num == 1;
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Integer> c =
        num -> {
          int result = s.nthUglyNumber(num);
          int expect = new TLESolution().nthUglyNumber(num);
          log.info("%d => %d VS %d", num, result, expect);
          Assert.assertEquals(expect, result);
        };

    for (int i = 1; i <= 10; i++) {
      c.accept(i);
    }

    c.accept(1000);
  }
}
