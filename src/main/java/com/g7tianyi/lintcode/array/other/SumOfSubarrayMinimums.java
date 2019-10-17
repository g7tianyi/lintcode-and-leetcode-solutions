package com.g7tianyi.lintcode.array.other;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.io.IOException;
import java.util.Stack;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Oct 16, 2019
 *
 * @link https://www.lintcode.com/problem/sum-of-subarray-minimums/description
 */
public class SumOfSubarrayMinimums {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    // 单调栈的思路，left[i]表示从第一个元素到第i个元素里最小值的下标
    public int sumSubarrayMins(int[] values) {

      Stack<Integer> stack = new Stack<>();
      int[] left = new int[values.length];
      for (int i = 0; i < values.length; ++i) {
        while (!stack.isEmpty() && values[stack.peek()] >= values[i]) {
          stack.pop();
        }
        if (stack.isEmpty()) {
          left[i] = -1; // 设为最左边，index为-1
        } else {
          left[i] = stack.peek();
        }
        stack.push(i);
      }

      stack.clear();
      int[] right = new int[values.length];
      for (int i = values.length - 1; i >= 0; --i) {
        // 注意下面的表达式不接受等于了，否则会有重复
        while (!stack.isEmpty() && values[stack.peek()] > values[i]) {
          stack.pop();
        }
        if (stack.isEmpty()) {
          right[i] = values.length; // 设为最右
        } else {
          right[i] = stack.peek();
        }
        stack.push(i);
      }

      int mod = (int) Math.pow(10, 9) + 7;
      int result = 0;
      for (int i = 0; i < values.length; ++i) {
        int l = i - left[i];
        int r = right[i] - i;
        result = (result + l * r * values[i]) % mod;
      }
      return result;
    }
  }

  public class TLESolution {

    public int sumSubarrayMins(int[] values) {
      long result = 0;
      int mod = (int) Math.pow(10, 9) + 7;
      for (int i = 0; i < values.length; ++i) {
        int minVal = values[i];
        result = (result + minVal) % mod;
        for (int j = i + 1; j < values.length; ++j) {
          minVal = Math.min(minVal, values[j]);
          result = (result + minVal) % mod;
        }
      }
      return (int) result;
    }
  }

  private final Solution s = new Solution();

  private final Consumer<int[]> c =
      values -> {
        log.info(values);
        log.info(s.sumSubarrayMins(values));
        log.info(new TLESolution().sumSubarrayMins(values));
      };

  @Test
  public void test() throws IOException {
    c.accept(Arrays.from(3, 1, 2, 4));
    c.accept(Arrays.from(4, 2, 2, 2, 2, 2, 2, 3, 4, 8, 3));
  }
}
