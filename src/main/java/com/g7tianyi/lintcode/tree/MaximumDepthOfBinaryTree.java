package com.g7tianyi.lintcode.tree;

import com.g7tianyi.lintcode.common.TreeNode;
import com.g7tianyi.lintcode.util.Log;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 24, 2019
 *
 * <p>Problem link: https://www.lintcode.com/problem/binary-tree-level-order-traversal/description
 */
public class MaximumDepthOfBinaryTree {

  private static final Log log = new Log();

  public class Solution {

    public int maxDepth(TreeNode root) {

      if (root == null) {
        return 0;
      }

      int leftDepth = 0;
      if (root.left != null) {
        leftDepth = maxDepth(root.left);
      }

      int rightDepth = 0;
      if (root.right != null) {
        rightDepth = maxDepth(root.right);
      }

      return Math.max(leftDepth, rightDepth) + 1;
    }
  }

  @AllArgsConstructor
  private static final class Input {

    private TreeNode root;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Input> runner = input -> log.info("%d", s.maxDepth(input.root));

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
