package com.g7tianyi.lintcode.hashmap;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by g7tianyi on Oct 05, 2019
 *
 * @link https://www.lintcode.com/problem/longest-consecutive-sequence/description
 */
public class LongestConsecutiveSequence {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int longestConsecutive(int[] values) {

      Set<Integer> set = new HashSet<>();
      for (int value : values) {
        set.add(value);
      }

      // vl: less than value, vg: greater than value
      int result = 0, vl, vg;
      for (int value : values) {
        set.remove(value);
        vl = value - 1;
        vg = value + 1;
        while (set.contains(vl)) {
          set.remove(vl);
          --vl;
        }
        while (set.contains(vg)) {
          set.remove(vg);
          ++vg;
        }
        if (result < vg - vl - 1) {
          result = vg - vl - 1;
        }
      }
      return result;
    }
  }

  public class MLE_Solution { // 内存限制我也是...

    public int longestConsecutive(int[] values) {
      Set<Integer> set = new HashSet<>();
      for (int value : values) {
        set.add(value);
      }

      int result = 0;
      for (int value : values) {
        // vl: less than value, vg: greater than value
        int len = 1, vl = value - 1, vg = value + 1;
        while (set.contains(vl)) {
          ++len;
          --vl;
        }
        while (set.contains(vg)) {
          ++len;
          ++vg;
        }
        if (result < len) {
          result = len;
        }
      }
      return result;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.longestConsecutive(Arrays.from(100, 4, 200, 1, 3, 2)));
  }
}
