package com.g7tianyi.lintcode.array;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 02, 2019
 *
 * @link https://www.lintcode.com/problem/intersection-of-two-arrays/description
 */
public class IntersectionOfTwoArrays {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int[] intersection(int[] nums1, int[] nums2) {

      Arrays.sort(nums1);
      Arrays.sort(nums2);

      List<Integer> numbers = new ArrayList<>();

      int i = 0, j = 0;
      Integer prev = null;
      while (i < nums1.length && j < nums2.length) {
        if (nums1[i] == nums2[j]) {
          if (prev == null || prev != nums1[i]) {
            prev = nums1[i];
            numbers.add(nums1[i]);
          }
          ++i;
          ++j;
        } else if (nums1[i] < nums2[j]) {
          ++i;
        } else {
          ++j;
        }
      }

      i = 0;
      int[] result = new int[numbers.size()];
      for (Integer integer : numbers) {
        result[i++] = integer;
      }
      return result;
    }
  }

  @AllArgsConstructor
  private class Case {
    int[] nums1;
    int[] nums2;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c = aCase -> log.info(s.intersection(aCase.nums1, aCase.nums2));

    c.accept(
        new Case(
            com.g7tianyi.common.Arrays.from(1, 2, 2, 1), com.g7tianyi.common.Arrays.from(2, 2)));

    c.accept(new Case(com.g7tianyi.common.Arrays.from(1, 2), com.g7tianyi.common.Arrays.from(2)));
    c.accept(new Case(com.g7tianyi.common.Arrays.from(1, 2, 3, 1), new int[] {}));
  }
}
