package com.g7tianyi.lintcode.array;

import com.g7tianyi.util.Logger;

/**
 * Created by g7tianyi on Sep 1, 2019
 *
 * @link https://www.lintcode.com/problem/swap-two-integers-in-array/description
 */
public class SwapTwoIntegersInArray {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public void swapIntegers(int[] A, int index1, int index2) {
      if (A == null
          || index1 < 0
          || index1 >= A.length
          || index2 < 0
          || index2 >= A.length
          || index1 == index2) {
        return;
      }

      int temp = A[index1];
      A[index1] = A[index2];
      A[index2] = temp;
    }
  }
}
