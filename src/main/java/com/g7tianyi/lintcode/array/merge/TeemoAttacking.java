package com.g7tianyi.lintcode.array.merge;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Oct 20, 2019
 *
 * @link https://www.lintcode.com/problem/teemo-attacking/description
 */
public class TeemoAttacking {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int findPoisonedDuration(int[] timeSeries, int duration) {

      int result = 0;
      if (timeSeries == null || timeSeries.length == 0) {
        return result;
      }

      int prevStart = 0, prevEnd = 0;
      for (int time : timeSeries) {
        if (prevEnd >= time) {
          prevEnd = time + duration;
        } else {
          result += (prevEnd - prevStart);
          prevStart = time;
          prevEnd = time + duration;
        }
      }
      result += (prevEnd - prevStart);

      return result;
    }
  }

  private final Solution s = new Solution();

  private final Consumer<int[]> c =
      values -> {
        log.info(values);
        log.info(s.findPoisonedDuration(values, 3));
      };

  @Test
  public void test() {
    log.info(s.findPoisonedDuration(Arrays.from(1, 4), 2));
    log.info(s.findPoisonedDuration(Arrays.from(1, 2), 2));

    c.accept(Arrays.sortedArrayOf(10, 100));
  }
}
