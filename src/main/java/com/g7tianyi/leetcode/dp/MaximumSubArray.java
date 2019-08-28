package com.g7tianyi.leetcode.dp;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 28, 2019
 *
 * @link https://leetcode-cn.com/classic/problems/maximum-subarray/description/
 */
public class MaximumSubArray {

  private static final Logger log = Logger.getInstance();

  class Solution {

    public int maxSubArray(int[] elems) {

      if (elems.length == 0) {
        return 0;
      }

      int result = elems[0], prev = elems[0];
      for (int i = 1; i < elems.length; ++i) {
        if (prev > 0) {
          prev += elems[i];
        } else {
          prev = elems[i];
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

    private int[] elems;

    private int expected;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Input> runner =
        input -> {
          log.info(input.elems);
          int result = s.maxSubArray(input.elems);
          Assert.assertEquals(result, input.expected);
          log.info("%s\n", result);
        };

    runner.accept(new Input(Arrays.from(-2, 1, -3, 4, -1, 2, 1, -5, 4), 6));
    runner.accept(new Input(Arrays.from(-2, 2, -3, 4, -1, 2, 1, -5, 3), 6));
    runner.accept(new Input(Arrays.from(1, 2, 3, 4), 10));
  }
}
