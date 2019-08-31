package com.g7tianyi.lintcode.bitops;

/**
 * Created by g7tianyi on Aug 31, 2019
 *
 * @link https://www.lintcode.com/problem/power-of-three/note/192914
 */
public class PowerOfTwo {

  public class Solution {

    public boolean isPowerOfTwo(int num) {
      return num > 0 && (num & (num - 1)) == 0;
    }
  }
}
