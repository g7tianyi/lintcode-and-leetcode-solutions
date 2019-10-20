package com.g7tianyi.lintcode.array.matrix;

import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Oct 20, 2019
 *
 * @link https://www.lintcode.com/problem/diagonal-traverse/description
 */
public class DiagonalTraverse {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int[] findDiagonalOrder(int[][] matrix) {

      if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
        return new int[] {};
      }

      int H = matrix.length, W = matrix[0].length;
      int[] result = new int[H * W];
      for (int i = 0, k = 0; i < H + W - 1; ++i) {
        if ((i & 1) == 0) { // 偶数列
          for (int j = H - 1; j >= 0; --j) {
            if (i - j >= 0 && i - j < W) {
              result[k++] = matrix[j][i - j];
            }
          }
        } else {
          for (int j = 0; j < H; ++j) {
            if (i - j >= 0 && i - j < W) {
              result[k++] = matrix[j][i - j];
            }
          }
        }
      }
      return result;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.findDiagonalOrder(new int[][] {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}}));
    log.info(
        s.findDiagonalOrder(
            new int[][] {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}}));
    log.info(s.findDiagonalOrder(new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}));
  }
}
