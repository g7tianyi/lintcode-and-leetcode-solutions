package com.g7tianyi.lintcode.oo;

import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Sep 1, 2019
 *
 * @link https://www.lintcode.com/problem/rectangle-area/description
 */
public class RectangleArea {

  private static final Logger log = Logger.getInstance();

  public class Rectangle {

    private int width;

    private int height;

    public Rectangle(int width, int height) {
      this.width = width;
      this.height = height;
    }

    public int getArea() {
      return width * height;
    }
  }

  @Test
  public void test() {
    Rectangle rectangle = new Rectangle(3, 4);
    log.info(rectangle.getArea());
  }
}
