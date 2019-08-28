package com.g7tianyi.lintcode.math;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 23, 2019
 *
 * @link https://www.lintcode.com/problem/cosine-similarity/description
 */
public class CosineSimilarity {

  private static final Logger log = new Logger();

  public class Solution {

    public double cosineSimilarity(int[] arr1, int[] arr2) {

      if (arr1 == null || arr2 == null || arr1.length != arr2.length || arr1.length == 0) {
        return 2.0000;
      }

      long d1 = 0, d2 = 0;
      for (int i = 0; i < arr1.length; ++i) {
        d1 += arr1[i] * arr1[i];
        d2 += arr2[i] * arr2[i];
      }
      double denominator = Math.sqrt((double) d1) * Math.sqrt((double) d2);
      if (denominator == 0) {
        return 2.0000;
      }

      long numerator = 0;
      for (int i = 0; i < arr1.length; ++i) {
        numerator += arr1[i] * arr2[i];
      }

      return numerator / denominator;
    }
  }

  @AllArgsConstructor
  private static class Case {
    private int[] arr1;
    private int[] arr2;
    private double expected;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> runner =
        aCase -> {
          double result = s.cosineSimilarity(aCase.arr1, aCase.arr2);
          log.info(result);
          Assert.assertEquals(result, aCase.expected, 0.001);
        };

    runner.accept(new Case(new int[] {1, 4, 0}, new int[] {1, 2, 3}, 0.5834));
    runner.accept(new Case(new int[] {1}, new int[] {2}, 1.0));
    runner.accept(new Case(new int[] {0}, new int[] {0}, 2.0));
  }
}
