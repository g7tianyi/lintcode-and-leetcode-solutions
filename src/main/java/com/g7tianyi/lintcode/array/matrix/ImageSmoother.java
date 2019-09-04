package com.g7tianyi.lintcode.array.matrix;

import com.g7tianyi.util.Logger;

/**
 * Created by g7tianyi on Sep 04, 2019
 *
 * @link https://www.lintcode.com/problem/image-smoother/description
 */
public class ImageSmoother {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int[][] imageSmoother(int[][] M) {

      int[][] result = new int[M.length][];

      for (int i = 0; i < M.length; ++i) {

        result[i] = new int[M[i].length];

        for (int j = 0; j < M[i].length; ++j) {

          boolean top = i > 0;
          boolean left = j > 0;
          boolean right = j + 1 < M[i].length;
          boolean bottom = i + 1 < M.length;

          int sum = M[i][j], count = 1;

          if (top) {
            sum += M[i - 1][j];
            ++count;
          }
          if (top && left) {
            sum += M[i - 1][j - 1];
            ++count;
          }
          if (top && right) {
            sum += M[i - 1][j + 1];
            ++count;
          }

          if (left) {
            sum += M[i][j - 1];
            ++count;
          }
          if (right) {
            sum += M[i][j + 1];
            ++count;
          }

          if (bottom) {
            sum += M[i + 1][j];
            ++count;
          }
          if (bottom && left) {
            sum += M[i + 1][j - 1];
            ++count;
          }
          if (bottom && right) {
            sum += M[i + 1][j + 1];
            ++count;
          }

          result[i][j] = sum / count;
        }
      }

      return result;
    }
  }
}
