package com.g7tianyi.lintcode.tree.bst;

import com.g7tianyi.common.TreeNode;
import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Oct 25, 2019
 *
 * @link https://www.lintcode.com/problem/search-in-a-binary-search-tree/description
 */
public class SearchInABinarySearchTree {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public TreeNode searchBST(TreeNode root, int val) {

      TreeNode node = root;
      while (node != null) {
        if (node.val == val) {
          return node;
        }
        if (val < node.val) {
          node = node.left;
        } else {
          node = node.right;
        }
      }
      return null;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {}
}
