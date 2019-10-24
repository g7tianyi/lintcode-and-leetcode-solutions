package com.g7tianyi.lintcode.math;

import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Oct 24, 2019
 *
 * @link https://www.lintcode.com/problem/time-angle/description
 */
public class TimeAngle {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public float timeAngle(int h, int m) {
      // m / 60 * 360
      // h / 12 * 360 + 30 * m / 60
      float result = Math.abs(m * 6 - h * 30 - (float) m / 2);
      if (result > 180) {
        return 360 - result;
      }
      return result;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.timeAngle(12, 0));
    log.info(s.timeAngle(1, 0));

    log.info(s.timeAngle(7, 7));
  }
}
