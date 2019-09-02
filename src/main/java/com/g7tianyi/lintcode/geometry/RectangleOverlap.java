package com.g7tianyi.lintcode.geometry;

import com.g7tianyi.common.Point;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 02, 2019
 *
 * @link https://www.lintcode.com/problem/rectangle-overlap/description
 */
public class RectangleOverlap {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public boolean doOverlap(Point p1, Point p2, Point p3, Point p4) {
      // Consider cases when these two rectangles are NOT overlapped
      return !(p1.x > p4.x || p2.x < p3.x || p1.y < p4.y || p2.y > p3.y);
    }
  }

  @AllArgsConstructor
  private class Case {
    Point p1;
    Point p2;
    Point p3;
    Point p4;
    boolean expect;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c =
        aCase -> {
          boolean overlapped = s.doOverlap(aCase.p1, aCase.p2, aCase.p3, aCase.p4);
          log.info(overlapped);
          Assert.assertEquals(aCase.expect, overlapped);
        };

    c.accept(new Case(Point.of(0, 8), Point.of(8, 0), Point.of(6, 6), Point.of(10, 0), true));
    c.accept(new Case(Point.of(6, 6), Point.of(10, 0), Point.of(0, 8), Point.of(8, 0), true));
    c.accept(new Case(Point.of(0, 8), Point.of(8, 0), Point.of(9, 6), Point.of(10, 0), false));
    c.accept(new Case(Point.of(0, 5), Point.of(8, 3), Point.of(8, 3), Point.of(10, 0), true));
  }
}
