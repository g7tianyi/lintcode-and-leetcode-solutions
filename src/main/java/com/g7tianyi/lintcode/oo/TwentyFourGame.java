package com.g7tianyi.lintcode.oo;

import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Sep 24, 2019
 *
 * @link https://www.lintcode.com/problem/24-game/description
 */
public class TwentyFourGame {

  private static final Logger log = Logger.getInstance();

  public static class Solution {

    private static class Value {
      int numerator; // 分子
      int denominator; // 分母

      public Value(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
      }

      public Value add(Value value) {
        int denominator = this.denominator * value.denominator;
        int numerator = this.numerator * value.denominator + value.numerator * this.denominator;
        return from(numerator, denominator);
      }

      public Value sub(Value value) {
        int denominator = this.denominator * value.denominator;
        int numerator = this.numerator * value.denominator - value.numerator * this.denominator;
        return from(numerator, denominator);
      }

      public Value mul(Value value) {
        return from(numerator * value.numerator, denominator * value.denominator);
      }

      public Value div(Value value) {
        return from(numerator * value.denominator, denominator * value.numerator);
      }

      public boolean equalsTo(int num) {
        return numerator % denominator == 0 && numerator / denominator == num;
      }

      public String toString() {
        if (denominator == 1) {
          return String.valueOf(numerator);
        }
        return String.format("%d/%d", numerator, denominator);
      }

      private static Value from(int numerator, int denominator) {
        int divisor = gcd(Math.abs(numerator), Math.abs(denominator));
        return new Value(numerator / divisor, denominator / divisor);
      }

      private static int gcd(int a, int b) {
        int large = a, small = b;
        if (a < b) {
          large = b;
          small = a;
        }

        int temp;
        while (small != 0) {
          temp = large % small;
          large = small;
          small = temp;
        }
        return large;
      }
    }

    public boolean compute24(double[] nums) {
      if (nums == null || nums.length == 0) {
        return false;
      }

      Value[] values = new Value[nums.length];
      for (int i = 0; i < nums.length; ++i) {
        values[i] = new Value((int) nums[i], 1);
      }

      return new Resolver(values).resolve();
    }

    private static class Resolver {

      private final Value[] values;
      private boolean[] visit;

      public Resolver(Value[] values) {
        this.values = values;
        this.visit = new boolean[values.length];
      }

      public boolean resolve() {
        int count = 0, pos = -1;
        for (int i = 0; i < values.length; ++i) {
          if (!visit[i]) {
            ++count;
            pos = i;
          }
        }
        if (count == 1) {
          return values[pos].equalsTo(24);
        }

        Value first;
        for (int i = 0; i < values.length - 1; ++i) {
          if (visit[i]) {
            continue;
          }

          first = values[i];
          for (int j = i + 1; j < values.length; ++j) {
            if (visit[j]) {
              continue;
            }
            visit[j] = true;
            if (compute(first, values[j], i) || compute(values[j], first, i)) {
              return true;
            }
            visit[j] = false;
          }
          values[i] = first;
        }
        return false;
      }

      private boolean compute(Value first, Value second, int i) {
        values[i] = first.add(second);
        if (resolve()) {
          return true;
        }

        values[i] = first.sub(second);
        if (resolve()) {
          return true;
        }

        values[i] = first.mul(second);
        if (resolve()) {
          return true;
        }

        if (!second.equalsTo(0)) {
          values[i] = first.div(second);
          return resolve();
        }

        return false;
      }
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.compute24(new double[] {6, 6, 8, 1}));
    log.info(s.compute24(new double[] {5, 5, 5, 1}));
    log.info(s.compute24(new double[] {1, 4, 8, 7}));
    log.info(s.compute24(new double[] {1, 2, 3, 4}));
    log.info(s.compute24(new double[] {1, 1, 1, 2}));
  }
}
