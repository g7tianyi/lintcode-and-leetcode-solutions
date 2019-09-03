package com.g7tianyi.lintcode.array.matrix;

import com.g7tianyi.common.Strings;
import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 03, 2019
 *
 * @link https://www.lintcode.com/problem/spiral-array/description
 */
public class SpiralArray {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int[][] spiralArray(int num) {

      int[][] wheels =
          new int[][] {
            {0, 1}, // right
            {1, 0}, // bottom
            {0, -1}, // left
            {-1, 0}, // top
          };

      int[][] values = new int[num][num];
      for (int i = 0; i < num; ++i) {
        int[] row = new int[num];
        for (int j = 0; j < num; ++j) {
          row[j] = 0;
        }
        values[i] = row;
      }

      int value = 1;
      int i = 0, j = 0;
      int ni, nj; // next-i next-j
      int d = 0; //
      while (value <= num * num) {
        values[i][j] = value++;

        ni = i + wheels[d][0];
        nj = j + wheels[d][1];

        if (ni < 0 || ni >= num || nj < 0 || nj >= num || values[ni][nj] != 0) {
          d = (d + 1) % 4;
        }

        i += wheels[d][0];
        j += wheels[d][1];
      }

      return values;
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Integer> c =
        num -> {
          int[][] matrix = s.spiralArray(num);
          for (int i = 0; i < num; ++i) {
            log.info(Strings.format(matrix[i]));
          }
          log.info();
        };

    c.accept(1);
    c.accept(2);
    c.accept(3);
    c.accept(4);
    c.accept(5);
    c.accept(10);
  }
}
