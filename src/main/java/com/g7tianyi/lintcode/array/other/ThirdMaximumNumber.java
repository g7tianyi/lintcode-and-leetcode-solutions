package com.g7tianyi.lintcode.array.other;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Oct 15, 2019
 *
 * @link https://www.lintcode.com/problem/third-maximum-number/description
 */
public class ThirdMaximumNumber {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int thirdMax(int[] nums) {
      long[] tops = new long[] {Long.MIN_VALUE, Long.MIN_VALUE, Long.MIN_VALUE};
      for (int num : nums) {
        long curr = num, temp;
        for (int i = 0; i < 3; ++i) {
          if (curr == tops[i]) {
            break;
          }
          if (curr > tops[i]) {
            temp = tops[i];
            tops[i] = curr;
            curr = temp;
          }
        }
      }

      long result = tops[2] != Long.MIN_VALUE ? tops[2] : tops[0];
      return (int) result;
    }
  }

  private final Solution s = new Solution();

  private final Consumer<int[]> c =
      elems -> {
        log.info(elems);
        log.info(s.thirdMax(elems));
      };

  @Test
  public void test() {
    c.accept(Arrays.from(3, 2, 1));
    c.accept(Arrays.from(3, 2, 2, 1));
    c.accept(Arrays.from(2, 2, 1));
    c.accept(Arrays.from(2, 2, 2));
    c.accept(Arrays.from(2));
    c.accept(Arrays.from(2, 5));
    c.accept(Arrays.random(10, 100));
  }
}
