package com.g7tianyi.lintcode.array;

import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Aug 30, 2019
 *
 * @link https://www.lintcode.com/problem/largest-number-at-least-twice-of-others/description
 */
public class LargestNumberAtLeastTwiceOfOthers {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    // TODO: How to find the dominant index within only one loop??
    public int dominantIndex(int[] elems) {
      // Write your code here
      if (elems == null) {
        return -1;
      }

      int len = elems.length;
      if (len == 0) {
        return 0;
      }

      int max = elems[0], pos = 0;
      for (int i = 1; i < len; ++i) {
        if (max < elems[i]) {
          max = elems[i];
          pos = i;
        }
      }

      for (int i = 0; i < len; ++i) {
        if (i == pos) {
          continue;
        }
        if ((elems[i] << 1) > max) {
          return -1;
        }
      }

      return pos;
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    log.info(s.dominantIndex(new int[] {3, 6, 1, 0}));
    log.info(s.dominantIndex(new int[] {1, 2, 3, 4}));
    log.info(s.dominantIndex(new int[] {}));
  }
}
