package com.g7tianyi.lintcode.binarysearch;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Sep 25, 2019
 *
 * @link https://www.lintcode.com/problem/wood-cut/description
 */
public class WoodCut {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int woodCut(int[] woods, int count) {

      if (woods == null || woods.length == 0) {
        return 0;
      }

      int max = woods[0];
      for (int wood : woods) {
        if (max < wood) {
          max = wood;
        }
      }

      int result = 0, min = 1, mid;
      while (min <= max) {
        mid = min + ((max - min) >> 1);
        int num = 0;
        for (int wood : woods) {
          num += wood / mid;
        }

        if (num >= count) {
          result = Math.max(result, mid);
        }

        if (num >= count) { // 木头短了，或者刚刚好，但仍然尝试一下更长的木头是否可以满足标准
          min = mid + 1;
        } else { // 木头长了
          max = mid - 1;
        }
      }

      return result;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.woodCut(Arrays.from(2147483644, 2147483645, 2147483646, 2147483647), 4));

    log.info(s.woodCut(Arrays.from(232, 124, 456), 7));
    log.info(s.woodCut(Arrays.from(1, 2, 3), 7));
    log.info(s.woodCut(Arrays.from(3), 2));
  }
}
