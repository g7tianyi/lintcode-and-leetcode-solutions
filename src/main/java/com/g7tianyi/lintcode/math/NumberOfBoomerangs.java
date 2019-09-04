package com.g7tianyi.lintcode.math;

import com.g7tianyi.util.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by g7tianyi on Sep 04, 2019
 *
 * @link https://www.lintcode.com/problem/number-of-boomerangs/description
 */
public class NumberOfBoomerangs {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int numberOfBoomerangs(int[][] points) {

      int result = 0;

      Map<Integer, Integer> map = new HashMap<>();
      for (int[] p0 : points) {
        map.clear();
        for (int[] p1 : points) {
          int d = (p0[0] - p1[0]) * (p0[0] - p1[0]) + (p0[1] - p1[1]) * (p0[1] - p1[1]);
          map.put(d, map.getOrDefault(d, 0) + 1);
        }

        for (Integer count : map.values()) {
          if (count > 1) {
            result += count * (count - 1);
          }
        }
      }

      return result;
    }
  }
}
