package com.g7tianyi.lintcode.array.other;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by g7tianyi on Oct 21, 2019
 *
 * @link https://www.lintcode.com/problem/summary-ranges/description
 */
public class SummaryRanges {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public List<String> summaryRanges(int[] nums) {

      List<String> result = new ArrayList<>();

      if (nums == null || nums.length == 0) {
        return result;
      }

      int start = nums[0], prev = nums[0];
      for (int i = 1; i < nums.length; ++i) {
        if (nums[i] > prev + 1) {
          if (start == prev) {
            result.add(String.valueOf(start));
          } else {
            result.add(String.format("%d->%d", start, prev));
          }
          start = nums[i];
        }
        prev = nums[i];
      }

      if (start == prev) {
        result.add(String.valueOf(start));
      } else {
        result.add(String.format("%d->%d", start, prev));
      }

      return result;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.summaryRanges(Arrays.from(0, 1, 2, 4, 5, 7)));
    log.info(s.summaryRanges(Arrays.from(0, 2, 3, 4, 6, 8, 9)));
  }
}
