package com.g7tianyi.lintcode.tree.segment;

import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Oct 21, 2019
 *
 * @link https://www.lintcode.com/problem/segment-tree-build/description
 */
public class SegmentTreeBuild {

  private static final Logger log = Logger.getInstance();

  public class SegmentTreeNode {

    public int start, end;
    public SegmentTreeNode left, right;

    public SegmentTreeNode(int start, int end) {
      this.start = start;
      this.end = end;
      this.left = this.right = null;
    }
  }

  public class Solution {

    public SegmentTreeNode build(int start, int end) {
      if (start > end) {
        return null;
      }

      SegmentTreeNode root = new SegmentTreeNode(start, end);
      build(root);
      return root;
    }

    private void build(SegmentTreeNode root) {
      if (root.start == root.end) {
        return;
      }

      int mid = root.start + ((root.end - root.start) >> 1);
      root.left = new SegmentTreeNode(root.start, mid);
      root.right = new SegmentTreeNode(mid + 1, root.end);

      build(root.left);
      build(root.right);
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {}
}
