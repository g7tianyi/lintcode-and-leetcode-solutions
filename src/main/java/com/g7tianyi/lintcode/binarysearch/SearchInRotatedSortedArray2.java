package com.g7tianyi.lintcode.binarysearch;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Oct 03, 2019
 *
 * @link https://www.lintcode.com/problem/search-in-rotated-sorted-array/description
 */
public class SearchInRotatedSortedArray2 {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public boolean search(int[] values, int value) {

      int former = 0, latter = values.length - 1, middle;

      while (former <= latter) {
        middle = former + ((latter - former) >> 1);
        if (values[middle] == value) {
          return true;
        }

        if (values[former] < values[middle]) { // 前半部分有序
          if (value >= values[former] && value < values[middle]) { // 落在了前半部分的区间内
            latter = middle - 1;
          } else {
            former = middle + 1;
          }
        } else if (values[former] > values[middle]) { // 后半部分有序
          if (value > values[middle] && value <= values[latter]) {
            former = middle + 1;
          } else {
            latter = middle - 1;
          }
        } else {
          ++former;
        }
      }

      return false;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {

    log.info(s.search(Arrays.from(5, 5, 5, 5, 5, 5, 5, 1, 2, 3, 4), 4));
    log.info(s.search(Arrays.from(5, 5, 5, 5, 5, 5, 5, 1, 2, 3, 4), 4));
    log.info(s.search(Arrays.from(5, 5, 1, 2, 3, 4, 5, 5, 5, 5, 5), 4));
    log.info(s.search(Arrays.from(5, 5, 1, 2, 3, 4, 5, 5, 5, 5, 5), 3));
    log.info(s.search(Arrays.from(5, 5, 5, 5, 1, 2, 3, 4, 5, 5, 5), 3));
    log.info(s.search(Arrays.from(3, 4, 4, 5, 7, 0, 1, 2), 4));
    log.info(s.search(Arrays.from(3, 4, 4, 5, 7, 0, 1, 2), 7));
    log.info(s.search(Arrays.from(3, 4, 4, 5, 7, 0, 1, 2), 1));
  }
}
