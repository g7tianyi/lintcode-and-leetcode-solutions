package com.g7tianyi.lintcode.tree.bst;

import com.g7tianyi.common.TreeNode;
import com.g7tianyi.util.Logger;

/**
 * Created by g7tianyi on Sep 03, 2019
 *
 * @link
 *     https://www.lintcode.com/problem/convert-sorted-array-to-binary-search-tree-with-minimal-height/description
 */
public class ConvertSortedArrayToBinarySearchTreeWithMinimalHeight {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public TreeNode sortedArrayToBST(int[] elems) {
      return buildCompleteBinaryTree(elems, 0, elems.length - 1);
    }

    private TreeNode buildCompleteBinaryTree(int[] elems, int start, int end) {

      if (start < 0 || end >= elems.length || start > end) {
        return null;
      }

      int middle = start + ((end - start) >> 1);
      TreeNode root = new TreeNode(elems[middle]);
      root.left = buildCompleteBinaryTree(elems, start, middle - 1);
      root.right = buildCompleteBinaryTree(elems, middle + 1, end);
      return root;
    }
  }
}
