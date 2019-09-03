package com.g7tianyi.lintcode.tree;

import com.g7tianyi.common.TreeNode;
import com.g7tianyi.util.Logger;

/**
 * Created by g7tianyi on Sep 03, 2019
 *
 * @link https://www.lintcode.com/problem/minimum-absolute-difference-in-bst/description
 */
public class MinimumAbsoluteDifferenceInBST {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    class Info {
      Integer prevValue;
      int minDifference;

      public Info() {
        this(null, Integer.MAX_VALUE);
      }

      public Info(Integer prevValue, int minDifference) {
        this.prevValue = prevValue;
        this.minDifference = minDifference;
      }
    }

    public int getMinimumDifference(TreeNode root) {
      Info info = new Info();
      inorderTraversal(root, info);
      return info.minDifference;
    }

    private void inorderTraversal(TreeNode root, Info info) {

      if (root == null) {
        return;
      }

      inorderTraversal(root.left, info);

      if (info.prevValue != null) {
        info.minDifference = Math.min(info.minDifference, Math.abs(info.prevValue - root.val));
      }

      info.prevValue = root.val; // This is the key code..
      inorderTraversal(root.right, info);
    }
  }
}
