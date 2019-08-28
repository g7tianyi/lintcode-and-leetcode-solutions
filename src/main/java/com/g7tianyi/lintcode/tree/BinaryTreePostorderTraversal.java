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
 * @link https://www.lintcode.com/problem/binary-tree-preorder-traversal/description
 */
public class BinaryTreePostorderTraversal {

  private static final Logger log = new Logger();

  public class Solution {

    public class TraversalNode {

      private int val;

      private TreeNode node;
    }

    // 用非递归模拟递归的两种思路：使用栈模拟，使用递推
    public List<Integer> postorderTraversal(TreeNode root) {

      List<Integer> result = new ArrayList<>();

      Stack<TraversalNode> stack = new Stack<>();

      traverse(root, stack);

      while (!stack.empty()) {
        TraversalNode node = stack.pop();
        if (node.node == null) {
          result.add(node.val);
        } else {
          traverse(node.node, stack);
        }
      }

      return result;
    }

    private void traverse(TreeNode node, Stack<TraversalNode> stack) {

      if (node == null) {
        return;
      }

      TraversalNode valueNode = new TraversalNode();
      valueNode.val = node.val;
      stack.push(valueNode);

      if (node.right != null) {
        TraversalNode rightNode = new TraversalNode();
        rightNode.node = node.right;
        stack.push(rightNode);
      }

      if (node.left != null) {
        TraversalNode leftNode = new TraversalNode();
        leftNode.node = node.left;
        stack.push(leftNode);
      }
    }
  }

  public class RecursiveSolution {

    public List<Integer> postorderTraversal(TreeNode root) {

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
      if (root.right != null) {
        traverse(root.right, result);
      }
      result.add(root.val);
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
          List<Integer> listNodes = s.postorderTraversal(input.root);
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
