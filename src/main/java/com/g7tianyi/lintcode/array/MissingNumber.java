package com.g7tianyi.lintcode.array;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Oct 05, 2019
 *
 * @link https://www.lintcode.com/problem/missing-number/description
 */
public class MissingNumber {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int findMissing(int[] values) {

      for (int i = 0; i < values.length; ++i) {
        while (values[i] >= 0 && values[i] != i) {
          if (values[i] == values.length) {
            values[i] = -1;
            break;
          }
          int temp = values[values[i]];
          values[values[i]] = values[i];
          values[i] = temp;
        }
      }

      for (int i = 0; i < values.length; ++i) {
        if (values[i] == -1) {
          return i;
        }
      }
      return values.length;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.findMissing(Arrays.from(0, 3, 1)));
    log.info(s.findMissing(Arrays.from(2, 5, 4, 0, 1)));
    log.info(s.findMissing(Arrays.from(1, 2, 3)));
  }
}
