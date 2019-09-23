package com.g7tianyi.common;

/** Created by g7tianyi on Aug 28, 2019 */
public final class Numbers {

  private Numbers() {}

  public static int fromBinaryString(String s) {
    int num = 0, pow = 1, i = s.length() - 1;
    while (i >= 0) {
      if (s.charAt(i) == '1') {
        num += pow;
      }
      pow <<= 1;
      --i;
    }
    return num;
  }

  public static int nextInt() {
    return nextInt(100);
  }

  public static int nextInt(int max) {
    return (int) (max + Math.random() * max) % max;
  }

  public static int nextLong() {
    return nextInt(1000);
  }

  public static long nextLong(long max) {
    return (long) (Math.random() * max);
  }
}
