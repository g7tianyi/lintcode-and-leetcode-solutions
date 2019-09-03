package com.g7tianyi.lintcode.tree;

import com.g7tianyi.common.TreeNode;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 03, 2019
 *
 * @link https://www.lintcode.com/problem/binary-tree-pruning/description
 */
public class BinaryTreePruning {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public TreeNode pruneTree(TreeNode root) {
      dfs(root);
      return root;
    }

    public boolean dfs(TreeNode root) {

      if (root == null) {
        return false;
      }

      boolean left = dfs(root.left);
      if (!left) {
        root.left = null;
      }

      boolean right = dfs(root.right);
      if (!right) {
        root.right = null;
      }

      return left || right || root.val == 1;
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<TreeNode> c = aCase -> {};
  }
}
