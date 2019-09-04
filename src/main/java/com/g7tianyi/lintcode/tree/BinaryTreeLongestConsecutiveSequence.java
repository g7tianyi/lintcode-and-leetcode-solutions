package com.g7tianyi.lintcode.tree;

import com.g7tianyi.common.TreeNode;
import com.g7tianyi.util.Logger;

/**
 * Created by g7tianyi on Sep 04, 2019
 *
 * @link https://www.lintcode.com/problem/binary-tree-longest-consecutive-sequence/description
 */
public class BinaryTreeLongestConsecutiveSequence {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int longestConsecutive(TreeNode root) {
      return traverse(root, null, 0);
    }

    private int traverse(TreeNode curr, TreeNode prev, int len) {
      if (curr == null) {
        return len;
      }
      len = (prev != null && curr.val == prev.val + 1) ? len + 1 : 1;
      return Math.max(
          len, Math.max(traverse(curr.left, curr, len), traverse(curr.right, curr, len)));
    }
  }
}
