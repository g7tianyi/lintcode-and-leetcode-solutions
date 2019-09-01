package com.g7tianyi.lintcode.dp;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 02, 2019
 *
 * @link https://www.lintcode.com/problem/min-cost-climbing-stairs/description
 */
public class MinCostClimbingStairs {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int minCostClimbingStairs(int[] cost) {
      int p0 = cost[0], p1 = cost[1], p2;
      for (int i = 2; i < cost.length; ++i) {
        p2 = Math.min(p0, p1) + cost[i];
        p0 = p1;
        p1 = p2;
      }
      return Math.min(p0, p1);
    }
  }

  @AllArgsConstructor
  private class Case {
    int[] costs;
    int expect;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c =
        aCase -> Assert.assertEquals(aCase.expect, s.minCostClimbingStairs(aCase.costs));

    c.accept(new Case(new int[] {1, 100, 1, 1, 1, 100, 1, 1, 100, 1}, 6));

    c.accept(new Case(new int[] {10, 15, 20}, 15));
  }
}
