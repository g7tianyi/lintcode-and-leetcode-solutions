package com.g7tianyi.lintcode.tree.segment;

import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Oct 21, 2019
 *
 * @link https://www.lintcode.com/problem/segment-tree-query/description
 */
public class SegmentTreeQuery2 {

  private static final Logger log = Logger.getInstance();

  public class SegmentTreeNode {

    public int start, end, count;
    public SegmentTreeNode left, right;

    public SegmentTreeNode(int start, int end, int count) {
      this.start = start;
      this.end = end;
      this.count = count;
      this.left = this.right = null;
    }
  }

  public class Solution {

    public int query(SegmentTreeNode root, int start, int end) {
      if (root == null) {
        return 0;
      }

      if (start < root.start) {
        start = root.start;
      }
      if (end > root.end) {
        end = root.end;
      }

      if (start == root.start && end == root.end) {
        return root.count;
      }

      int mid = root.start + ((root.end - root.start) >> 1);
      if (start > mid) {
        return query(root.right, start, end);
      }
      if (end <= mid) {
        return query(root.left, start, end);
      }
      return query(root.left, start, mid) + query(root.right, mid + 1, end);
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {}
}
