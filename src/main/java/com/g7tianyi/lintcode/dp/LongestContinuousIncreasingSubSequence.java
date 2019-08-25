package com.g7tianyi.lintcode.dp;

import com.g7tianyi.lintcode.util.Console;
import com.g7tianyi.lintcode.util.Log;
import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 25, 2019
 *
 * <p>Problem link:
 * https://www.lintcode.com/problem/longest-continuous-increasing-subsequence/description
 */
public class LongestContinuousIncreasingSubSequence {

  private static final Log log = new Log();

  public class Solution {

    public int longestIncreasingContinuousSubsequence(int[] arr) {
      if (arr == null) {
        return 0;
      }
      if (arr.length < 3) {
        return arr.length;
      }

      int maxI = 0, maxD = 0;
      int maxIn = 1, maxDn = 1; // 包含第n个数字在内的最长单调递增/递减子序列的长度

      for (int i = 1; i < arr.length; i++) {
        if (arr[i] > arr[i - 1]) { // 递增
          maxDn = 1;
          maxIn++;
          if (maxI < maxIn) {
            maxI = maxIn;
          }
        } else if (arr[i] < arr[i - 1]) { // 递减
          maxIn = 1;
          maxDn++;
          if (maxD < maxDn) {
            maxD = maxDn;
          }
        }
      }

      return Math.max(maxI, maxD);
    }
  }

  @AllArgsConstructor
  public static class Input {

    private int[] elems;

    private int expected;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Input> runner =
        input -> {
          Console.log(input.elems);
          int result = s.longestIncreasingContinuousSubsequence(input.elems);
          Assert.assertEquals(result, input.expected);
          log.info("%s\n", result);
        };

    runner.accept(new Input(new int[] {5, 4, 2, 1, 3}, 4));
    runner.accept(new Input(new int[] {5, 1, 2, 3, 4}, 4));
    runner.accept(new Input(new int[] {1}, 1));
    runner.accept(new Input(new int[] {2}, 1));
    runner.accept(new Input(new int[] {1, 2, 1, 2}, 2));
  }
}
