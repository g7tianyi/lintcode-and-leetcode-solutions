package com.g7tianyi.lintcode.tree.traversal;

import com.g7tianyi.common.TreeNode;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 03, 2019
 *
 * @link https://www.lintcode.com/problem/sum-of-left-leaves/description
 */
public class SumOfLeftLeaves {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int sumOfLeftLeaves(TreeNode root) {
      // Write your code here

      if (root == null) {
        return 0;
      }

      int result = 0;
      if (root.left != null) {
        if (root.left.left == null && root.left.right == null) {
          result += root.left.val;
        } else {
          result += sumOfLeftLeaves(root.left);
        }
      }

      if (root.right != null) {
        result += sumOfLeftLeaves(root.right);
      }

      return result;
    }
  }

  @AllArgsConstructor
  private static final class Case {

    private TreeNode root;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c = aCase -> log.info("%d", s.sumOfLeftLeaves(aCase.root));

    c.accept(new Case(TreeNode.createTree("1,2,3")));
    c.accept(new Case(TreeNode.createTree("3,9,20,#,#,15,7")));
    c.accept(new Case(TreeNode.createTree("4,1,#,2,#,#,#,3")));
  }
}
