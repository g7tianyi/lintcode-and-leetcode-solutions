package com.g7tianyi.lintcode.dp.sequence;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Oct 19, 2019
 *
 * @link https://www.lintcode.com/problem/continuous-subarray-sum/description
 */
public class ContinuousSubArraySum {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public List<Integer> continuousSubarraySum(int[] values) {

      List<Integer> result = new ArrayList<>();
      if (values == null || values.length == 0) {
        return result;
      }

      int maxSum = values[0], maxIndex0 = 0, maxIndex1 = 0;
      int currSum, prevSum = values[0], prevIndex0 = 0;
      for (int index = 1; index < values.length; ++index) {
        currSum = values[index];
        if (prevSum >= 0) {
          currSum += prevSum;
        } else {
          prevIndex0 = index;
        }

        if (maxSum < currSum) {
          maxSum = currSum;
          maxIndex0 = prevIndex0;
          maxIndex1 = index;
        }

        prevSum = currSum;
      }

      result.add(maxIndex0);
      result.add(maxIndex1);
      return result;
    }
  }

  private final Solution s = new Solution();

  private final Consumer<int[]> c =
      values -> {
        log.info(values);
        log.info(s.continuousSubarraySum(values));
        log.info();
      };

  @Test
  public void test() {
    c.accept(Arrays.from(-3, 1, 3, -3, 4));
    c.accept(Arrays.from(0, 1, 0, 1));
    c.accept(Arrays.from(-3, -9, 20, -19, 18));
    c.accept(Arrays.from(-18));
  }
}
