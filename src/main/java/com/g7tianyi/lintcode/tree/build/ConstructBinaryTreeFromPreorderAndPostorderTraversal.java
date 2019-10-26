package com.g7tianyi.lintcode.tree.build;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.common.TreeNode;
import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Oct 26, 2019
 *
 * @link
 *     https://www.lintcode.com/problem/construct-binary-tree-from-preorder-and-inorder-traversal/description
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
      if (inorder == null || inorder.length == 0 || preorder == null || preorder.length == 0) {
        return null;
      }
      return build(inorder, 0, inorder.length - 1, preorder, 0, preorder.length - 1);
    }

    private TreeNode build(
        int[] inorder, int iStart, int iEnd, int[] preorder, int pStart, int pEnd) {

      if (iStart == iEnd) {
        return new TreeNode(inorder[iStart]);
      }

      // 前序遍历的第一个节点是根节点
      TreeNode root = new TreeNode(preorder[pStart]);
      int mid = iStart;
      for (; mid <= iEnd; ++mid) {
        if (inorder[mid] == root.val) { // 中序遍历的中点，借这个mid划分左子树和右子树
          break;
        }
      }

      if (iStart <= mid - 1) {
        // (mid - iStart) 是指一共有多少个左子节点
        root.left = build(inorder, iStart, mid - 1, preorder, pStart + 1, pStart + (mid - iStart));
      }
      if (mid + 1 <= iEnd) {
        root.right = build(inorder, mid + 1, iEnd, preorder, pStart + (mid - iStart) + 1, pEnd);
      }
      return root;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.buildTree(Arrays.from(1, 2, 4, 3, 6, 7), Arrays.from(4, 2, 1, 6, 3, 7)).serialize());
    log.info(s.buildTree(Arrays.from(1, 2, 3, 4, 5), Arrays.from(2, 4, 5, 3, 1)).serialize());
  }
}
