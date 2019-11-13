package com.g7tianyi.lintcode.tree.bst;

import com.g7tianyi.common.TreeNode;
import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Nov 12, 2019
 *
 * @link https://www.lintcode.com/problem/remove-node-in-binary-search-tree/description
 */
public class RemoveNodeInBinarySearchTree {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    // 如果node是根节点，删除根节点
    // 如果node的右子树为空，直接将node的左子树赋给node的parent节点
    // 如果node的右子树不为空，寻找其右子树中值最小的节点替换node
    // 如果找不到node则返回root
    public TreeNode removeNode(TreeNode root, int value) {

      TreeNode sentry = new TreeNode(0);
      sentry.left = root;

      TreeNode parent = search(sentry, root, value);
      TreeNode node;
      if (parent.left != null && parent.left.val == value) {
        node = parent.left;
      } else if (parent.right != null && parent.right.val == value) {
        node = parent.right;
      } else {
        return sentry.left;
      }

      removeNode(parent, node);

      return sentry.left;
    }

    private TreeNode search(TreeNode parent, TreeNode node, int value) {
      while (node != null && node.val != value) {
        parent = node;
        if (value < node.val) {
          node = node.left;
        } else {
          node = node.right;
        }
      }
      return parent;
    }

    private void removeNode(TreeNode parent, TreeNode node) {
      if (node.right == null) { // 待删除节点的右子树为空
        if (parent.left == node) { // 待删除节点是其父节点的左子树
          parent.left = node.left;
        } else {
          parent.right = node.left;
        }
      } else {
        // 待删除节点的右子树不为空

        // 1. 寻找其右子树中值最小的节点替换node
        TreeNode replacement = node.right;
        TreeNode father = node;
        while (replacement.left != null) {
          father = replacement;
          replacement = replacement.left;
        }

        // temp节点的右子树显然是空的
        // 2. 调整replacement节点的父节点的孩子指针
        if (father.left == replacement) {
          father.left = replacement.right;
        } else {
          father.right = replacement.right;
        }

        // 3. 调整replacement节点的父节点的孩子指针
        if (parent.left == node) {
          parent.left = replacement;
        } else {
          parent.right = replacement;
        }

        // 4. 调整replacement节点自身的孩子节点，指向待删除节点的孩子们
        replacement.left = node.left;
        replacement.right = node.right;
      }
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {

    log.info(s.removeNode(TreeNode.createTree("1"), 1));

    for (int i = 1; i <= 8; ++i) {
      log.info(s.removeNode(TreeNode.createTree("5,4,6,2,#,#,8,1,3,7"), i).serialize());
    }
  }
}
