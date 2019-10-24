package com.g7tianyi.lintcode.array.boring;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by g7tianyi on Oct 24, 2019
 *
 * @link https://www.lintcode.com/problem/parking-dilemma/description
 */
public class ParkingDilemma {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int ParkingDilemma(int[] cars, int k) {
      Arrays.sort(cars);
      int result = Integer.MAX_VALUE;
      for (int i = 0; i + k - 1 < cars.length; ++i) {
        result = Math.min(result, cars[i + k - 1] - cars[i] + 1);
      }
      return result;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.ParkingDilemma(com.g7tianyi.common.Arrays.from(2, 10, 8, 17), 3));
  }
}
