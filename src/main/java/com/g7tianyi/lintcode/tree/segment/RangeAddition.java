package com.g7tianyi.lintcode.tree.segment;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by g7tianyi on Oct 16, 2019
 *
 * @link https://www.lintcode.com/problem/range-addition/description
 */
public class RangeAddition {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int[] getModifiedArray(int length, int[][] updates) {
      int[] result = new int[length];
      for (int[] update : updates) {
        for (int i = update[0]; i <= update[1]; ++i) {
          result[i] += update[2];
        }
      }
      return result;
    }
  }

  public class SegTreeSolution {

    class SegTree {

      private int start, end, delta;
      private SegTree left, right;

      public SegTree(int start, int end) {
        this.start = start;
        this.end = end;
        this.delta = 0;

        if (this.start < this.end) {
          initChildren();
        }
      }

      public void update(int start, int end, int delta) {
        if (this.start == start && this.end == end) {
          this.delta += delta;
          return;
        }

        int mid = this.start + ((this.end - this.start) >> 1);
        if (end <= mid) {
          this.left.update(start, end, delta);
        } else if (start > mid) {
          this.right.update(start, end, delta);
        } else {
          this.left.update(start, mid, delta);
          this.right.update(mid + 1, end, delta);
        }
      }

      public int query(int pos) {
        int value = 0;
        SegTree node = this;
        while (node != null) {
          value += node.delta;
          if (node.start == pos && node.end == pos) {
            break;
          }

          int mid = node.start + ((node.end - node.start) >> 1);
          if (mid < pos) {
            node = node.right;
          } else {
            node = node.left;
          }
        }
        return value;
      }

      private void initChildren() {
        int mid = start + ((end - start) >> 1);
        this.left = new SegTree(start, mid);
        this.right = new SegTree(mid + 1, end);
      }

      public void print() {
        Queue<SegTree> queue = new LinkedList<>();
        queue.offer(this);
        queue.offer(null);
        while (!queue.isEmpty()) {
          SegTree node = queue.poll();
          if (node == null) {
            System.out.println();
            if (!queue.isEmpty()) {
              queue.offer(null);
            }
          } else {
            System.out.printf("[%d, %d, %d] ", node.start, node.end, node.delta);
            if (node.left != null) {
              queue.offer(node.left);
            }
            if (node.right != null) {
              queue.offer(node.right);
            }
          }
        }
      }
    }

    public int[] getModifiedArray(int length, int[][] updates) {
      SegTree segTree = new SegTree(0, length - 1);
      for (int[] update : updates) {
        segTree.update(update[0], update[1], update[2]);
      }
      segTree.print();

      int[] result = new int[length];
      for (int i = 0; i < length; ++i) {
        result[i] = segTree.query(i);
      }
      return result;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    //    log.info(
    //        s.getModifiedArray(
    //            5,
    //            new int[][] {
    //              {1, 3, 2},
    //              {2, 4, 3},
    //              {0, 2, -2},
    //            }));
    log.info(
        s.getModifiedArray(
            10,
            new int[][] {
              {2, 4, 6},
              {5, 6, 8},
              {1, 9, -4}
            }));
  }
}
