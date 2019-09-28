package com.g7tianyi.lintcode.math.prime;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by g7tianyi on Aug 29, 2019
 *
 * @link https://www.lintcode.com/problem/kth-prime-number/description
 */
public class KthPrimeNumber {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int kthPrime(int num) {

      List<Integer> primes = new ArrayList<>();
      primes.add(2);
      int curr = 2;
      while (curr != num) {
        ++curr;

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
      }

      return primes.size();
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    log.info(s.kthPrime(2));
    log.info(s.kthPrime(3));
    log.info(s.kthPrime(5));
    log.info(s.kthPrime(11));
  }
}
