package com.g7tianyi.lintcode.dp;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 24, 2019
 *
 * @link https://www.lintcode.com/problem/minimum-subarray/description
 */
public class MinimumSubArray {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int minSubArray(List<Integer> elems) {
      if (elems == null || elems.isEmpty()) {
        return 0;
      }

      // F(n)表示arr(0...n-1)的数组中，以n-1结尾的子数组的最小和
      int result = elems.get(0), prev = elems.get(0), curr;
      for (int i = 1; i < elems.size(); i++) {
        curr = elems.get(i);
        if (prev < 0) {
          prev += curr;
        } else {
          prev = curr;
        }

        if (result > prev) {
          result = prev;
        }
      }

      return result;
    }
  }

  @AllArgsConstructor
  public static class Input {

    private List<Integer> elems;

    private int expected;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Input> runner =
        input -> {
          log.info(input.elems);
          int result = s.minSubArray(input.elems);
          Assert.assertEquals(result, input.expected);
          log.info("%s\n", result);
        };

    {
      List<Integer> elems = new ArrayList<>();
      elems.add(1);
      elems.add(-1);
      elems.add(-2);
      elems.add(1);
      runner.accept(new Input(elems, -3));
    }
    {
      List<Integer> elems = new ArrayList<>();
      elems.add(1);
      elems.add(-1);
      elems.add(-2);
      elems.add(1);
      elems.add(-4);
      runner.accept(new Input(elems, -6));
    }
  }
}
