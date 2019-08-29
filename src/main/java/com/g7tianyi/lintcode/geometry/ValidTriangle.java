package com.g7tianyi.lintcode.geometry;

/**
 * Created by g7tianyi on Aug 28, 2019
 *
 * @link https://www.lintcode.com/problem/valid-triangle/description
 */
public class ValidTriangle {

  public static class Solution {

    public boolean isValidTriangle(int a, int b, int c) {
      return a + b > c && a + c > b && b + c > a;
    }
  }
}
