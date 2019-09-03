package com.g7tianyi.lintcode.tree;

import com.g7tianyi.common.TreeNode;
import com.g7tianyi.util.Logger;

import java.util.Stack;

/**
 * Created by g7tianyi on Sep 03, 2019
 *
 * @link https://www.lintcode.com/problem/invert-binary-tree/description
 */
public class InvertBinaryTree {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public void invertBinaryTree(TreeNode root) {

      invertBinaryTreeRecursively(root);
    }

    private void invertBinaryTreeRecursively(TreeNode root) {
      if (root == null) {
        return;
      }

      TreeNode left = root.left;
      invertBinaryTreeRecursively(left);

      TreeNode right = root.right;
      invertBinaryTreeRecursively(right);

      root.left = right;
      root.right = left;
    }

    private class NodeInfo {
      TreeNode rootNode;
      TreeNode leftNode;
      TreeNode rightNode;

      public NodeInfo(TreeNode rootNode, TreeNode leftNode, TreeNode rightNode) {
        this.rootNode = rootNode;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
      }
    }

    private void invertBinaryTreeLooply(TreeNode root) {
      if (root == null) {
        return;
      }

      Stack<NodeInfo> stack = new Stack<>();
      stack.push(new NodeInfo(root, root.left, root.right));

      while (!stack.isEmpty()) {
        NodeInfo nodeInfo = stack.pop();
        nodeInfo.rootNode.left = nodeInfo.rightNode;
        nodeInfo.rootNode.right = nodeInfo.leftNode;

        if (nodeInfo.leftNode != null) {
          stack.push(
              new NodeInfo(nodeInfo.leftNode, nodeInfo.leftNode.left, nodeInfo.leftNode.right));
        }

        if (nodeInfo.rightNode != null) {
          stack.push(
              new NodeInfo(nodeInfo.rightNode, nodeInfo.rightNode.left, nodeInfo.rightNode.right));
        }
      }
    }
  }
}
