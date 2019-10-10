package com.g7tianyi.lintcode.search;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Oct 10, 2019
 *
 * @link https://www.lintcode.com/problem/trapping-rain-water/description
 */
public class TrappingRainWater {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int trapRainWater(int[] values) {
      return getWater(values, false) + getWater(values, true);
    }

    private int getWater(int[] values, boolean rev) {
      if (rev) {
        reverse(values);
      }

      int i = 0;
      int result = 0, temp = 0;
      for (int j = 1; j < values.length; ++j) {
        if (values[j] > values[i] || (rev && values[j] == values[i])) {
          result += temp;
          temp = 0;
          i = j;
        } else {
          temp += (values[i] - values[j]);
        }
      }

      return result;
    }

    private void reverse(int[] values) {
      int i = 0, j = values.length - 1;
      int temp;
      while (i < j) {
        temp = values[i];
        values[i] = values[j];
        values[j] = temp;
        ++i;
        --j;
      }
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.trapRainWater(Arrays.from(1, 2, 1)));
    log.info(s.trapRainWater(Arrays.from(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1)));
    log.info(s.trapRainWater(Arrays.from(0, 1, 0, 2, 1, 0, 1, 3, 4, 1, 2, 1)));
    log.info(s.trapRainWater(Arrays.from(50, 0, 20, 30, 50)));
  }
}
