package com.g7tianyi.lintcode.tree.bst;

import com.g7tianyi.common.TreeNode;
import com.g7tianyi.util.Logger;

/**
 * Created by g7tianyi on Sep 03, 2019
 *
 * @link https://www.lintcode.com/problem/lowest-common-ancestor-of-a-binary-search-tree/description
 */
public class LowestCommonAncestorOfBinarySearchTree {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

      TreeNode large = p, small = q;
      if (p.val < q.val) {
        large = q;
        small = p;
      }

      if (root.val > large.val) {
        // p and q shall lie in the left sub-tree
        return lowestCommonAncestor(root.left, small, large);
      }

      if (root.val < small.val) {
        // p and q shall lie in the right sub-tree
        return lowestCommonAncestor(root.right, small, large);
      }

      return root;
    }
  }
}
