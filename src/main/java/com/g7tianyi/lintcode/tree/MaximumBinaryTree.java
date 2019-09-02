package com.g7tianyi.lintcode.tree;

import com.g7tianyi.common.TreeNode;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 03, 2019
 *
 * @link https://www.lintcode.com/problem/maximum-binary-tree/description
 */
public class MaximumBinaryTree {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public TreeNode constructMaximumBinaryTree(int[] elems) {
      return constructMaximumBinaryTree(elems, 0, elems.length - 1);
    }

    private TreeNode constructMaximumBinaryTree(int[] elems, int start, int end) {

      log.info("%d %d", start, end);

      if (start < 0 || end >= elems.length || start > end) {
        return null;
      }

      int pos = start + 1, maxPos = start;
      while (pos <= end) {
        if (elems[maxPos] < elems[pos]) {
          maxPos = pos;
        }
        ++pos;
      }

      TreeNode root = new TreeNode(elems[maxPos]);
      root.left = constructMaximumBinaryTree(elems, start, maxPos - 1);
      root.right = constructMaximumBinaryTree(elems, maxPos + 1, end);
      return root;
    }
  }

  @AllArgsConstructor
  private class Case {
    int[] elems;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c = aCase -> s.constructMaximumBinaryTree(aCase.elems);

    c.accept(new Case(new int[] {3, 2, 1, 6, 0, 5}));
  }
}
