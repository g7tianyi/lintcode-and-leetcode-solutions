package com.g7tianyi.lintcode.hash;

import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 30, 2019
 *
 * @link https://www.lintcode.com/problem/k-diff-pairs-in-an-array/description
 */
public class KDiffPairsInAnArray {

  public class Solution {

    public int findPairs(int[] elems, int k) {

      Map<Integer, Integer> map = new HashMap<>();
      for (int elem : elems) {
        if (!map.containsKey(elem)) {
          map.put(elem, 1);
        } else {
          map.put(elem, 2);
        }
      }

      int result = 0;
      if (k == 0) {
        for (Integer value : map.values()) {
          if (value == 2) {
            ++result;
          }
        }
      } else {
        for (Integer key : map.keySet()) {
          if (map.containsKey(key + k)) {
            ++result;
          }
          if (map.containsKey(key - k)) {
            ++result;
          }
        }
        result >>= 1;
      }

      return result;
    }
  }

  @AllArgsConstructor
  private static class Case {

    private int[] elems;
    private int k;
    private int r;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c = aCase -> Assert.assertEquals(aCase.r, s.findPairs(aCase.elems, aCase.k));

    c.accept(new Case(new int[] {3, 1, 4, 1, 5}, 2, 2));
    c.accept(new Case(new int[] {1, 2, 3, 4, 5}, 1, 4));
    c.accept(new Case(new int[] {1, 3, 1, 5, 4}, 0, 1));
    c.accept(new Case(new int[] {1, 3, 1, 1, 4}, 0, 1));

    // Honestly, I think the following case should return 6...
    c.accept(new Case(new int[] {1, 1, 1, 1, 4}, 0, 1));

    c.accept(new Case(new int[] {1, 1, 1, 1, 1}, 0, 1));
  }
}
