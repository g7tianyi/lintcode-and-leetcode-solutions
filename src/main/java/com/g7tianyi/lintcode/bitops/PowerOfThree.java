package com.g7tianyi.lintcode.bitops;

/**
 * Created by g7tianyi on Aug 31, 2019
 *
 * @link https://www.lintcode.com/problem/power-of-three/note/192914
 */
public class PowerOfThree {

  public static class Solution {

    public boolean isPowerOfThree(int n) {
      // 由于输入是int，int范围内最大的3次方数为1162261467，那么我们只要看这个数能否被n整除即可
      // 其实这题真是无聊...
      return n > 0 && 1162261467 % n == 0;
    }
  }
}
