package com.g7tianyi.lintcode.tree;

import com.g7tianyi.common.TreeNode;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 04, 2019
 *
 * @link https://www.lintcode.com/problem/closest-binary-search-tree-value/description
 */
public class ClosestBinarySearchTreeValue {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int closestValue(TreeNode root, double target) {

      TreeNode curr = root;
      int result = root.val;
      double minDiff = Math.abs(target - root.val), newDiff;
      while (curr != null) {
        newDiff = Math.abs(target - curr.val);
        if (minDiff > newDiff) {
          minDiff = newDiff;
          result = curr.val;
        }

        if (target < curr.val) {
          curr = curr.left;
        } else {
          curr = curr.right;
        }
      }
      return result;
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
