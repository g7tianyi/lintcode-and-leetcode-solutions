package com.g7tianyi.lintcode.search;

import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Oct 24, 2019
 *
 * @link https://www.lintcode.com/problem/paint-fill/description
 */
public class PaintFill {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public boolean paintFill(int[][] screen, int x, int y, int newColor) {
      if (screen == null
          || screen.length == 0
          || screen[0].length == 0
          || x >= screen.length
          || y >= screen[0].length) {
        return false;
      }

      if (screen[x][y] == newColor) {
        return false;
      }

      fill(screen, x, y, screen[x][y], newColor);
      return true;
    }

    private void fill(int[][] screen, int x, int y, int oldColor, int newColor) {
      screen[x][y] = newColor;
      if (x > 0 && screen[x - 1][y] == oldColor) {
        fill(screen, x - 1, y, oldColor, newColor);
      }
      if (x < screen.length - 1 && screen[x + 1][y] == oldColor) {
        fill(screen, x + 1, y, oldColor, newColor);
      }
      if (y > 0 && screen[x][y - 1] == oldColor) {
        fill(screen, x, y - 1, oldColor, newColor);
      }
      if (y < screen[0].length - 1 && screen[x][y + 1] == oldColor) {
        fill(screen, x, y + 1, oldColor, newColor);
      }
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {}
}
