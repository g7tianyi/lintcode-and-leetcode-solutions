package com.g7tianyi.lintcode.tree.segment;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.common.Interval;
import com.g7tianyi.util.Logger;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by g7tianyi on Oct 26, 2019
 *
 * @link https://www.lintcode.com/problem/interval-sum/description
 */
public class IntervalSum {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    private class SegTree {

      private int start, end;
      private long sum;
      private SegTree left, right;

      public SegTree(int start, int end) {
        this.start = start;
        this.end = end;
      }
    }

    public List<Long> intervalSum(int[] values, List<Interval> queries) {
      SegTree root = build(values, 0, values.length - 1);
      List<Long> result = new ArrayList<>();
      for (Interval interval : queries) {
        result.add(query(root, interval.start, interval.end));
      }
      return result;
    }

    private SegTree build(int[] values, int start, int end) {

      SegTree node = new SegTree(start, end);

      if (start == end) {
        node.sum = values[start];
        return node;
      }

      int mid = start + ((end - start) >> 1);

      node.left = build(values, start, mid);
      node.right = build(values, mid + 1, end);
      node.sum = node.left.sum + node.right.sum;
      return node;
    }

    private long query(SegTree root, int start, int end) {

      if (start == root.start && end == root.end) {
        return root.sum;
      }

      int mid = root.start + ((root.end - root.start) >> 1);
      if (end <= mid) {
        return query(root.left, start, end);
      }
      if (start > mid) {
        return query(root.right, start, end);
      }
      return query(root.left, start, mid) + query(root.right, mid + 1, end);
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(
        s.intervalSum(
            Arrays.from(1, 2, 7, 8, 5),
            Lists.newArrayList(new Interval(0, 4), new Interval(1, 2), new Interval(2, 4))));
  }
}
