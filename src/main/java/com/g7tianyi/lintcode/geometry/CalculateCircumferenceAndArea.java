package com.g7tianyi.lintcode.geometry;

/**
 * Created by g7tianyi on Aug 31, 2019
 *
 * @link https://www.lintcode.com/problem/calculate-circumference-and-area/description
 */
public class CalculateCircumferenceAndArea {

  public static class Solution {

    public double[] calculate(int r) {
      double circumference = 2 * 3.14f * (double) r;
      double area = circumference * (double) r / 2;
      return new double[] {circumference, area};
    }
  }
}
