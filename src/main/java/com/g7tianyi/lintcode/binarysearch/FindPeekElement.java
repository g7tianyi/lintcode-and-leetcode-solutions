package com.g7tianyi.lintcode.binarysearch;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Oct 05, 2019
 *
 * @link https://www.lintcode.com/problem/find-peak-element/description
 */
public class FindPeekElement {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    // 初始条件说第一个元素从左到右，以及最后一个元素从右到左都开始单调上升
    // 当我们随机选中中间一个数字时，四种情况：
    // - 两边都小于自己，相当于找到了峰值
    // - 左边大于自己，相当于左边下降过，所以左边必然会出现峰值
    // - 右边大于自己，相当于右边下降过，所以右边必然会出现峰值
    // - 两边都大于自己，相当于左右两边都下降过了，所以两边必然都会出现峰值
    // 是一道需要仔细分析、寻找规律的好题
    public int findPeak(int[] A) {
      int former = 1, latter = A.length - 2, middle;
      while (former <= latter) {
        middle = former + ((latter - former) >> 1);
        // 因为former从1开始，latter从len - 2开始，所以不必担心越界
        if (A[middle] > A[middle - 1] && A[middle] > A[middle + 1]) {
          return middle;
        }
        if (A[middle] < A[middle - 1]) { // 左边开始下降了，左边必有峰
          latter = middle - 1;
        } else { // 右边开始下降了，或者两边都有下降了，右边必有峰
          former = middle + 1;
        }
      }
      return -1;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.findPeak(Arrays.from(1, 2, 1, 3, 4, 5, 7, 6)));
    log.info(s.findPeak(Arrays.from(1, 2, 3, 1, 4, 5, 7, 6, 9)));
  }
}
