package com.g7tianyi.common;

/** Created by g7tianyi on Sep 02, 2019 */
public class Point {

  public int x;
  public int y;

  public Point() {
    x = 0;
    y = 0;
  }

  public Point(int a, int b) {
    x = a;
    y = b;
  }

  public static Point of(int a, int b) {
    return new Point(a, b);
  }
}
