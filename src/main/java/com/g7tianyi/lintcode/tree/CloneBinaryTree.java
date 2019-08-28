package com.g7tianyi.lintcode.tree;

import com.g7tianyi.common.TreeNode;
import com.g7tianyi.util.Logger;

/**
 * Created by g7tianyi on Aug 28, 2019
 *
 * @link https://www.lintcode.com/problem/clone-binary-tree/description
 */
public class CloneBinaryTree {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public TreeNode cloneTree(TreeNode root) {
      // write your code here
      if (root == null) {
        return null;
      }

      TreeNode result = new TreeNode(root.val);
      if (root.left != null) {
        result.left = cloneTree(root.left);
      }

      if (root.right != null) {
        result.right = cloneTree(root.right);
      }

      return result;
    }
  }
}
