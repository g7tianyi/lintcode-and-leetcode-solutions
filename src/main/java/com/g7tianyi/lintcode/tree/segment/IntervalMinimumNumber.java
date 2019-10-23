package com.g7tianyi.lintcode.tree.segment;

import com.g7tianyi.common.Interval;
import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by g7tianyi on Oct 23, 2019
 *
 * @link https://www.lintcode.com/problem/interval-minimum-number/description
 */
public class IntervalMinimumNumber {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    private class SegTree {

      private int start, end, min;
      private SegTree left, right;

      public SegTree(int start, int end) {
        this.start = start;
        this.end = end;
      }
    }

    public List<Integer> intervalMinNumber(int[] values, List<Interval> queries) {

      SegTree root = build(values, 0, values.length - 1);

      List<Integer> result = new ArrayList<>();

      for (Interval interval : queries) {
        int start = interval.start, end = interval.end;
        if (start > end) {
          start = interval.end;
          end = interval.start;
        }
        result.add(query(root, start, end));
      }

      return result;
    }

    private SegTree build(int[] values, int start, int end) {

      SegTree node = new SegTree(start, end);

      if (start == end) {
        node.min = values[start];
        return node;
      }

      int mid = start + ((end - start) >> 1);

      node.left = build(values, start, mid);
      node.right = build(values, mid + 1, end);
      node.min = Math.min(node.left.min, node.right.min);
      return node;
    }

    private int query(SegTree root, int start, int end) {

      if (start == root.start && end == root.end) {
        return root.min;
      }

      int mid = root.start + ((root.end - root.start) >> 1);
      if (end <= mid) {
        return query(root.left, start, end);
      }
      if (start > mid) {
        return query(root.right, start, end);
      }
      return Math.min(query(root.left, start, mid), query(root.right, mid + 1, end));
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {}
}
