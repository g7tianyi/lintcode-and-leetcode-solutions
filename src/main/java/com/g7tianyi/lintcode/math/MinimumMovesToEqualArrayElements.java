package com.g7tianyi.lintcode.math;

import com.g7tianyi.common.Strings;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 02, 2019
 *
 * @link https://www.lintcode.com/problem/minimum-moves-to-equal-array-elements/description
 */
public class MinimumMovesToEqualArrayElements {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    //
    // 1 2 3
    //        +1 for all, except the maximum value 2
    // 2 3 3
    //        -1 for all
    // 1 2 2
    //
    // the effect looks like maximum value - 1
    //
    public int minMoves(int[] nums) {
      int min = Integer.MAX_VALUE, res = 0;
      for (int num : nums) {
        min = Math.min(min, num);
      }
      for (int num : nums) {
        res += num - min;
      }
      return res;
    }
  }

  public class SolutionWA {

    // The original array is:
    //
    //   X0, X1, X2, ... Xn-1
    //
    // After R times change...
    //
    //   X   X   X, ...  X
    //
    // R * (len - 1) + (X0 + X1 + X2 + ... + Xn-1) = len * X
    //
    // X starts from MAX(X0, X1, ..., Xn-1) when the maximum occurs only once
    // and the rest of the elements are exactly the same, in another word, if
    // there're at least 3 different numbers, X must be started from MAX + 1.
    //
    @SuppressWarnings("IdempotentLoopBody")
    public int minMoves(int[] nums) {
      if (nums == null || nums.length == 0) {
        return 0;
      }

      int max = nums[0], occ = 1;
      int min = nums[0], opt = nums[0];
      int sum = nums[0], len = nums.length;
      for (int i = 1; i < len; ++i) {
        if (max < nums[i]) {
          max = nums[i];
          occ = 1;
        } else if (nums[i] == max) {
          ++occ;
        }
        if (min > nums[i]) {
          min = nums[i];
        }
        if (opt == max || opt == min) {
          opt = nums[i];
        }
        sum += nums[i];
      }

      if (occ == len) {
        return 0;
      }

      int option = max;
      if (occ != 1 || (max != min && max != opt && min != opt)) {
        ++option;
      }

      int result, right;
      while (option > 0) {
        right = len * option - sum;
        result = right / (len - 1);
        if (result * (len - 1) == right) {
          return result;
        }
        ++option;
      }
      return Integer.MAX_VALUE;
    }
  }

  @AllArgsConstructor
  private class Case {
    int[] nums;
    int expect;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c =
        aCase -> {
          int result = s.minMoves(aCase.nums);
          log.info("%s => %d", Strings.format(aCase.nums), result);
          Assert.assertEquals(aCase.expect, result);
        };

    c.accept(new Case(new int[] {-100, 0, 100}, 300));
    c.accept(new Case(new int[] {1, 1, 1}, 0));
    c.accept(new Case(new int[] {1, 1, 3}, 2));
    c.accept(new Case(new int[] {1, 2, 3}, 3));
    c.accept(new Case(new int[] {5, 6, 8, 8, 5}, 7));
  }
}
