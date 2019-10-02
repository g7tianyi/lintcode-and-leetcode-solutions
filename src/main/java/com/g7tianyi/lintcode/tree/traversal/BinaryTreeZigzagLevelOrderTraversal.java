package com.g7tianyi.lintcode.tree.traversal;

import com.g7tianyi.common.TreeNode;
import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.*;

/**
 * Created by g7tianyi on Oct 02, 2019
 *
 * @link https://www.lintcode.com/problem/binary-tree-zigzag-level-order-traversal/description
 */
public class BinaryTreeZigzagLevelOrderTraversal {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

      List<List<Integer>> result = new ArrayList<>();
      if (root == null) {
        return result;
      }

      Queue<TreeNode> nodeQueue = new LinkedList<>();
      nodeQueue.offer(root);
      nodeQueue.offer(null);

      int curr = 0;
      List<Integer> rowInfo = new ArrayList<>();
      while (!nodeQueue.isEmpty()) {
        TreeNode node = nodeQueue.poll();
        if (node == null) {
          if (curr == 1) {
            Collections.reverse(rowInfo);
          }
          curr ^= 1;
          result.add(rowInfo);

          if (!nodeQueue.isEmpty()) {
            nodeQueue.offer(null);
            rowInfo = new ArrayList<>();
          }
        } else {
          rowInfo.add(node.val);
          if (node.left != null) {
            nodeQueue.offer(node.left);
          }
          if (node.right != null) {
            nodeQueue.offer(node.right);
          }
        }
      }

      return result;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.zigzagLevelOrder(TreeNode.createTree("1,2,3")));
    log.info(s.zigzagLevelOrder(TreeNode.createTree("3,9,20,#,#,15,7")));
  }
}
