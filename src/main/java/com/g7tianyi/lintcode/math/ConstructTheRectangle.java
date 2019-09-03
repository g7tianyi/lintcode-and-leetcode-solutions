package com.g7tianyi.lintcode.math;

import com.g7tianyi.common.Strings;
import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 03, 2019
 *
 * @link https://www.lintcode.com/problem/construct-the-rectangle/description
 */
public class ConstructTheRectangle {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int[] constructRectangle(int area) {

      int L = (int) Math.ceil(Math.sqrt(area)), W;
      while (true) {
        if (area % L == 0) {
          W = area / L;
          break;
        }
        ++L;
      }
      return new int[] {L, W};
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Integer> c =
        area -> log.info("%d => %s", area, Strings.format(s.constructRectangle(area)));

    c.accept(4);
    c.accept(5);
    c.accept(8);
    c.accept(12);
    c.accept(15);
    c.accept(16);
  }
}
