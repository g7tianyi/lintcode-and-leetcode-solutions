package com.g7tianyi.lintcode.array.other;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by g7tianyi on Oct 21, 2019
 *
 * @link https://www.lintcode.com/problem/subarray-product-less-than-k/description
 */
public class SubArrayProductLessThanK {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int numSubarrayProductLessThanK(int[] values, int K) {

      if (values == null) {
        return 0;
      }

      int result = 0;
      Map<Integer, Integer> curr = new HashMap<>();
      for (int value : values) {
        if (value >= K) {
          continue;
        }

        ++result;
        Set<Integer> keys = curr.keySet();

        Map<Integer, Integer> next = new HashMap<>();
        next.put(value, 1);

        for (int key : keys) {
          int temp = key * value;
          if (temp < K) {
            result += curr.get(key);
            next.put(temp, next.getOrDefault(temp, 0) + curr.get(key));
          }
        }
        curr = next;
      }
      return result;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.numSubarrayProductLessThanK(Arrays.from(10, 5, 2, 6), 100));
    log.info(s.numSubarrayProductLessThanK(Arrays.from(5, 10, 2), 10));
    log.info(s.numSubarrayProductLessThanK(Arrays.from(10), 10));
    log.info(s.numSubarrayProductLessThanK(Arrays.from(8), 10));
    log.info(
        s.numSubarrayProductLessThanK(Arrays.from(1, 1, 9, 3, 6, 5, 2, 4, 8, 3, 9, 4, 3, 10), 489));
  }
}
