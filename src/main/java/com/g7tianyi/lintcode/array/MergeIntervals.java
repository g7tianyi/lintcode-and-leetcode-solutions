package com.g7tianyi.lintcode.array;

import com.g7tianyi.util.Logger;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 28, 2019
 *
 * @link https://www.lintcode.com/problem/merge-intervals/description
 */
public class MergeIntervals {

  private static final Logger log = new Logger();

  public class Interval {

    int start, end;

    Interval(int start, int end) {
      this.start = start;
      this.end = end;
    }

    @Override
    public String toString() {
      return String.format("[%d, %d]", start, end);
    }
  }

  public class Solution {

    public List<Interval> merge(List<Interval> intervals) {

      if (intervals == null || intervals.isEmpty()) {
        return intervals;
      }

      intervals.sort(
          (o1, o2) -> {
            if (o1.start != o2.start) {
              return Integer.compare(o1.start, o2.start);
            }
            return Integer.compare(o1.end, o2.end);
          });

      List<Interval> result = new ArrayList<>();

      Interval prev = intervals.get(0);
      Interval curr;

      for (int i = 1; i < intervals.size(); ++i) {
        curr = intervals.get(i);
        // Case 1: [1, 10] => [3, 7]
        // Case 2: [1, 10] => [7, 11]
        // Case 3: [1, 10] => [12, 18]

        if (curr.end <= prev.end) {
          continue;
        }
        if (curr.start <= prev.end) { // Case like
          prev.end = curr.end;
        } else {
          result.add(prev);
          prev = curr;
        }
      }

      result.add(prev);

      return result;
    }
  }

  @AllArgsConstructor
  private static class Case {

    private List<Interval> intervals;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c = aCase -> log.info(s.merge(aCase.intervals));

    c.accept(
        new Case(
            Lists.newArrayList(
                new Interval(8, 10),
                new Interval(1, 3),
                new Interval(15, 18),
                new Interval(2, 6))));
  }
}
