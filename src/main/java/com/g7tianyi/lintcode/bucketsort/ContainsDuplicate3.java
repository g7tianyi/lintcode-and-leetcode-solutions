package com.g7tianyi.lintcode.bucketsort;

import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 31, 2019
 *
 * @link https://www.lintcode.com/problem/contains-duplicate-iii/description
 */
public class ContainsDuplicate3 {

  public class Solution {

    // Bucket-Sort
    public boolean containsNearbyAlmostDuplicate(int[] values, int maxIndexDiff, int maxValueDiff) {

      if (values == null || values.length <= 1 || maxIndexDiff < 1 || maxValueDiff < 0) {
        return false;
      }

      int minValue = values[0];
      for (int elem : values) {
        if (minValue > elem) {
          minValue = elem;
        }
      }

      Map<Integer, Integer> buckets = new HashMap<>();

      for (int i = 0, value; i < values.length; ++i) {

        value = values[i];

        if (i > maxIndexDiff) {
          // remove bucket who's further than maxIndexDiff
          int bucketIndex = (values[i - maxIndexDiff - 1] - minValue) / (maxValueDiff + 1);
          buckets.remove(bucketIndex);
        }

        int bucketIndex = (value - minValue) / (maxValueDiff + 1);
        if (buckets.containsKey(bucketIndex)) {
          return true;
        }

        if (buckets.containsKey(bucketIndex - 1)
            && value - buckets.get(bucketIndex - 1) <= maxValueDiff) {
          return true;
        }

        if (buckets.containsKey(bucketIndex + 1)
            && buckets.get(bucketIndex + 1) - value <= maxValueDiff) {
          return true;
        }

        buckets.put(bucketIndex, value);
      }

      return false;
    }
  }

  @AllArgsConstructor
  private static class Case {

    private int[] elems;
    private int k;
    private int t;
    private boolean result;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c =
        aCase ->
            Assert.assertEquals(
                aCase.result, s.containsNearbyAlmostDuplicate(aCase.elems, aCase.k, aCase.t));

    c.accept(new Case(new int[] {1, 3, 1}, 1, 1, false));
    c.accept(new Case(new int[] {1, 3, 1}, 1, 2, true));
  }
}
