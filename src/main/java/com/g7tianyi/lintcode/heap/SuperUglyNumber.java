package com.g7tianyi.lintcode.heap;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Created by g7tianyi on Oct 24, 2019
 *
 * @link https://www.lintcode.com/problem/super-ugly-number/description
 */
public class SuperUglyNumber {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int nthSuperUglyNumber(int n, int[] primes) {
      if (n == 1) {
        return 1;
      }

      Arrays.sort(primes);

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
        for (long multiplier : primes) {
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

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.nthSuperUglyNumber(6, com.g7tianyi.common.Arrays.from(2, 7, 13, 19)));
    log.info(s.nthSuperUglyNumber(11, com.g7tianyi.common.Arrays.from(2, 3, 5)));
  }
}
