package com.g7tianyi.lintcode.tree.traversal;

import com.g7tianyi.common.TreeNode;
import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by g7tianyi on Oct 02, 2019
 *
 * @link https://www.lintcode.com/problem/binary-tree-level-order-traversal-ii/description
 */
public class BinaryTreeLevelOrderTraversal2 {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public List<List<Integer>> levelOrderBottom(TreeNode root) {

      LinkedList<List<Integer>> result = new LinkedList<>();

      if (root == null) {
        return result;
      }

      Queue<TreeNode> treeQueue = new LinkedList<>();
      treeQueue.offer(root);
      treeQueue.offer(null);

      List<Integer> level = new ArrayList<>();

      while (!treeQueue.isEmpty()) {
        TreeNode node = treeQueue.poll();
        if (node == null) {
          result.addFirst(level);
          level = new ArrayList<>();
          if (!treeQueue.isEmpty()) {
            treeQueue.offer(null);
          }
        } else {
          level.add(node.val);
          if (node.left != null) {
            treeQueue.offer(node.left);
          }
          if (node.right != null) {
            treeQueue.offer(node.right);
          }
        }
      }

      return result;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.levelOrderBottom(null));
    log.info(s.levelOrderBottom(TreeNode.createTree("1")));
    log.info(s.levelOrderBottom(TreeNode.createTree("1,2,3")));
    log.info(s.levelOrderBottom(TreeNode.createTree("3,9,20,#,#,15,7")));
  }
}
