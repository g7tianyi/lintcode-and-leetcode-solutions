package com.g7tianyi.lintcode.common;

/** Created by g7tianyi on Aug 24, 2019 */
public final class Arrays {

  public static int[] makeArrayFrom(int... args) {
    int[] arr = new int[args.length];
    int index = 0;
    for (int elem : args) {
      arr[index++] = elem;
    }
    return arr;
  }
}
