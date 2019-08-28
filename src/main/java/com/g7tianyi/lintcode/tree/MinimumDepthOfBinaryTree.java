package com.g7tianyi.lintcode.tree;

import com.g7tianyi.common.TreeNode;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 26, 2019
 *
 * @link https://www.lintcode.com/problem/minimum-depth-of-binary-tree/description
 */
public class MinimumDepthOfBinaryTree {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int minDepth(TreeNode root) {

      if (root == null) {
        return 0;
      }

      int left = minDepth(root.left);
      int right = minDepth(root.right);

      int subDepth;
      if (left == 0 && right == 0) {
        subDepth = 0;
      } else if (left != 0 && right == 0) {
        subDepth = left;
      } else if (left == 0) {
        subDepth = right;
      } else {
        subDepth = Math.min(left, right);
      }

      return subDepth + 1;
    }
  }

  @AllArgsConstructor
  private static final class Input {

    private TreeNode root;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Input> runner = input -> log.info("%d", s.minDepth(input.root));

    runner.accept(new Input(TreeNode.createTree("1,#,2,#,#,3")));
    runner.accept(new Input(TreeNode.createTree("1,2,3,#,#,4,5")));
  }
}
