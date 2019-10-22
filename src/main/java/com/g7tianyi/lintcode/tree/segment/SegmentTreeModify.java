package com.g7tianyi.lintcode.tree.segment;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.Stack;

/**
 * Created by g7tianyi on Oct 21, 2019
 *
 * @link https://www.lintcode.com/problem/segment-tree-modify/description
 */
public class SegmentTreeModify {

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

    public void modify(SegmentTreeNode root, int index, int value) {

      if (root == null || root.start > index || root.end < index) {
        return;
      }

      Stack<SegmentTreeNode> stack = new Stack<>();
      stack.push(root);

      SegmentTreeNode node, next;
      while (true) {
        node = stack.peek();
        if (node.start == index && node.end == index) {
          node.max = value;
          break;
        }

        int mid = node.start + ((node.end - node.start) >> 1);
        if (index <= mid) {
          next = node.left;
        } else {
          next = node.right;
        }
        if (next != null) {
          stack.push(next);
        }
      }

      int max = value;
      while (!stack.isEmpty()) {
        node = stack.pop();
        if (node.left != null) {
          max = Math.max(max, node.left.max);
        }
        if (node.right != null) {
          max = Math.max(max, node.right.max);
        }
        node.max = max;
      }
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {}
}
