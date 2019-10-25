package com.g7tianyi.lintcode.tree.depth;

import com.g7tianyi.common.TreeNode;
import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Oct 25, 2019
 *
 * @link https://www.lintcode.com/problem/find-leaves-of-binary-tree/description
 */
public class FindLeavesOfBinaryTree {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public List<List<Integer>> findLeaves(TreeNode root) {

      int height = getHeight(root);

      List<List<Integer>> result = new ArrayList<>();
      for (int i = 0; i < height; ++i) {
        List<Integer> row = new ArrayList<>();
        traverse(root, row);
        result.add(row);
      }

      return result;
    }

    private boolean traverse(TreeNode root, List<Integer> result) {

      if (root.left == null && root.right == null) {
        result.add(root.val);
        return true;
      }

      if (root.left != null) {
        if (traverse(root.left, result)) {
          root.left = null;
        }
      }

      if (root.right != null) {
        if (traverse(root.right, result)) {
          root.right = null;
        }
      }

      return false;
    }

    private int getHeight(TreeNode root) {
      if (root == null) {
        return 0;
      }
      return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }
  }

  private final Solution s = new Solution();

  private final Consumer<TreeNode> c =
      root -> {
        s.findLeaves(root).forEach(log::info);
        log.info();
      };

  @Test
  public void test() {
    c.accept(TreeNode.createTree("1,2,3,4,5"));
    c.accept(TreeNode.createTree("1,2,3,4"));
    c.accept(TreeNode.createTree("1,2,3,#,5"));
  }
}
