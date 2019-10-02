package com.g7tianyi.lintcode.array.merge;

import com.g7tianyi.common.Interval;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 1, 2019
 *
 * @link https://www.lintcode.com/problem/merge-two-sorted-interval-lists/description
 */
public class MergeTwoSortedIntervalLists {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public List<Interval> mergeTwoInterval(List<Interval> list1, List<Interval> list2) {

      if (list1 == null || list1.isEmpty()) {
        return list2;
      }
      if (list2 == null || list2.isEmpty()) {
        return list1;
      }

      List<Interval> result = new ArrayList<>();
      Interval prev = null, curr;
      Interval inter1, inter2;
      int i = 0, j = 0;

      while (i < list1.size() && j < list2.size()) {

        inter1 = list1.get(i);
        inter2 = list2.get(j);

        if (inter1.start < inter2.start) {
          curr = inter1;
          ++i;
        } else {
          curr = inter2;
          ++j;
        }

        if (prev == null) {
          prev = curr;
          continue;
        }

        if (curr.start > prev.end) {
          result.add(prev);
          prev = curr;
        } else if (prev.end < curr.end) {
          prev.end = curr.end;
        }
      }

      while (i < list1.size()) {
        curr = list1.get(i);
        if (curr.start > prev.end) {
          result.add(prev);
          prev = curr;
        } else if (prev.end < curr.end) {
          prev.end = curr.end;
        }
        ++i;
      }

      while (j < list2.size()) {
        curr = list2.get(j);
        if (curr.start > prev.end) {
          result.add(prev);
          prev = curr;
        } else if (prev.end < curr.end) {
          prev.end = curr.end;
        }
        ++j;
      }

      result.add(prev);

      return result;
    }
  }

  @AllArgsConstructor
  private static class Case {

    private List<Interval> list1;
    private List<Interval> list2;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c =
        aCase -> {
          log.info(aCase.list1);
          log.info(aCase.list2);
          log.info(s.mergeTwoInterval(aCase.list1, aCase.list2));
          log.info();
        };

    c.accept(new Case(Interval.froms("[1,2] [3,4]"), Interval.froms("[2,3] [5,6]")));
    c.accept(new Case(Interval.froms("[1,2] [3,4]"), Interval.froms("[4,5] [6,7]")));
    c.accept(new Case(Interval.froms("[1,2] [3,5] [7,9] [10,12]"), Interval.froms("[2,4] [5,6]")));
    c.accept(new Case(Interval.froms("[1,2]"), Interval.froms("[2,4] [5,6] [10,12]")));
    c.accept(new Case(Interval.froms("[1,7] [8,20]"), Interval.froms("[2,4] [5,6] [10,12]")));
  }
}
