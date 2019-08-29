package com.g7tianyi.lintcode.search;

/**
 * Created by g7tianyi on Aug 30, 2019
 *
 * @link https://www.lintcode.com/problem/flood-fill/description
 */
public class FloodFill {

  public class Solution {

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {

      int oldColor = image[sr][sc];

      int m = image.length, n = image[0].length;

      dfs(image, m, n, sr, sc, oldColor, newColor);

      return image;
    }

    public void dfs(int[][] image, int m, int n, int i, int j, int oldColor, int newColor) {

      image[i][j] = newColor;

      if (i > 0 && image[i - 1][j] == oldColor) {
        dfs(image, m, n, i - 1, j, oldColor, newColor);
      }

      if (i + 1 < m && image[i + 1][j] == oldColor) {
        dfs(image, m, n, i + 1, j, oldColor, newColor);
      }

      if (j > 0 && image[i][j - 1] == oldColor) {
        dfs(image, m, n, i, j - 1, oldColor, newColor);
      }

      if (j + 1 < n && image[i][j + 1] == oldColor) {
        dfs(image, m, n, i, j + 1, oldColor, newColor);
      }
    }
  }
}
