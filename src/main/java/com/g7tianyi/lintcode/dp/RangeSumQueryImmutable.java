package com.g7tianyi.lintcode.dp;

/**
 * Created by g7tianyi on Aug 25, 2019
 *
 * @link https://www.lintcode.com/problem/range-sum-query-immutable/description
 */
public class RangeSumQueryImmutable {

  class NumArray {

    private int[] arr;

    public NumArray(int[] arr) {
      for (int i = 1; i < arr.length; i++) {
        arr[i] += arr[i - 1];
      }
      this.arr = arr;
    }

    public int sumRange(int i, int j) {
      if (i == 0) {
        return this.arr[j];
      }
      return this.arr[j] - this.arr[i];
    }
  }
}
