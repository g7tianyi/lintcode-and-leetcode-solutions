package com.g7tianyi.lintcode.geometry;

import com.g7tianyi.util.Logger;

/**
 * Created by g7tianyi on Sep 04, 2019
 *
 * @link https://www.lintcode.com/problem/largest-triangle-area/description
 */
public class LargestTriangleArea {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public double largestTriangleArea(int[][] points) {
      double result = 0;
      for (int i = 0; i < points.length; ++i) {
        for (int j = i + 1; j < points.length; ++j) {
          for (int k = j + 1; k < points.length; ++k) {
            int x1 = points[i][0], y1 = points[i][1];
            int x2 = points[j][0], y2 = points[j][1];
            int x3 = points[k][0], y3 = points[k][1];
            double area =
                Math.abs(0.5 * (x2 * y3 + x1 * y2 + x3 * y1 - x3 * y2 - x2 * y1 - x1 * y3));
            result = Math.max(result, area);
          }
        }
      }
      return result;
    }
  }
}
