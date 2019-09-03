package com.g7tianyi.lintcode.tree;

import com.g7tianyi.common.TreeNode;
import com.g7tianyi.util.Logger;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by g7tianyi on Sep 03, 2019
 *
 * @link https://www.lintcode.com/problem/symmetric-tree/description
 */
public class SymmetricTree {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public boolean isSymmetric(TreeNode root) {
      if (root == null) {
        return true;
      }

      Queue<TreeNode> nodeQueue1 = new LinkedList<>();
      Queue<TreeNode> nodeQueue2 = new LinkedList<>();

      nodeQueue1.offer(root.left);
      nodeQueue2.offer(root.right);

      while (!nodeQueue1.isEmpty() && !nodeQueue2.isEmpty()) {
        TreeNode node1 = nodeQueue1.poll();
        TreeNode node2 = nodeQueue2.poll();
        if (node1 == null && node2 == null) {
          continue;
        }
        if (node1 == null || node2 == null || node1.val != node2.val) {
          return false;
        }

        nodeQueue1.offer(node1.left);
        nodeQueue1.offer(node1.right);
        nodeQueue2.offer(node2.right);
        nodeQueue2.offer(node2.left);
      }
      return true;
    }
  }

  public class RecursiveSolution {

    public boolean isSymmetric(TreeNode root) {
      if (root == null) {
        return true;
      }
      return isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode left, TreeNode right) {
      if (left == null && right == null) {
        return true;
      }

      if (left == null || right == null || left.val != right.val) {
        return false;
      }

      return isSymmetric(left.right, right.left) && isSymmetric(left.left, right.right);
    }
  }
}
