package com.g7tianyi.lintcode.math.prime;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 28, 2019
 *
 * @link https://www.lintcode.com/problem/check-sum-of-k-primes/description
 */
public class CheckSumOfKPrimes {

  private static final Logger log = Logger.getInstance();

  // 牛逼的哥德巴赫猜想:
  // a.任一大于2的偶数都可写成两个质数之和.
  // b.任一大于5的奇数都可写成三个素数之和.
  public class Solution {

    public boolean isSumOfKPrimes(int n, int k) {
      if (k < 1 || n < 2 * k) {
        return false;
      }

      if (k == 1) {
        if (n == 2) {
          return true;
        }
        for (int i = 3; i * i <= n; i += 2) {
          if (n % i == 0) {
            return false;
          }
        }
        return false;
      }

      if (n % 2 == 0) {
        if (k % 2 == 0) {
          return true;
        } else {
          return isSumOfKPrimes(n - 2, k - 1);
        }
      } else {
        if (k % 2 == 1) {
          return isSumOfKPrimes(n - 3, k - 1);
        } else {
          return isSumOfKPrimes(n - 5, k - 2);
        }
      }
    }
  }

  // 纯计算机方法，显然要超时，因为sum太大了
  public class ComputationalSolution {

    private List<Integer> primes = new ArrayList<>();

    public boolean isSumOfKPrimes(int sum, int k) {
      if (k < 1 || sum < 2 * k) {
        return false;
      }
      getPrimes(sum);
      return checkSumOfKPrimes(sum, k);
    }

    private boolean checkSumOfKPrimes(int sum, int k) {
      if (k == 1) {
        return isPrime(sum);
      }
      for (Integer prime : primes) {
        if (checkSumOfKPrimes(sum - prime, k - 1)) {
          return true;
        }
      }
      return false;
    }

    private void getPrimes(int max) {
      primes.clear();
      primes.add(2);
      for (int i = 3; i <= max; ++i) {
        boolean isPrime = true;
        for (Integer prime : primes) {
          if (prime * prime > i) {
            break;
          }
          if (i % prime == 0) {
            isPrime = false;
            break;
          }
        }
        if (isPrime) {
          primes.add(i);
        }
      }
    }

    private boolean isPrime(int num) {
      int former = 0, latter = primes.size() - 1, middle, value;
      while (former <= latter) {
        middle = former + ((latter - former) >> 1);
        value = primes.get(middle);
        if (value == num) {
          return true;
        } else if (value < num) {
          former = middle + 1;
        } else {
          latter = middle - 1;
        }
      }
      return false;
    }
  }

  @AllArgsConstructor
  private class Case {
    private int sum, k;
  }

  private final Solution s = new Solution();

  private final Consumer<Case> c = aCase -> log.info(s.isSumOfKPrimes(aCase.sum, aCase.k));

  @Test
  public void test() {
    c.accept(new Case(10, 2)); // 5 5
    c.accept(new Case(11, 4)); // 2 2 2 5
    c.accept(new Case(1000000000, 1000));
  }
}
