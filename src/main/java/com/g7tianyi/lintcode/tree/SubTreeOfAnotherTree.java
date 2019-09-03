package com.g7tianyi.lintcode.tree;

import com.g7tianyi.common.TreeNode;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 03, 2019
 *
 * @link https://www.lintcode.com/problem/subtree-of-another-tree/description
 */
public class SubTreeOfAnotherTree {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public boolean isSubtree(TreeNode main, TreeNode tree) {

      return equivalent(main, tree)
          || (main.left != null && isSubtree(main.left, tree))
          || (main.right != null && isSubtree(main.right, tree));
    }

    public boolean equivalent(TreeNode main, TreeNode tree) {

      if (main == null && tree == null) {
        return true;
      }

      if ((main != null && tree == null) || (main == null && tree != null)) {
        return false;
      }

      if (main.val != tree.val) {
        return false;
      }

      return equivalent(main.left, tree.left) && equivalent(main.right, tree.right);
    }
  }

  @AllArgsConstructor
  private class Case {}

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c = aCase -> {};
  }
}
