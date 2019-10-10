package com.g7tianyi.lintcode.array.twopointers;

import com.g7tianyi.common.Numbers;
import com.g7tianyi.common.Strings;
import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by g7tianyi on Oct 10, 2019
 *
 * @link https://www.lintcode.com/problem/3sum-closest/description
 */
public class Closest3Sum {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int threeSumClosest(int[] values, int V) {

      if (values == null || values.length < 3) {
        return 0;
      }

      Arrays.sort(values);

      //  A[i] + A[j] + A[k] == target，直接返回结果；
      //  A[i] + A[j] + A[k] < target，三数之和需要变大点儿才更接近target的值，所以j指针右移
      //  A[i] + A[j] + A[k] > target，三数之和需要变小点儿才更接近target的值，所以k指针左移
      int result = -1;
      int minDiff = Integer.MAX_VALUE; // 当前任意三个数字与V的的最小差距
      for (int i = 0, j, k, currSum, currDiff; i < values.length; ++i) {
        j = i + 1;
        k = values.length - 1;
        while (j < k) {
          currSum = values[i] + values[j] + values[k];
          currDiff = Math.abs(currSum - V);
          if (currDiff < minDiff) {
            minDiff = currDiff;
            result = currSum;
          }

          if (currSum == V) {
            return result;
          } else if (currSum < V) {
            ++j;
          } else {
            --k;
          }
        }
      }

      return result;
    }
  }

  public static int bruteForce(int[] values, int V) {
    int result = values[0] + values[1] + values[2];
    int ii = 0, jj = 1, kk = 2;
    for (int i = 0; i < values.length; ++i) {
      for (int j = i + 1; j < values.length; ++j) {
        for (int k = j + 1; k < values.length; ++k) {
          if (Math.abs(values[i] + values[j] + values[k] - V) < Math.abs(result - V)) {
            result = values[i] + values[j] + values[k];
            ii = i;
            jj = j;
            kk = k;
          }
        }
      }
    }
    log.info(
        "%d + %d + %d - %d = %d",
        values[ii], values[jj], values[kk], V, values[ii] + values[jj] + values[kk] - V);
    return result;
  }

  private final Solution s = new Solution();

  private void runTest() {
    int[] values = com.g7tianyi.common.Arrays.randomNatureNumbers(25, 1000);
    Arrays.sort(values);
    int target = Numbers.nextInt(1000);
    log.info("%s | %d", Strings.format(values), target);

    int result = s.threeSumClosest(values, target);
    int expect = bruteForce(values, target);
    log.info("%d VS %d", result, expect);
    log.info();
  }

  @Test
  public void test() {
    log.info(s.threeSumClosest(com.g7tianyi.common.Arrays.from(2, 7, 11, 15), 20));
    log.info(s.threeSumClosest(com.g7tianyi.common.Arrays.from(-1, 2, 1, -4), 1));

    for (int i = 0; i < 10; ++i) {
      runTest();
    }
  }
}
