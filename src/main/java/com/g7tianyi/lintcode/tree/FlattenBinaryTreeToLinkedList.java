package com.g7tianyi.lintcode.tree;

import com.g7tianyi.common.TreeNode;
import com.g7tianyi.util.Logger;

/**
 * Created by g7tianyi on Aug 29, 2019
 *
 * @link https://www.lintcode.com/problem/flatten-binary-tree-to-linked-list/description
 */
public class FlattenBinaryTreeToLinkedList {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public void flatten(TreeNode root) {
      // write your code here
      if (root == null) {
        return;
      }

      flatten(root.left);
      flatten(root.right);

      if (root.left != null) {
        TreeNode node = root.left;
        while (node.right != null) {
          node = node.right;
        }
        node.right = root.right;

        root.right = root.left;
        root.left = null;
      }
    }
  }
}
