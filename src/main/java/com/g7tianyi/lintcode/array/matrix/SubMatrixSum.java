package com.g7tianyi.lintcode.array.matrix;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Nov 15, 2019
 *
 * @link https://www.lintcode.com/problem/submatrix-sum/description
 */
public class SubMatrixSum {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int[][] submatrixSum(int[][] matrix) {

      if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
        return null;
      }

      for (int i = 1; i < matrix.length; ++i) {
        for (int j = 0; j < matrix[i].length; ++j) {
          matrix[i][j] += matrix[i - 1][j];
        }
      }

      int[] row = new int[matrix[0].length];
      for (int i = 0; i < matrix.length; ++i) {
        for (int j = i; j < matrix.length; ++j) {
          for (int k = 0; k < matrix[i].length; ++k) {
            if (i == 0) {
              row[k] = matrix[j][k];
            } else {
              row[k] = matrix[j][k] - matrix[i - 1][k];
            }
          }

          Map<Integer, Integer> map = new HashMap<>();
          map.put(0, -1);
          int sum = 0;
          for (int k = 0; k < row.length; k++) {
            sum += row[k];
            if (map.containsKey(sum)) {
              return new int[][] {
                {i, map.get(sum) + 1},
                {j, k}
              };
            }
            map.put(sum, k);
          }
        }
      }

      return null;
    }
  }

  public class ON4Solution {

    public int[][] submatrixSum(int[][] matrix) {

      for (int i = 0; i < matrix.length; ++i) {
        for (int j = 1; j < matrix[i].length; ++j) {
          matrix[i][j] += matrix[i][j - 1];
        }
      }

      for (int i = 1; i < matrix.length; ++i) {
        for (int j = 0; j < matrix[i].length; ++j) {
          matrix[i][j] += matrix[i - 1][j];
          if (matrix[i][j] == 0) {
            return new int[][] {
              {0, 0},
              {i, j},
            };
          }
        }
      }

      for (int i = 0; i < matrix.length; ++i) {
        for (int j = 0; j < matrix[i].length; ++j) {
          for (int k = i + 1; k < matrix.length; ++k) {
            for (int l = j + 1; l < matrix[k].length; ++l) {
              if (matrix[k][l]
                      - matrix[k - i - 1][l]
                      - matrix[k][l - j - 1]
                      + matrix[k - i - 1][l - j - 1]
                  == 0) {
                return new int[][] {
                  {i, j},
                  {k, l},
                };
              }
            }
          }
        }
      }

      return null;
    }
  }

  private final Solution s = new Solution();

  private final Consumer<int[][]> c =
      values -> {
        int[][] result = s.submatrixSum(values);
        if (result == null) {
          log.info("null");
          return;
        }
        for (int[] row : result) {
          log.info(row);
        }
      };

  @Test
  public void test() {
    c.accept(
        new int[][] {
          {1, 5, 7},
          {3, 7, -8},
          {4, -8, 9},
        });
    c.accept(
        new int[][] {
          {0, 1},
          {1, 0},
        });
  }
}
