package com.g7tianyi.lintcode.tree.traversal;

import com.g7tianyi.common.TreeNode;
import com.g7tianyi.util.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by g7tianyi on Sep 03, 2019
 *
 * @link https://www.lintcode.com/problem/increasing-order-search-tree/description
 */
public class IncreasingOrderSearchTree {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public TreeNode increasingBST(TreeNode root) {

      List<Integer> values = new ArrayList<>();
      inorderTraversal(root, values);

      if (values.isEmpty()) {
        return null;
      }

      TreeNode curr = new TreeNode(values.get(values.size() - 1)), node;
      for (int i = values.size() - 2; i >= 0; --i) {
        node = new TreeNode(values.get(i));
        node.right = curr;
        curr = node;
      }
      return curr;
    }

    private void inorderTraversal(TreeNode root, List<Integer> values) {

      if (root == null) {
        return;
      }

      if (root.left != null) {
        inorderTraversal(root.left, values);
      }

      values.add(root.val);

      if (root.right != null) {
        inorderTraversal(root.right, values);
      }
    }
  }
}
