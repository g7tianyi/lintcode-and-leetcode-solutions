package com.g7tianyi.lintcode.binarysearch;

import com.g7tianyi.util.Logger;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by g7tianyi on Aug 25, 2019
 *
 * @link https://www.lintcode.com/problem/classical-binary-search/description
 */
public class ClassicalBinarySearch {

  private static final Logger log = new Logger();

  public class Solution {

    public int findPosition(int[] arr, int value) {
      int former = 0, latter = arr.length - 1, middle;
      while (former <= latter) {
        middle = former + ((latter - former) >> 1);
        if (arr[middle] == value) {
          return middle;
        }

        if (arr[middle] < value) {
          former = middle + 1;
        } else {
          latter = middle - 1;
        }
      }
      return -1;
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Assert.assertEquals(2, s.findPosition(new int[] {1, 2, 2, 4, 5, 5}, 2));
    Assert.assertEquals(-1, s.findPosition(new int[] {1, 2, 2, 4, 5, 5}, 6));
  }
}
