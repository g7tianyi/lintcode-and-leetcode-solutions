package com.g7tianyi.lintcode.hashmap;

import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 31, 2019
 *
 * @link https://www.lintcode.com/problem/contains-duplicate-ii/description
 */
public class ContainsDuplicate2 {

  public class Solution {

    public boolean containsNearbyDuplicate(int[] elems, int k) {

      Map<Integer, List<Integer>> map = new HashMap<>();
      for (int i = 0, size; i < elems.length; ++i) {
        List<Integer> indices = map.getOrDefault(elems[i], new ArrayList<>());
        indices.add(i);
        size = indices.size();
        if (size > 1 && indices.get(size - 1) - indices.get(size - 2) <= k) {
          return true;
        }
        map.put(elems[i], indices);
      }

      return false;
    }
  }

  public class SolutionMLE {

    // MLE
    public boolean containsNearbyDuplicate(int[] elems, int k) {

      Map<Integer, List<Integer>> map = new HashMap<>();
      for (int i = 0; i < elems.length; ++i) {
        List<Integer> indices = map.getOrDefault(elems[i], new ArrayList<>());
        indices.add(i);
        map.put(elems[i], indices);
      }

      for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
        List<Integer> indices = entry.getValue();
        if (indices.size() < 2) {
          continue;
        }
        indices.sort(Integer::compare);

        for (int i = 1; i < indices.size(); ++i) {
          if (indices.get(i) - indices.get(i - 1) <= k) {
            return true;
          }
        }
      }

      return false;
    }
  }

  @AllArgsConstructor
  private static class Case {

    private int[] elems;
    private int k;
    private boolean result;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c =
        aCase -> Assert.assertEquals(aCase.result, s.containsNearbyDuplicate(aCase.elems, aCase.k));

    c.accept(new Case(new int[] {1, 2, 1}, 0, false));
    c.accept(new Case(new int[] {1, 2, 1}, 2, true));
  }
}
