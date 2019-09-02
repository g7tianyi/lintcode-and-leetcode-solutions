package com.g7tianyi.lintcode.tree.traversal;

import com.g7tianyi.common.TreeNode;
import com.g7tianyi.util.Logger;

/**
 * Created by g7tianyi on Sep 03, 2019
 *
 * @link
 */
public class MergeTwoBinaryTrees {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {

      if (t1 == null) {
        return t2;
      }

      if (t2 == null) {
        return t1;
      }

      TreeNode node = new TreeNode(t1.val + t2.val);
      node.left = mergeTrees(t1.left, t2.left);
      node.right = mergeTrees(t1.right, t2.right);
      return node;
    }
  }
}
