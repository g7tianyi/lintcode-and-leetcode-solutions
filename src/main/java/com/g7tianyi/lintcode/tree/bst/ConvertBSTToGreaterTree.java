package com.g7tianyi.lintcode.tree.bst;

import com.g7tianyi.common.TreeNode;
import com.g7tianyi.util.Logger;

/**
 * Created by g7tianyi on Sep 03, 2019
 *
 * @link https://www.lintcode.com/problem/convert-bst-to-greater-tree/description
 */
public class ConvertBSTToGreaterTree {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public TreeNode convertBST(TreeNode root) {
      traverse(root, 0);
      return root;
    }

    private int traverse(TreeNode root, int largerSum) {
      if (root == null) {
        return 0;
      }

      int val = root.val;

      int rightSum = traverse(root.right, largerSum);
      root.val += (largerSum + rightSum);
      int leftSum = traverse(root.left, root.val);

      return val + leftSum + rightSum;
    }
  }
}
