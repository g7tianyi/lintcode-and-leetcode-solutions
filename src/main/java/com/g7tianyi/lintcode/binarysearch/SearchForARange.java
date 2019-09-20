package com.g7tianyi.lintcode.binarysearch;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Sep 20, 2019
 *
 * @link https://www.lintcode.com/problem/search-for-a-range/description
 */
public class SearchForARange {

  private static final Logger log = Logger.getInstance();

  public class Solution {
    public int[] searchRange(int[] values, int value) {
      if (values.length == 0) {
        return new int[] {-1, -1};
      }
      int upper = upperBound(values, value);
      if (upper == -1) {
        return new int[] {-1, -1};
      }
      int lower = lowerBound(values, value);
      return new int[] {lower, upper};
    }

    private int upperBound(int[] values, int value) {
      int former = 0, latter = values.length - 1, middle;
      while (former <= latter) {
        middle = former + ((latter - former) >> 1);
        if (values[middle] == value && (middle == latter || values[middle + 1] > value)) {
          return middle;
        }
        if (values[middle] <= value) {
          former = middle + 1;
        } else {
          latter = middle - 1;
        }
      }
      return former >= 0 && former < values.length && values[former] == value ? former : -1;
    }

    private int lowerBound(int[] values, int value) {
      int former = 0, latter = values.length - 1, middle;
      while (former <= latter) {
        middle = former + ((latter - former) >> 1);
        if (values[middle] == value && (middle == 0 || values[middle - 1] < value)) {
          return middle;
        }
        if (values[middle] >= value) {
          latter = middle - 1;
        } else {
          former = middle + 1;
        }
      }
      return former >= 0 && former < values.length && values[former] == value ? former : -1;
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    log.info(s.searchRange(new int[] {}, 1));
    log.info(s.searchRange(Arrays.from(5, 7, 7, 8, 8, 10), 8));
    log.info(s.searchRange(Arrays.from(5, 7, 7, 8, 8, 10), 9));
    log.info(s.searchRange(Arrays.from(5, 7, 7, 8, 8, 10), 10));
    log.info(s.searchRange(Arrays.from(5, 7, 7, 8, 8, 10), 11));
    log.info(s.searchRange(Arrays.from(5, 7, 7, 8, 8, 10), 4));

    log.info(s.searchRange(Arrays.from(5, 7, 7, 8, 8), 5));
    log.info(s.searchRange(Arrays.from(5, 7, 7, 8, 8), 6));
    log.info(s.searchRange(Arrays.from(5, 7, 7, 8, 8), 7));

    log.info(s.searchRange(Arrays.from(5, 5, 5, 5, 5, 5, 5, 5, 5, 5), 5));
  }
}
