package com.g7tianyi.lintcode.search;

import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 28, 2019
 *
 * @link https://www.lintcode.com/problem/island-perimeter/description
 */
public class IslandPerimeter {

  public class Solution {

    public int islandPerimeter(int[][] map) {

      int result = 0;
      if (map == null) {
        return result;
      }

      int m = map.length;
      if (m == 0) {
        return result;
      }

      int n = map[0].length;

      for (int i = 0; i < m; ++i) {
        for (int j = 0; j < n; ++j) {
          if (map[i][j] == 1) {
            if (i == 0 || map[i - 1][j] == 0) {
              ++result;
            }
            if (i == m - 1 || map[i + 1][j] == 0) {
              ++result;
            }
            if (j == 0 || map[i][j - 1] == 0) {
              ++result;
            }
            if (j == n - 1 || map[i][j + 1] == 0) {
              ++result;
            }
          }
        }
      }

      return result;
    }
  }

  @AllArgsConstructor
  public static class Case {

    private int[][] map;

    private int expected;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c = aCase -> Assert.assertEquals(aCase.expected, s.islandPerimeter(aCase.map));

    int[][] m1 =
        new int[][] {
          {0, 1, 0, 0},
          {1, 1, 1, 0},
          {0, 1, 0, 0},
          {1, 1, 0, 0},
        };
    c.accept(new Case(m1, 16));
  }
}
