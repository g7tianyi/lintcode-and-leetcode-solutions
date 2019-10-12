package com.g7tianyi.lintcode.hashmap;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by g7tianyi on Oct 12, 2019
 *
 * @link https://www.lintcode.com/problem/4sum-ii/description
 */
public class FourSum2 {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
      Map<Long, Integer> m1 = combineSum(A, B);
      Map<Long, Integer> m2 = combineSum(C, D);

      int result = 0;
      for (Map.Entry<Long, Integer> entry : m1.entrySet()) {
        int value = m2.getOrDefault(-entry.getKey(), 0);
        result += entry.getValue() * value;
      }
      return result;
    }

    private Map<Long, Integer> combineSum(int[] A, int[] B) {
      Map<Long, Integer> m = new HashMap<>();
      for (int a : A) {
        for (int b : B) {
          long sum = a + b;
          Integer count = m.getOrDefault(sum, 0);
          m.put(sum, count + 1);
        }
      }
      return m;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(
        s.fourSumCount(
            Arrays.from(1, 2), Arrays.from(-2, -1), Arrays.from(-1, 2), Arrays.from(0, 2)));
  }
}
