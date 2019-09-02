package com.g7tianyi.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** Created by g7tianyi on Aug 24, 2019 */
public final class Lists {

  public static List<Integer> from(int... args) {
    List<Integer> result = new ArrayList<>();
    for (int elem : args) {
      result.add(elem);
    }
    return result;
  }

  public static List<String> from(String... args) {
    return new ArrayList<>(Arrays.asList(args));
  }
}
