package com.g7tianyi.lintcode.tree.traversal;

import com.g7tianyi.common.TreeNode;
import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Oct 25, 2019
 *
 * @link https://www.lintcode.com/problem/binary-tree-postorder-traversal-null/description
 */
public class BinaryTreePostorderTraversalNull {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public List<Integer> postorderTraversal(TreeNode root) {
      return loop(root);
    }

    private List<Integer> loop(TreeNode root) {

      LinkedList<Integer> result = new LinkedList<>();

      if (root == null) {
        return result;
      }

      Stack<TreeNode> stack = new Stack<>();
      stack.push(root);

      while (!stack.isEmpty()) {
        TreeNode node = stack.pop();
        result.addFirst(node.val);
        if (node.left != null) {
          stack.push(node.left);
        }
        if (node.right != null) {
          stack.push(node.right);
        }
      }

      return result;
    }

    private List<Integer> recursion(TreeNode root) {

      List<Integer> result = new ArrayList<>();

      if (root == null) {
        return result;
      }

      recursion(root, result);

      return result;
    }

    private void recursion(TreeNode root, List<Integer> result) {
      if (root == null) {
        return;
      }

      if (root.left != null) {
        recursion(root.left, result);
      }
      if (root.right != null) {
        recursion(root.right, result);
      }
      result.add(root.val);
    }
  }

  private final Solution s = new Solution();

  private final Consumer<TreeNode> c =
      root -> {
        log.info(s.recursion(root));
        log.info(s.loop(root));
        log.info();
      };

  @Test
  public void test() {

    c.accept(TreeNode.createTree("1,2,3"));
    c.accept(TreeNode.createTree("1,2,#,3,4"));
    c.accept(TreeNode.createTree("1,2,#,3,4"));
    c.accept(TreeNode.createRandomTree(20));
  }
}
