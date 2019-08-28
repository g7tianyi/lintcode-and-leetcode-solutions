package com.g7tianyi.lintcode.math;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 23, 2019
 *
 * @link https://www.lintcode.com/problem/digit-counts/description
 */
public class DigitCounts {

  private static final Logger log = new Logger();

  public class Solution {

    /** 基本思路是统计个位、十位、百位、千位等各个位上该数字出现的次数 */
    public int digitCounts(int k, int n) {

      if (n < 0 || k < 0) {
        return 0;
      }

      if (n < 10) {
        return count(k, n);
      }

      int result = 0;
      int base = 1;
      int prev = n;
      int tail;
      while (prev != 0) {
        tail = prev % 10;
        prev /= 10;
        result += count(base, prev, tail, k, n);
        base *= 10;
      }
      return result;
    }

    private int count(int k, int n) {
      if (k <= n) {
        return 1;
      }
      return 0;
    }

    private int count(int base, int prev, int tail, int k, int n) {

      if (prev == 0) { // 最高位的情况
        if (k == 0) { // 最高位不能为0
          return 0;
        }
        if (tail > k) {
          return base;
        } else if (tail == k) {
          return n % base + 1;
        }
        return 0;
      }

      if (tail < k) {
        return prev * base;
      } else if (tail > k) {
        return (prev + 1) * base;
      } else {
        return prev * base + n % base + 1;
      }
    }
  }

  public class Verifier {

    public int digitCounts(int k, int n) {
      if (n == 0) {
        if (k == 0) {
          return 1;
        }
        return 0;
      }

      int result = 0;
      for (int i = 0; i <= n; i++) {
        result += count(k, i);
      }
      return result;
    }

    private int count(int k, int n) {
      if (k == 0 && n == 0) {
        return 1;
      }

      int result = 0;
      while (n != 0) {
        if (n % 10 == k) {
          result++;
        }
        n /= 10;
      }
      return result;
    }
  }

  @AllArgsConstructor
  private static class Input {

    private int k;

    private int n;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Verifier v = new Verifier();

    Consumer<Input> runner =
        input -> {
          int verifierResult = v.digitCounts(input.k, input.n);
          int solutionResult = s.digitCounts(input.k, input.n);
          log.info(
              "(%d, %d) => %d, %d\n\n", input.k, input.n, verifierResult, solutionResult);
          Assert.assertEquals(verifierResult, solutionResult);
        };

    runner.accept(new Input(0, 0));
    runner.accept(new Input(0, 9));
    runner.accept(new Input(0, 12));
    runner.accept(new Input(1, 1));
    runner.accept(new Input(1, 12));
    runner.accept(new Input(1, 147));
    runner.accept(new Input(6, 347));
    runner.accept(new Input(7, 1479));
    runner.accept(new Input(7, 3479));
    runner.accept(new Input(2, 3479));
    runner.accept(new Input(9, 3471));
    runner.accept(new Input(2, 3471));
    runner.accept(new Input(1, 21345));
  }
}
