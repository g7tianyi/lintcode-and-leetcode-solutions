package com.g7tianyi.lintcode.dp;

import com.g7tianyi.lintcode.util.Console;
import com.g7tianyi.lintcode.util.Log;
import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 24, 2019
 *
 * @link https://www.lintcode.com/problem/maximum-subarray/description
 */
public class MaximumSubArray {

  private static final Log log = new Log();

  public class Solution {

    public int maxSubArray(int[] arr) {
      if (arr == null || arr.length == 0) {
        return 0;
      }

      // F(n)表示arr(0...n-1)的数组中，以n-1结尾的子数组的最大和
      int result = arr[0], prev = arr[0], curr;
      for (int i = 1; i < arr.length; i++) {
        curr = arr[i];
        if (prev > 0) {
          prev += curr;
        } else {
          prev = curr;
        }

        if (result < prev) {
          result = prev;
        }
      }

      return result;
    }
  }

  @AllArgsConstructor
  public static class Input {

    private int[] arr;

    private int expected;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Input> runner =
        input -> {
          Console.log(input.arr);
          int result = s.maxSubArray(input.arr);
          Assert.assertEquals(result, input.expected);
          log.info("%s\n", result);
        };

    {
      int[] arr = {-2, 2, -3, 4, -1, 2, 1, -5, 3};
      runner.accept(new Input(arr, 6));
    }
    {
      int[] arr = {1, 2, 3, 4};
      runner.accept(new Input(arr, 10));
    }
  }
}
