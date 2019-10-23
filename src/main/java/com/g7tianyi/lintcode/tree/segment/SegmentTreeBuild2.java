package com.g7tianyi.lintcode.tree.segment;

import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Oct 22, 2019
 *
 * @link https://www.lintcode.com/problem/segment-tree-build-ii/description
 */
public class SegmentTreeBuild2 {

  private static final Logger log = Logger.getInstance();

  public class SegmentTreeNode {
    public int start, end, max;
    public SegmentTreeNode left, right;

    public SegmentTreeNode(int start, int end, int max) {
      this.start = start;
      this.end = end;
      this.max = max;
      this.left = this.right = null;
    }
  }

  public class Solution {

    public SegmentTreeNode build(int[] values) {

      if (values == null || values.length == 0) {
        return null;
      }

      SegmentTreeNode root = new SegmentTreeNode(0, values.length - 1, -1);

      build(root, values, 0, values.length - 1);

      return root;
    }

    private void build(SegmentTreeNode root, int[] values, int start, int end) {

      int max = values[start];
      if (start == end) {
        root.max = max;
        return;
      }

      int mid = start + ((end - start) >> 1);
      if (start <= mid) {
        root.left = new SegmentTreeNode(start, mid, -1);
        build(root.left, values, start, mid);
        max = Math.max(max, root.left.max);
      }
      if (mid < end) {
        root.right = new SegmentTreeNode(mid + 1, end, -1);
        build(root.right, values, mid + 1, end);
        max = Math.max(max, root.right.max);
      }
      root.max = max;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {}
}
