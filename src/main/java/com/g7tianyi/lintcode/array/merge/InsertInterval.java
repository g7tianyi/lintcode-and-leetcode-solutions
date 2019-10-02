package com.g7tianyi.lintcode.array.merge;

import com.g7tianyi.common.Interval;
import com.g7tianyi.common.Strings;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 19, 2019
 *
 * @link https://www.lintcode.com/problem/insert-interval/description
 */
public class InsertInterval {

  private static final Logger log = Logger.getInstance();

  public class Solution {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {

      for (Interval interval : intervals) {
        if (newInterval.end < interval.start) {
          break;
        }
        if (newInterval.start <= interval.end) {
          newInterval.start = Math.min(newInterval.start, interval.start);
        }
        newInterval.end = Math.max(newInterval.end, interval.end);
      }

      List<Interval> result = new ArrayList<>();
      boolean inserted = false;
      Interval curr;

      for (int i = 0; i < intervals.size(); ++i) {

        curr = intervals.get(i);
        if (curr.end < newInterval.start) {
          result.add(curr);

          Interval next = null;
          if (i + 1 < intervals.size()) {
            next = intervals.get(i + 1);
          }
          if (!inserted && (next == null || next.start > newInterval.end)) {
            inserted = true;
            result.add(newInterval);
          }
        } else if (curr.start > newInterval.end) {
          Interval prev = null;
          if (i > 0) {
            prev = intervals.get(i - 1);
          }
          if (!inserted && (prev == null || prev.end < newInterval.start)) {
            inserted = true;
            result.add(newInterval);
          }

          result.add(curr);
        } else if (!inserted) {
          result.add(newInterval);
          inserted = true;
        }
      }

      return result;
    }
  }

  @AllArgsConstructor
  private static class Case {
    List<Interval> intervals;
    Interval newInterval;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c =
        aCase -> {
          log.info("%s + %s", Strings.format(aCase.intervals), aCase.newInterval);
          List<Interval> result = s.insert(aCase.intervals, aCase.newInterval);
          log.info(result);
          log.info();
        };

    c.accept(new Case(Interval.froms("[1,2] [5,9]"), Interval.from("[2,5]")));
    c.accept(new Case(Interval.froms("[1,2] [5,9]"), Interval.from("[2,4]")));
    c.accept(new Case(Interval.froms("[1,2] [5,9]"), Interval.from("[4,5]")));
    c.accept(new Case(Interval.froms("[1,2] [5,9]"), Interval.from("[4,6]")));
    c.accept(new Case(Interval.froms("[1,2] [5,6]"), Interval.from("[6,9]")));
    c.accept(new Case(Interval.froms("[3,4] [5,9]"), Interval.from("[1,3]")));
    c.accept(new Case(Interval.froms("[1,2] [5,9]"), Interval.from("[3,4]")));
    c.accept(new Case(Interval.froms("[3,4] [5,9]"), Interval.from("[1,2]")));
    c.accept(new Case(Interval.froms("[1,2] [3,4]"), Interval.from("[5,9]")));
    c.accept(new Case(Interval.froms("[1,2] [3,4] [5,6] [7,8]"), Interval.from("[4,9]")));
  }
}
