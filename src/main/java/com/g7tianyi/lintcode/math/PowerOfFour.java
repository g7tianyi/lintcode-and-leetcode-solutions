package com.g7tianyi.lintcode.math;

/**
 * Created by g7tianyi on Aug 31, 2019
 *
 * @link https://www.lintcode.com/problem/power-of-three/note/192914
 */
public class PowerOfFour {

  public class Solution {

    // 4 : 000...000 000 100
    // 16: 000...000 100 000
    // 64: 000...100 000 000
    public boolean isPowerOfFour(int num) {
      // (num & (num - 1)) == 0 ensures that the number is power of 2
      // if we don't ensure the number is power of 2, we'd encounter the following case:
      // 000...100 100 000
      return num > 0 && (num & (num - 1)) == 0 && (num & 0x55555555) == num;
    }
  }
}
