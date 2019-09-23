package com.g7tianyi.lintcode.dp;

import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Sep 23, 2019
 *
 * @link https://www.lintcode.com/problem/maximal-rectangle/description
 */
public class MaximalRectangle {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int maximalRectangle(boolean[][] M) {

      if (M == null || M.length == 0 || M[0].length == 0) {
        return 0;
      }

      int H = M.length, W = M[0].length;

      // R(i,j)表示第i行第j个位置开始往右走，一直到遇到一个0为止，一共有多少个1
      // D(i,j)表示第i行第j个位置开始往下走，一直到遇到一个0为止，一共有多少个1
      int[][] R = new int[H][W], D = new int[H][W];
      for (int i = 0; i < H; ++i) {
        for (int j = W - 1, k = 0; j >= 0; --j) {
          if (M[i][j]) {
            R[i][j] = ++k;
          } else {
            k = 0;
          }
        }
      }
      for (int j = 0; j < W; ++j) {
        for (int i = H - 1, k = 0; i >= 0; --i) {
          if (M[i][j]) {
            D[i][j] = ++k;
          } else {
            k = 0;
          }
        }
      }

      int result = 0;
      for (int i = 0; i < H; ++i) {
        for (int j = 0; j < W; ++j) {
          if (!M[i][j]) {
            continue;
          }

          // 1 1 1 1 1
          // 1 1 1 1 1
          // 1 1 1 0 0
          // 对于上面的矩阵，计算：1 * 3, 2 * 3, 3 * 3, 4 * 2 和 5 * 2 的子矩阵
          for (int w = 1, h = D[i][j]; w <= R[i][j]; ++w) {
            h = Math.min(h, D[i][j + w - 1]);
            result = Math.max(result, w * h);
          }
        }
      }

      return result;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test0() {
    log.info(
        s.maximalRectangle(
            new boolean[][] {
              {true, true, false, false, true},
            }));

    log.info(
        s.maximalRectangle(
            new boolean[][] {
              {true}, {true}, {true}, {false}, {true},
            }));

    log.info(s.maximalRectangle(null));
  }

  @Test
  public void test() {
    log.info(
        s.maximalRectangle(
            new boolean[][] {
              {true, true, false, false, true},
              {false, true, false, false, true},
              {false, false, true, true, true},
              {false, false, true, true, true},
              {false, false, false, false, true},
            }));

    log.info(
        s.maximalRectangle(
            new boolean[][] {
              {true, true, true, true, true},
              {true, true, true, true, true},
              {true, true, true, true, true},
              {true, true, true, true, true},
              {true, true, true, true, true},
            }));

    log.info(
        s.maximalRectangle(
            new boolean[][] {
              {true, true, true, true, true},
              {true, true, true, true, true},
              {true, true, true, false, false},
            }));
  }
}
