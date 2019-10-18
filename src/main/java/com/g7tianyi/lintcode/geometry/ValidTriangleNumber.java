package com.g7tianyi.lintcode.geometry;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.Arrays;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Oct 18, 2019
 *
 * @link https://www.lintcode.com/problem/valid-triangle-number/description
 */
public class ValidTriangleNumber {

  private static final Logger log = Logger.getInstance();

  // O(N^2)，双指针
  public class Solution {

    public int triangleNumber(int[] values) {

      if (values == null || values.length < 3) {
        return 0;
      }

      Arrays.sort(values);

      int result = 0, len = values.length;
      for (int i = 0; i < len - 2; ++i) {
        int j = i + 1, k = i + 2;
        while (j < len - 1) {
          if (values[i] + values[j] <= values[k]) {
            ++j;
            k = j + 1;
          } else {
            ++result;
            if (k + 1 < len) {
              ++k;
            } else {
              ++j;
              k = j + 1;
            }
          }
        }
      }
      return result;
    }
  }

  // O(N^2*logN)
  public class Solution2 {

    public int triangleNumber(int[] values) {

      if (values == null || values.length < 3) {
        return 0;
      }

      Arrays.sort(values);

      int result = 0, len = values.length;
      for (int i = 0; i < len - 2; ++i) {
        for (int j = i + 1; j < len - 1; ++j) {
          if (values[i] == 0 || values[j] == 0) {
            continue;
          }
          int pos = binarySearch(values, values[i] + values[j], j + 1, len - 1);
          result += (pos - j);
        }
      }
      return result;
    }

    // 找到第一个小于
    private int binarySearch(int[] values, int maxVal, int start, int end) {
      int i = start, j = end, m;
      while (i <= j) {
        m = i + ((j - i) >> 1);
        if (values[m] < maxVal && (m == end || values[m + 1] >= maxVal)) {
          // 说明m是最后一个小于maxVal的下标了
          return m;
        }

        if (values[m] >= maxVal) {
          j = m - 1;
        } else {
          i = m + 1;
        }
      }
      return start - 1;
    }
  }

  // O(N^3)
  private static int bruteForce(int[] values) {
    int result = 0, len = values.length;
    for (int i = 0; i < len - 2; ++i) {
      for (int j = i + 1; j < len - 1; ++j) {
        for (int k = j + 1; k < len; ++k) {
          if (values[i] == 0 || values[j] == 0 || values[k] == 0) {
            continue;
          }
          if (values[i] + values[j] > values[k]
              && values[i] + values[k] > values[j]
              && values[j] + values[k] > values[i]) {
            ++result;
          }
        }
      }
    }
    return result;
  }

  private final Consumer<int[]> c =
      values -> {
        Arrays.sort(values);
        log.info(values);
        log.info(bruteForce(values));
        log.info(new Solution().triangleNumber(values));
        log.info(new Solution2().triangleNumber(values));
      };

  @Test
  public void test() {
    c.accept(com.g7tianyi.common.Arrays.from(2, 2, 3, 4));
    c.accept(com.g7tianyi.common.Arrays.from(3, 3, 3));
    c.accept(com.g7tianyi.common.Arrays.random(10, 100));
  }
}
