package com.g7tianyi.lintcode.tree;

import com.g7tianyi.common.TreeNode;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 25, 2019
 *
 * @link https://www.lintcode.com/problem/binary-tree-inorder-traversal/description
 */
public class BinaryTreeInorderTraversal {

  private static final Logger log = new Logger();

  public class Solution {

    private class StackNode {

      private int val;

      private TreeNode node;

      public StackNode(int val) {
        this.val = val;
      }

      public StackNode(TreeNode node) {
        this.node = node;
      }
    }

    // 用非递归模拟递归的两种思路：使用栈模拟，使用递推
    public List<Integer> inorderTraversal(TreeNode root) {

      List<Integer> result = new ArrayList<>();
      if (root == null) {
        return result;
      }

      Stack<StackNode> stack = new Stack<>();
      if (root.right != null) {
        stack.push(new StackNode(root.right));
      }
      stack.push(new StackNode(root.val));
      if (root.left != null) {
        stack.push(new StackNode(root.left));
      }

      while (!stack.empty()) {
        StackNode stackNode = stack.pop();
        if (stackNode.node != null) {
          if (stackNode.node.right != null) {
            stack.push(new StackNode(stackNode.node.right));
          }
          stack.push(new StackNode(stackNode.node.val));
          if (stackNode.node.left != null) {
            stack.push(new StackNode(stackNode.node.left));
          }
        } else {
          result.add(stackNode.val);
        }
      }

      return result;
    }
  }

  public class RecursiveSolution {

    public List<Integer> inorderTraversal(TreeNode root) {

      List<Integer> result = new ArrayList<>();

      traverse(root, result);

      return result;
    }

    private void traverse(TreeNode root, List<Integer> result) {
      if (root == null) {
        return;
      }

      if (root.left != null) {
        traverse(root.left, result);
      }

      result.add(root.val);

      if (root.right != null) {
        traverse(root.right, result);
      }
    }
  }

  @AllArgsConstructor
  private static final class Input {

    private TreeNode root;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    // RecursiveSolution s = new RecursiveSolution();

    Consumer<Input> runner =
        input -> {
          List<Integer> listNodes = s.inorderTraversal(input.root);
          log.info(listNodes);
          log.info();
        };

    TreeNode root = new TreeNode(1);
    TreeNode node1 = new TreeNode(2);
    TreeNode node2 = new TreeNode(3);
    TreeNode node3 = new TreeNode(4);
    root.left = node1;
    root.right = node2;
    node1.left = node3;

    runner.accept(new Input(root));

    root.left = null;
    root.right = node1;
    node1.left = node2;
    runner.accept(new Input(root));
  }
}
