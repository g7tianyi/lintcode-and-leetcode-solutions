package com.g7tianyi.lintcode.tree.traversal;

import com.g7tianyi.common.TreeNode;

/**
 * Created by g7tianyi on Sep 1, 2019
 *
 * @link https://www.lintcode.com/problem/binary-tree-maximum-node/description
 */
public class BinaryTreeMaximumNode {

  public class Solution {

    public TreeNode maxNode(TreeNode root) {

      if (root == null) {
        return null;
      }

      TreeNode maxLeft = null;
      if (root.left != null) {
        maxLeft = maxNode(root.left);
      }
      TreeNode maxRight = null;
      if (root.right != null) {
        maxRight = maxNode(root.right);
      }

      TreeNode result = root;
      if (maxLeft != null && maxLeft.val > result.val) {
        result = maxLeft;
      }
      if (maxRight != null && maxRight.val > result.val) {
        result = maxRight;
      }
      return result;
    }
  }
}
