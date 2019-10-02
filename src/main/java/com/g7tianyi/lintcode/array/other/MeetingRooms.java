package com.g7tianyi.lintcode.array.other;

import com.g7tianyi.common.Interval;
import com.g7tianyi.util.Logger;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 31, 2019
 *
 * @link https://www.lintcode.com/problem/meeting-rooms/description
 */
public class MeetingRooms {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public boolean canAttendMeetings(List<Interval> intervals) {

      if (intervals == null || intervals.isEmpty()) {
        return false;
      }

      intervals.sort(
          new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
              if (o1.start != o2.start) {
                return Integer.compare(o1.start, o2.start);
              }
              return Integer.compare(o1.end, o2.end);
            }
          });

      int prevEnd = intervals.get(0).end;
      for (int i = 1; i < intervals.size(); ++i) {
        Interval interval = intervals.get(i);
        if (interval.start < prevEnd) {
          return false;
        }
      }

      return true;
    }
  }

  @AllArgsConstructor
  private static class Case {

    private List<Interval> intervals;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c = aCase -> log.info(s.canAttendMeetings(aCase.intervals));

    c.accept(
        new Case(
            Lists.newArrayList(new Interval(0, 30), new Interval(5, 10), new Interval(15, 20))));
    c.accept(new Case(Lists.newArrayList(new Interval(5, 8), new Interval(9, 15))));
  }
}
