package com.g7tianyi.lintcode.binarysearch;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by g7tianyi on Oct 11, 2019
 *
 * @link https://www.lintcode.com/problem/the-smallest-difference/description
 */
public class TheSmallestDifference {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    /**
     * 思路1：将B排好序，然后对于A中每个元素A[i]，利用二分查找，寻找在B中的插入位置，
     *        前后一对比就可以得出最小差——当然需要处理下边界条件，就是A[i]恰好是最大或最小的，
     *        显然是O(NlogN)，空间开销是常数的。
     * 思路2：将A和B连接到一起，然后排序，然后比较所有的相邻两个数字的差，这个需要额外的O(N)的空间，
     *        但代码是很简洁的。
     * 思路3：将A和B都排好序，利用归并排序的思路，总是比较相邻两个数字的差。
     */
    public int smallestDifference(int[] A, int[] B) {
      Arrays.sort(B);
      int result = -1, temp;
      for (int v1 : A) {
        temp = search(B, v1);
        if (result == -1 || result > temp) {
          result = temp;
        }
      }
      return result;
    }

    private int search(int[] values, int value) {
      int former = 0, latter = values.length - 1, middle;
      while (former <= latter) {
        middle = former + ((latter - former) >> 1);
        if (values[middle] == value) {
          return 0;
        }

        if (values[middle] > value) {
          if (middle == 0) {
            return values[middle] - value;
          }
          if (values[middle - 1] < value) {
            return Math.min(value - values[middle - 1], values[middle] - value);
          }
          latter = middle - 1;
        } else { // value > values[middle]
          if (middle == values.length - 1) {
            return value - values[middle];
          }
          if (values[middle + 1] > value) {
            return Math.min(values[middle + 1] - value, value - values[middle]);
          }
          former = middle + 1;
        }
      }
      if (latter <= 0) {
        return values[0] - value;
      }
      return value - values[values.length - 1];
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(
        s.smallestDifference(
            com.g7tianyi.common.Arrays.from(8, 9, 11, 13),
            com.g7tianyi.common.Arrays.from(1, 2, 4, 6)));
    log.info(
        s.smallestDifference(
            com.g7tianyi.common.Arrays.from(2, 3, 4, 6),
            com.g7tianyi.common.Arrays.from(9, 10, 11, 12)));
    log.info(
        s.smallestDifference(
            com.g7tianyi.common.Arrays.from(3, 6, 7, 4),
            com.g7tianyi.common.Arrays.from(2, 8, 9, 3)));
  }
}
