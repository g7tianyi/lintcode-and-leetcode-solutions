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

  public static Interval from(String s) {
    s = s.replaceAll("\\[", "").replaceAll("]", "");
    String[] elems = s.split(",");
    return new Interval(Integer.parseInt(elems[0]), Integer.parseInt(elems[1]));
  }

  public static List<Interval> froms(String s) {
    List<Interval> result = new ArrayList<>();
    String[] intervals = s.replaceAll("\\[", "").replaceAll("]", "").split("\\s");
    for (String sInterval : intervals) {
      result.add(Interval.from(sInterval));
    }
    return result;
  }
}
