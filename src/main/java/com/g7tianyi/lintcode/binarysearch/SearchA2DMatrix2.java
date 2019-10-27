package com.g7tianyi.lintcode.binarysearch;

import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Aug 27, 2019
 *
 * @link https://www.lintcode.com/problem/search-a-2d-matrix-ii/description
 */
public class SearchA2DMatrix2 {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int searchMatrix(int[][] matrix, int target) {

      if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
        return 0;
      }

      int result = 0;
      int i = 0, j = matrix[0].length - 1;
      while (i < matrix.length && j >= 0) {
        if (matrix[i][j] == target) {
          ++result;
          ++i;
          --j;
        } else if (matrix[i][j] < target) {
          // 右上角数字的特点是，横排看它是最大值，竖排看它是最小值
          // 如果当前值小于目标值，只能尝试往下边找
          ++i;
        } else {
          // 如果当前值大于目标值，只能尝试往左边找
          --j;
        }
      }
      return result;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    int[][] matrix =
        new int[][] {
          {1, 3, 5, 7},
          {2, 4, 7, 8},
          {3, 5, 9, 10},
        };

    log.info(s.searchMatrix(matrix, 3));
    log.info(s.searchMatrix(matrix, 4));
    log.info(s.searchMatrix(matrix, 7));
    log.info(s.searchMatrix(matrix, 9));
    log.info(s.searchMatrix(matrix, 0));
  }
}
