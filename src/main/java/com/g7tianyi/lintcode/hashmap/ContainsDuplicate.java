package com.g7tianyi.lintcode.hashmap;

import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 31, 2019
 *
 * @link https://www.lintcode.com/problem/contains-duplicate/description
 */
public class ContainsDuplicate {

  public class Solution {

    public boolean containsDuplicate(int[] nums) {

      Set<Integer> set = new HashSet<>();
      for (int num : nums) {
        if (set.contains(num)) {
          return true;
        }
        set.add(num);
      }
      return false;
    }
  }

  @AllArgsConstructor
  private static class Case {

    private int[] elems;
    private boolean result;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c = aCase -> Assert.assertEquals(aCase.result, s.containsDuplicate(aCase.elems));

    c.accept(new Case(new int[] {1, 2, 3}, false));
    c.accept(new Case(new int[] {1, 1}, true));
  }
}
