package com.g7tianyi.lintcode.tree;

import com.g7tianyi.common.TreeNode;

/**
 * Created by g7tianyi on Aug 28, 2019
 *
 * @link https://www.lintcode.com/problem/same-tree/description
 */
public class SameTree {

  public class Solution {

    public boolean isIdentical(TreeNode a, TreeNode b) {

      if (a == null && b == null) {
        return true;
      }

      if (a == null || b == null) {
        return false;
      }

      return a.val == b.val && isIdentical(a.left, b.left) && isIdentical(a.right, b.right);
    }
  }
}
