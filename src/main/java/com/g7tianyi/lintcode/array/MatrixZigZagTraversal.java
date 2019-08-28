package com.g7tianyi.lintcode.array;

import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Aug 28, 2019
 *
 * @link https://www.lintcode.com/problem/matrix-zigzag-traversal/description
 */
public class MatrixZigZagTraversal {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int[] printZMatrix(int[][] values) {

      if (values == null || values.length == 0 || values[0].length == 0) {
        return new int[] {};
      }

      // 下面的代码不好懂，画个图
      //
      // 原矩阵如下：
      // 1 2 3 4
      // 5 6 7 8
      // 9 A B C
      // D E F G
      //
      // 转换一下：
      // 行数不变，列数变为 m + n - 1
      // 1 2 3 4 # # #
      // # 5 6 7 8 # #
      // # # 9 A B C #
      // # # # D E F G
      //
      // 遍历的顺序是：
      // 1) 按列遍历，列从0开始计数
      // 2) 偶数列，从下往上遍历；奇数列从上往下遍历
      //
      // 第0列从下往上：# # # 1
      // 第1列从上往下：2 5 # #
      // 第2列从下往上：# 9 6 3
      // ...
      //

      int m = values.length, n = values[0].length, k = 0;
      int[] result = new int[m * n];
      for (int i = 0; i < m + n - 1; ++i) {
        if ((i & 1) == 0) { // 偶数列
          for (int j = m - 1; j >= 0; --j) {
            if (i - j >= 0 && i - j < n) {
              result[k++] = values[j][i - j];
            }
          }
        } else {
          for (int j = 0; j < m; ++j) {
            if (i - j >= 0 && i - j < n) {
              result[k++] = values[j][i - j];
            }
          }
        }
      }

      return result;
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    log.info(s.printZMatrix(new int[][] {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}}));
    log.info(
        s.printZMatrix(
            new int[][] {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}}));
    log.info(s.printZMatrix(new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}));
  }
}
