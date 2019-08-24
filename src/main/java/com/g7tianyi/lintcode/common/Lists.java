package com.g7tianyi.lintcode.common;

import java.util.ArrayList;
import java.util.List;

/** Created by g7tianyi on Aug 24, 2019 */
public final class Lists {

  public static List<Integer> makeListsFrom(int... args) {
    List<Integer> result = new ArrayList<>();
    for (int elem : args) {
      result.add(elem);
    }
    return result;
  }
}
