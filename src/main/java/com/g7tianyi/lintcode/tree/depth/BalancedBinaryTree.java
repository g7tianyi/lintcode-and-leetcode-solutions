package com.g7tianyi.lintcode.tree.depth;

import com.g7tianyi.common.TreeNode;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 27, 2019
 *
 * @link https://www.lintcode.com/problem/balanced-binary-tree/description
 */
public class BalancedBinaryTree {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    private class NodeInfo {

      private boolean balanced;

      private int depth;

      public NodeInfo(boolean balanced, int depth) {
        this.balanced = balanced;
        this.depth = depth;
      }
    }

    public boolean isBalanced(TreeNode root) {
      if (root == null) {
        return true;
      }

      NodeInfo nodeInfo = traverse(root);

      return nodeInfo.balanced;
    }

    private NodeInfo traverse(TreeNode root) {
      boolean leftBalanced = true;
      int leftDepth = 0;
      if (root.left != null) {
        NodeInfo leftInfo = traverse(root.left);
        leftBalanced = leftInfo.balanced;
        leftDepth = leftInfo.depth;
      }

      boolean rightBalanced = true;
      int rightDepth = 0;
      if (root.right != null) {
        NodeInfo rightInfo = traverse(root.right);
        rightBalanced = rightInfo.balanced;
        rightDepth = rightInfo.depth;
      }

      boolean balanced = leftBalanced && rightBalanced && (Math.abs(rightDepth - leftDepth) < 2);
      int depth = Math.max(leftDepth, rightDepth) + 1;
      return new NodeInfo(balanced, depth);
    }
  }

  @AllArgsConstructor
  private static final class Input {

    private TreeNode root;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Input> runner = input -> log.info("%s", s.isBalanced(input.root));

    runner.accept(new Input(TreeNode.createTree("1,2,3")));
    runner.accept(new Input(TreeNode.createTree("3,9,20,#,#,15,7")));
    runner.accept(new Input(TreeNode.createTree("1,#,2,#,#,3,4")));
  }
}
