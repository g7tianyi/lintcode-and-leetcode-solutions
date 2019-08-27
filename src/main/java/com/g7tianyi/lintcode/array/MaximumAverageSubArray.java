package com.g7tianyi.lintcode.array;

import com.g7tianyi.lintcode.util.Console;
import com.g7tianyi.lintcode.util.Log;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 24, 2019
 *
 * @link https://www.lintcode.com/problem/maximum-average-subarray/description
 */
public class MaximumAverageSubArray {

  private static final Log log = new Log();

  public class Solution {

    public double findMaxAverage(int[] arr, int k) {

      int max = 0, i;
      for (i = 0; i < k; i++) {
        max += arr[i];
      }

      int prev = max, curr;
      while (i < arr.length) {
        curr = prev + arr[i] - arr[i - k];
        if (max < curr) {
          max = curr;
        }
        prev = curr;
        i++;
      }

      return (double) max / (double) k;
    }
  }

  @AllArgsConstructor
  public static class Input {

    private int[] arr;

    private int k;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Input> c =
        input ->
            log.info(Console.stringify(input.arr) + " => " + s.findMaxAverage(input.arr, input.k));

    c.accept(new Input(new int[] {1, 12, -5, -6, 50, 3}, 4));
    c.accept(new Input(new int[] {4, 2, 1, 3, 3}, 2));
  }
}
