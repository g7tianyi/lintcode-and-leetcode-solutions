package com.g7tianyi.common;

/** Created by g7tianyi on Aug 28, 2019 */
public final class Numbers {

  private Numbers() {}

  public static int nextInt() {
    return nextInt(100);
  }

  public static int nextInt(int max) {
    return (int) (Math.random() * max);
  }

  public static int nextLong() {
    return nextInt(1000);
  }

  public static long nextLong(long max) {
    return (long) (Math.random() * max);
  }
}
