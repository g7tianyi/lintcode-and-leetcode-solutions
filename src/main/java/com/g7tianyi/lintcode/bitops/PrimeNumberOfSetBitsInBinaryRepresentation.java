package com.g7tianyi.lintcode.bitops;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 01, 2019
 *
 * @link https://www.lintcode.com/problem/number-of-1-bits/description
 */
public class PrimeNumberOfSetBitsInBinaryRepresentation {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int countPrimeSetBits(int L, int R) {

      boolean[] pos = new boolean[33];
      for (int i = 0; i < 33; ++i) {
        pos[i] = false;
      }
      pos[2] = true;

      List<Integer> primes = new ArrayList<>();
      primes.add(2);

      int curr = 3;
      while (curr < 33) {
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
          pos[curr] = true;
          primes.add(curr);
        }
        ++curr;
      }

      int result = 0;
      for (int i = L; i <= R; ++i) {
        int num = numOfBitOnes(i);
        if (pos[num]) {
          ++result;
        }
      }
      return result;
    }

    private int numOfBitOnes(int num) {
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
    private int L;
    private int R;
    private int expected;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c =
        aCase -> {
          int result = s.countPrimeSetBits(aCase.L, aCase.R);
          log.info("[%d, %d] => %d", aCase.L, aCase.R, result);
          Assert.assertEquals(aCase.expected, result);
        };

    c.accept(new Case(6, 10, 4));
    c.accept(new Case(10, 15, 5));
  }
}
