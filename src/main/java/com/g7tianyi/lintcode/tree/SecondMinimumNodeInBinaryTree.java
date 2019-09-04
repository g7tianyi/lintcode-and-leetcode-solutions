package com.g7tianyi.lintcode.tree;

import com.g7tianyi.common.TreeNode;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 04, 2019
 *
 * @link https://www.lintcode.com/problem/second-minimum-node-in-a-binary-tree/description
 */
public class SecondMinimumNodeInBinaryTree {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int findSecondMinimumValue(TreeNode root) {
      return new Traverser().findSecondMinimumValue(root);
    }

    class Traverser {

      int min;
      int second = Integer.MAX_VALUE;

      public int findSecondMinimumValue(TreeNode root) {
        min = root.val;
        traverse(root);
        if (min == second) {
          return -1;
        }
        return second == Integer.MAX_VALUE ? -1 : second;
      }

      public void traverse(TreeNode node) {
        if (node == null) {
          return;
        }
        if (node.val > min && node.val < second) {
          second = node.val;
        } else if (node.val == min) {
          traverse(node.left);
          traverse(node.right);
        }
      }
    }
  }

  @AllArgsConstructor
  class Case {
    TreeNode root;
    int expect;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c =
        aCase -> {
          int result = s.findSecondMinimumValue(aCase.root);
          log.info(result);
          Assert.assertEquals(aCase.expect, result);
        };

    c.accept(new Case(TreeNode.createTree("2,2,5,#,#,5,7"), 5));
    c.accept(new Case(TreeNode.createTree("2,2,2,2,3,4,3"), 3));
    c.accept(new Case(TreeNode.createTree("2,2,2"), -1));
    c.accept(new Case(TreeNode.createTree("2,2,3"), 3));
    c.accept(new Case(TreeNode.createTree("2,3,3"), 3));
    c.accept(new Case(TreeNode.createTree("2,3,4"), 3));
  }
}
