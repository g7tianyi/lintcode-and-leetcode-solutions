package com.g7tianyi.lintcode.array.matrix;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.TreeSet;

/**
 * Created by g7tianyi on Oct 02, 2019
 *
 * @link https://www.lintcode.com/problem/max-sum-of-rectangle-no-larger-than-k/description
 */
public class MaxSumOfRectangleNoLargerThanK {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int maxSumSubmatrix(int[][] values, int value) {
      int H = values.length;
      if (H == 0) {
        return 0;
      }
      int W = values[0].length;
      int m = Math.min(H, W);
      int n = Math.max(H, W);

      boolean colIsBig = W > H;
      int res = Integer.MIN_VALUE;
      for (int i = 0; i < m; i++) {
        int[] array = new int[n];
        for (int j = i; j >= 0; j--) {
          int curr = 0;
          TreeSet<Integer> set = new TreeSet<>();
          set.add(0);
          for (int k = 0; k < n; k++) {
            array[k] = array[k] + (colIsBig ? values[j][k] : values[k][j]);
            curr = curr + array[k];
            Integer peer = set.ceiling(curr - value);
            if (peer != null && res < curr - peer) {
              res = curr - peer;
            }
            set.add(curr);
          }
        }
      }
      return res;
    }
  }

  // O(n^4)，不超时都不好意思了
  // 付出了O(n^4)的空间代价
  public class MLE_Solution {

    public int maxSumSubmatrix(int[][] values, int K) {

      int H = values.length, W = values[0].length;

      //       j     l
      //    .  . . . . ...
      // i ... X X X X ...
      //   ... X X X X ...
      //   ... X X X X ...
      // k ... X X X X ...
      //    .  . . . . ...
      //
      // [i, j]表示矩形左上角，[k, l]表示矩形右下角
      // F[i][j][k][l]表示这个矩形内所有数字的和
      //
      int[][][][] F = new int[H][W][H][W];
      int result = Integer.MIN_VALUE;
      for (int h = 1; h <= H; ++h) {
        for (int w = 1; w <= W; ++w) {
          for (int i = 0; i + h <= H; ++i) {
            for (int j = 0, k, l; j + w <= W; ++j) {
              k = i + h - 1;
              l = j + w - 1;
              if (h == 1 && w == 1) {
                F[i][j][k][l] = values[i][j];
              } else if (w > 1) {
                F[i][j][k][l] = F[i][j][k][l - 1] + F[i][l][k][l];
              } else {
                F[i][j][k][l] = F[i][j][k - 1][l] + F[k][j][k][l];
              }

              if (F[i][j][k][l] <= K && result <= F[i][j][k][l]) {
                result = F[i][j][k][l];
              }
            }
          }
        }
      }
      return result;
    }
  }

  // O(n^5)，不超时都不好意思了
  public class TLE_Solution {

    public int maxSumSubmatrix(int[][] values, int K) {

      int H = values.length, W = values[0].length;
      for (int i = 0; i < H; ++i) {
        for (int j = 1; j < W; ++j) {
          values[i][j] += values[i][j - 1];
        }
      }

      int result = Integer.MIN_VALUE;
      for (int w = 1; w <= W; ++w) {
        for (int h = 1; h <= H; ++h) {
          for (int i = 0; i + h <= H; ++i) {
            for (int j = 0; j + w <= W; ++j) {
              int sum = 0;
              for (int r = 0; r < h; ++r) { // row
                sum += values[i + r][j + w - 1] - (j > 0 ? values[i + r][j - 1] : 0);
              }
              if (sum <= K) {
                result = Math.max(result, sum);
              }
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
    log.info(
        s.maxSumSubmatrix(
            new int[][] {
              Arrays.from(5, -4, -3, 4), Arrays.from(-3, -4, 4, 5), Arrays.from(5, 1, 5, -4),
            },
            8));

    log.info(
        s.maxSumSubmatrix(
            new int[][] {
              Arrays.from(1, 0, 1), Arrays.from(0, -2, 3),
            },
            2));

    log.info(s.maxSumSubmatrix(new int[][] {Arrays.from(2, 2, -1)}, 3));
  }
}
