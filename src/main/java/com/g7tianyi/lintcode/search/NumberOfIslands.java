package com.g7tianyi.lintcode.search;

import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 25, 2019
 *
 * @link https://www.lintcode.com/problem/number-of-islands/description
 */
public class NumberOfIslands {

  public class Solution {

    public int numIslands(boolean[][] map) {
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
          if (map[i][j]) {
            result++;
            floodFill(map, w, h, i, j);
          }
        }
      }
      return result;
    }

    // 深度优先搜索，标准的灌水法
    private void floodFill(boolean[][] map, int w, int h, int i, int j) {

      map[i][j] = false;

      if (i - 1 >= 0 && map[i - 1][j]) {
        floodFill(map, w, h, i - 1, j);
      }

      if (j - 1 >= 0 && map[i][j - 1]) {
        floodFill(map, w, h, i, j - 1);
      }

      if (i + 1 < w && map[i + 1][j]) {
        floodFill(map, w, h, i + 1, j);
      }

      if (j + 1 < h && map[i][j + 1]) {
        floodFill(map, w, h, i, j + 1);
      }
    }
  }

  @AllArgsConstructor
  public static class Input {

    private boolean[][] map;

    private int expected;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Input> c = input -> Assert.assertEquals(input.expected, s.numIslands(input.map));

    boolean[][] m1 =
        new boolean[][] {
          {true, true, false, false, false},
          {false, true, false, false, true},
          {false, false, false, true, true},
          {false, false, false, false, false},
          {false, false, false, false, true},
        };

    c.accept(new Input(m1, 3));

    boolean[][] m2 =
        new boolean[][] {
          {true, true},
        };
    c.accept(new Input(m2, 1));

    boolean[][] m3 = new boolean[][] {};
    c.accept(new Input(m3, 0));
  }
}
