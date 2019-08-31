package com.g7tianyi.lintcode.math;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by g7tianyi on Aug 31, 2019
 *
 * @link https://www.lintcode.com/problem/count-primes/description
 */
public class CountPrimes {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int countPrimes(int num) {

      if (num <= 2) {
        return 0;
      }

      List<Integer> primes = new ArrayList<>();
      primes.add(2);

      int curr = 3;
      while (curr < num) {
        boolean isPrime = true;
        int opt = 0, prime;
        while (true) {
          prime = primes.get(opt);
          if (prime * prime > curr) {
            break;
          }
          if (curr % prime == 0) {
            isPrime = false;
            break;
          }
          ++opt;
        }
        if (isPrime) {
          primes.add(curr);
        }

        ++curr;
      }

      return primes.size();
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    log.info(s.countPrimes(2));
    log.info(s.countPrimes(3));
    log.info(s.countPrimes(4));
    log.info(s.countPrimes(11));
  }
}
