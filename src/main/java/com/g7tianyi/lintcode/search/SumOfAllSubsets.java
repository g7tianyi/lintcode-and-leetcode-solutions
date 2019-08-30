package com.g7tianyi.lintcode.search;

import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 30, 2019
 *
 * @link https://www.lintcode.com/problem/sum-of-all-subsets/description
 */
public class SumOfAllSubsets {

  public class Solution {

    public int subSum(int n) {

      return dfs(1, n, 0);
    }

    public int dfs(int curr, int max, int sum) {
      if (curr > max) {
        return sum;
      }
      return dfs(curr + 1, max, sum + curr) + dfs(curr + 1, max, sum);
    }
  }

  @AllArgsConstructor
  public static class Case {

    private int num;

    private int expected;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c = aCase -> Assert.assertEquals(aCase.expected, s.subSum(aCase.num));

    c.accept(new Case(2, 6));
    c.accept(new Case(3, 24));
  }
}
