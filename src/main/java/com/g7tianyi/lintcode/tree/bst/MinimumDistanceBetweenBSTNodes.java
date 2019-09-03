package com.g7tianyi.lintcode.tree.bst;

import com.g7tianyi.common.TreeNode;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 03, 2019
 *
 * @link https://www.lintcode.com/problem/minimum-distance-between-bst-nodes/description
 */
public class MinimumDistanceBetweenBSTNodes {

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

    public int minDiffInBST(TreeNode root) {
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

      info.prevValue = root.val;
      inorderTraversal(root.right, info);
    }
  }

  @AllArgsConstructor
  private class Case {}

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c = aCase -> {};
  }
}
