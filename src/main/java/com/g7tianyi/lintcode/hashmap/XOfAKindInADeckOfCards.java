package com.g7tianyi.lintcode.hashmap;

import com.g7tianyi.common.Lists;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 02, 2019
 *
 * @link https://www.lintcode.com/problem/x-of-a-kind-in-a-deck-of-cards/description
 */
public class XOfAKindInADeckOfCards {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public boolean hasGroupsSizeX(List<Integer> integers) {

      Map<Integer, Integer> map = new HashMap<>();
      for (Integer integer : integers) {
        Integer count = map.getOrDefault(integer, 0);
        map.put(integer, count + 1);
      }

      Collection<Integer> values = map.values();
      Integer minGroup = null;
      for (Integer value : values) {
        if (minGroup == null) {
          minGroup = value;
        } else {
          minGroup = gcd(value, minGroup);
        }
      }

      if (minGroup == null || minGroup < 2) {
        return false;
      }
      for (Integer value : values) {
        if (value % minGroup != 0) {
          return false;
        }
      }
      return true;
    }

    private int gcd(int a, int b) {
      int large = a, small = b;
      if (a < b) {
        large = b;
        small = a;
      }

      int temp;
      while (small != 0) {
        temp = large % small;
        large = small;
        small = temp;
      }

      return large;
    }
  }

  @AllArgsConstructor
  private class Case {
    List<Integer> integers;
    boolean expect;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c = aCase -> Assert.assertEquals(aCase.expect, s.hasGroupsSizeX(aCase.integers));

    c.accept(new Case(Lists.from(1, 1, 1, 1, 2, 2, 2, 2, 2, 2), true));
    c.accept(new Case(Lists.from(1, 1, 2, 2, 2, 2), true));
    c.accept(new Case(Lists.from(1, 1, 1, 1, 2, 2, 2, 2, 2, 2), true));
    c.accept(new Case(Lists.from(1, 2, 3, 4, 4, 3, 2, 1), true));
    c.accept(new Case(Lists.from(1, 1, 1, 2, 2, 2, 3, 3), false));
    c.accept(new Case(Lists.from(1), false));
  }
}
