package com.g7tianyi.common;

import java.util.ArrayList;
import java.util.List;

/** Created by g7tianyi on Sep 01, 2019 */
public class Interval {

  public int start;

  public int end;

  public Interval(int start, int end) {
    this.start = start;
    this.end = end;
  }

  @Override
  public String toString() {
    return String.format("[%d, %d]", start, end);
  }

  public static List<Interval> from(String s) {
    List<Interval> result = new ArrayList<>();
    String[] intervals = s.replaceAll("\\[", "").replaceAll("\\]", "").split("\\s");
    for (String sInterval : intervals) {
      String[] elems = sInterval.split(",");
      result.add(new Interval(Integer.valueOf(elems[0]), Integer.valueOf(elems[1])));
    }
    return result;
  }
}
