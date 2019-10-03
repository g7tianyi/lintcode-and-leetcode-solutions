package com.g7tianyi.lintcode.tree;

import com.g7tianyi.common.TreeNode;
import com.g7tianyi.util.Logger;

/**
 * Created by g7tianyi on Oct 03, 2019
 *
 * @link https://www.lintcode.com/problem/lowest-common-ancestor-of-a-binary-tree/description
 */
public class LowestCommonAncestorOfABinaryTree {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    // 假设给出的两个节点都在树中存在
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode A, TreeNode B) {
      if (root == A || root == B) {
        return root;
      }
      boolean aInLeft = root.left != null && contains(root.left, A);
      boolean bInRight = root.right != null && contains(root.right, B);
      if ((aInLeft && bInRight) || (!aInLeft && !bInRight)) {
        return root;
      } else if (aInLeft) {
        return lowestCommonAncestor(root.left, A, B);
      } else {
        return lowestCommonAncestor(root.right, A, B);
      }
    }

    private boolean contains(TreeNode root, TreeNode node) {
      if (root == node) {
        return true;
      }
      if (root.left != null && contains(root.left, node)) {
        return true;
      }
      return root.right != null && contains(root.right, node);
    }
  }
}
