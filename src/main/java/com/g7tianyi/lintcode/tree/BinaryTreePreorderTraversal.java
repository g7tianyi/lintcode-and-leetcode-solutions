package com.g7tianyi.lintcode.tree;

import com.g7tianyi.lintcode.common.TreeNode;
import com.g7tianyi.lintcode.util.Console;
import com.g7tianyi.lintcode.util.Log;
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
public class BinaryTreePreorderTraversal {

  private static final Log log = new Log();

  public class Solution {

    // 用非递归模拟递归的两种思路：使用栈模拟，使用递推
    public List<Integer> preorderTraversal(TreeNode root) {

      List<Integer> result = new ArrayList<>();
      if (root == null) {
        return result;
      }

      result.add(root.val);

      Stack<TreeNode> stack = new Stack<>();
      stack.push(root.right);
      stack.push(root.left);

      while (!stack.empty()) {
        TreeNode node = stack.pop();
        if (node != null) {
          result.add(node.val);
          stack.push(node.right);
          stack.push(node.left);
        }
      }

      return result;
    }
  }

  public class RecursiveSolution {

    public List<Integer> preorderTraversal(TreeNode root) {

      List<Integer> result = new ArrayList<>();

      traverse(root, result);

      return result;
    }

    private void traverse(TreeNode root, List<Integer> result) {
      if (root == null) {
        return;
      }

      result.add(root.val);
      if (root.left != null) {
        traverse(root.left, result);
      }
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

    Consumer<Input> runner =
        input -> {
          List<Integer> listNodes = s.preorderTraversal(input.root);
          Console.log(listNodes);
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
