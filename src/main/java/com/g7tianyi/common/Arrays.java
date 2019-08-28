package com.g7tianyi.common;

import static com.g7tianyi.common.Numbers.nextInt;

/** Created by g7tianyi on Aug 24, 2019 */
public final class Arrays {

  public static int[] from(int... args) {
    int[] arr = new int[args.length];
    int index = 0;
    for (int elem : args) {
      arr[index++] = elem;
    }
    return arr;
  }

  public static int[] of(int len) {
    return of(len, len + 1);
  }

  public static int[] of(int len, int max) {
    int[] arr = new int[len];
    for (int i = 0; i < len; ++i) {
      arr[i] = nextInt(max);
    }
    return arr;
  }

  public static int[] sortedArrayOf(int len, int max) {
    int[] arr = of(len, max);
    java.util.Arrays.sort(arr);
    return arr;
  }

  public static char[] from(char... args) {
    char[] arr = new char[args.length];
    int index = 0;
    for (char elem : args) {
      arr[index++] = elem;
    }
    return arr;
  }
}
