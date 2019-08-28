package com.g7tianyi.lintcode.list;

import java.util.List;

/**
 * Created by g7tianyi on Aug 29, 2019
 *
 * @link https://www.lintcode.com/problem/nested-list-weight-sum/description
 */
public class NestedListWeightSum {

  public static class Solution {

    // [[1,1],2,[1,1]] => 4 * 1 * 2 + 1 * 2 * 1 = 10
    // [1,[4,[6]]]     => 1 + 4 * 2 + 6 * 3 = 27
    public int depthSum(List<NestedInteger> nestedIntegers) {
      int sum = 0;
      for (NestedInteger nestedInteger : nestedIntegers) {
        sum += dfs(nestedInteger, 1);
      }
      return sum;
    }

    public int dfs(NestedInteger nestedInteger, int depth) {
      if (nestedInteger.isInteger()) {
        return nestedInteger.getInteger() * depth;
      }

      int sum = 0;
      List<NestedInteger> nestedIntegers = nestedInteger.getList();
      for (NestedInteger nested : nestedIntegers) {
        sum += dfs(nested, depth + 1);
      }
      return sum;
    }
  }
}
