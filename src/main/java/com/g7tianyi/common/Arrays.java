package com.g7tianyi.common;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import static com.g7tianyi.common.Numbers.nextInt;

/** Created by g7tianyi on Aug 24, 2019 */
public final class Arrays {

  public static int[] from(String filename) throws IOException {

    String content =
        FileUtils.readFileToString(new File(filename), "UTF-8")
            .replaceAll("\\[", "")
            .replaceAll("]", "");

    String[] elems = content.split(",");

    int[] result = new int[elems.length];
    int pos = 0;
    for (String elem : elems) {
      result[pos++] = Integer.parseInt(elem);
    }
    return result;
  }

  public static int[] from(int... args) {
    int[] arr = new int[args.length];
    int index = 0;
    for (int elem : args) {
      arr[index++] = elem;
    }
    return arr;
  }

  public static int[] random(int len, int max) {
    int[] arr = new int[len];
    Random random = new Random();
    for (int i = 0; i < len; ++i) {
      arr[i] = random.nextInt(max);
    }
    return arr;
  }

  public static int[] randomNatureNumbers(int len, int max) {
    int[] arr = new int[len];
    Random random = new Random();
    for (int i = 0; i < len; ++i) {
      arr[i] = random.nextInt(max);
      if (random.nextBoolean()) {
        arr[i] *= -1;
      }
    }
    return arr;
  }

  public static <T> void shuffle(T[] values) {
    Random random = new Random();
    for (int i = 0; i < values.length; i++) {
      int pos = random.nextInt(values.length);
      T temp = values[i];
      values[i] = values[pos];
      values[pos] = temp;
    }
  }

  public static void shuffle(int[] values) {
    Random random = new Random();
    for (int i = 0; i < values.length; i++) {
      int pos = random.nextInt(values.length);
      int temp = values[i];
      values[i] = values[pos];
      values[pos] = temp;
    }
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
