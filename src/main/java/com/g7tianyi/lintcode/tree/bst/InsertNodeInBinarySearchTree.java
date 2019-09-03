package com.g7tianyi.lintcode.tree.bst;

import com.g7tianyi.common.TreeNode;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 03, 2019
 *
 * @link
 */
public class InsertNodeInBinarySearchTree {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public TreeNode insertNode(TreeNode root, TreeNode node) {
      if (root == null) {
        return node;
      }

      TreeNode curr = root, prev = null;
      while (curr != null) {
        prev = curr;
        if (curr.val < node.val) {
          curr = curr.right;
        } else {
          curr = curr.left;
        }
      }

      if (prev.val < node.val) {
        prev.right = node;
      } else {
        prev.left = node;
      }

      return root;
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
