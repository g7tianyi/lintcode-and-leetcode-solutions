package com.g7tianyi.lintcode.search;

import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 25, 2019
 *
 * @link https://www.lintcode.com/problem/max-area-of-island/description
 */
public class MaxAreaOfIsland {

  public class Solution {

    public int maxAreaOfIsland(int[][] map) {

      int w = map.length;
      if (w == 0) {
        return 0;
      }

      int h = map[0].length;
      if (h == 0) {
        return 0;
      }

      int result = 0;
      for (int i = 0; i < w; i++) {
        for (int j = 0; j < h; j++) {
          if (map[i][j] == 1) {
            result = Math.max(result, area(map, w, h, i, j));
          }
        }
      }

      return result;
    }

    private int area(int[][] map, int w, int h, int i, int j) {

      if (map[i][j] == 0) {
        return 0;
      }

      map[i][j] = 0;

      int result = 1;
      if (i > 0 && map[i - 1][j] == 1) {
        result += area(map, w, h, i - 1, j);
      }
      if (j > 0 && map[i][j - 1] == 1) {
        result += area(map, w, h, i, j - 1);
      }
      if (i < w - 1 && map[i + 1][j] == 1) {
        result += area(map, w, h, i + 1, j);
      }
      if (j < h - 1 && map[i][j + 1] == 1) {
        result += area(map, w, h, i, j + 1);
      }

      return result;
    }
  }

  @AllArgsConstructor
  public static class Input {

    private int[][] map;

    private int expected;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Input> c = input -> Assert.assertEquals(input.expected, s.maxAreaOfIsland(input.map));

    int[][] m1 =
        new int[][] {
          {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
          {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
          {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
          {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
          {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
          {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
          {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
          {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}
        };

    c.accept(new Input(m1, 6));

    int[][] m2 =
        new int[][] {
          {0, 0, 0, 0, 0, 0, 0, 0},
        };
    c.accept(new Input(m2, 0));

    int[][] m3 = new int[][] {};
    c.accept(new Input(m3, 0));
  }
}
