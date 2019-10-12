package com.g7tianyi.lintcode.array.twopointers;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Oct 12, 2019
 *
 * @link https://www.lintcode.com/problem/container-with-most-water/description
 */
public class ContainerWithMostWater {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    // 当长度减小时候，高必须增长才有可能提升面积
    // 所以从长度最长时开始递减，然后寻找更高的高度
    public int maxArea(int[] values) {
      int result = 0;
      int i = 0, j = values.length - 1;
      while (i < j) {
        result = Math.max(result, (j - i) * Math.min(values[i], values[j]));
        if (values[i] < values[j]) {
          int k = i;
          while (k < j && values[k] <= values[i]) {
            ++k;
          }
          i = k;
        } else {
          int k = j;
          while (k > i && values[k] <= values[j]) {
            --k;
          }
          j = k;
        }
      }
      return result;
    }
  }

  // Time Limit Exceeded
  public class TLESolution {

    public int maxArea(int[] values) {

      // 高度 => 下标
      Set<Integer> heights = new HashSet<>();
      Map<Integer, Info> map = new HashMap<>();
      for (int i = 0; i < values.length; ++i) {
        heights.add(values[i]);
        if (!map.containsKey(values[i])) {
          map.put(values[i], new Info());
        }
        map.get(values[i]).set(i);
      }

      int result = 0;
      for (int i = 0; i < values.length; ++i) {
        int height = values[i];
        for (int h : heights) {
          if (h < height) {
            continue;
          }
          int area = height * maxDiff(map.get(h), i);
          if (area > result) {
            result = area;
          }
        }
      }

      return result;
    }

    private int maxDiff(Info posInfo, int pos) {
      int result = 0;
      if (posInfo.min != pos) {
        result = Math.abs(pos - posInfo.min);
      }
      if (posInfo.max != pos) {
        result = Math.max(result, Math.abs(posInfo.max - pos));
      }
      return result;
    }

    private class Info {

      private int min, max;

      public Info() {
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
      }

      public void set(int val) {
        if (max < val) {
          max = val;
        }
        if (min > val) {
          min = val;
        }
      }
    }
  }

  private static int bruteForce(int[] values) {
    int result = 0;
    for (int i = 0; i < values.length - 1; ++i) {
      for (int j = i + 1; j < values.length; ++j) {
        result = Math.max(result, Math.min(values[i], values[j]) * (j - i));
      }
    }
    return result;
  }

  private final Solution s = new Solution();

  private final Consumer<Integer> c =
      len -> {
        int[] values = com.g7tianyi.common.Arrays.random(len, 10);
        for (int i = 0; i < values.length; ++i) {
          ++values[i];
        }
        log.info(values);
        log.info("%d PK %d", s.maxArea(values), bruteForce(values));
      };

  @Test
  public void test() {
    c.accept(5);
    c.accept(6);
    c.accept(10);
    c.accept(11);
    c.accept(12);
    c.accept(20);
  }
}
