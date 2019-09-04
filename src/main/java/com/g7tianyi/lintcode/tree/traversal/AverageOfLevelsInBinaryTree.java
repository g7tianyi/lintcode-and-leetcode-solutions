package com.g7tianyi.lintcode.tree.traversal;

import com.g7tianyi.common.TreeNode;
import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 04, 2019
 *
 * @link https://www.lintcode.com/problem/average-of-levels-in-binary-tree/description
 */
public class AverageOfLevelsInBinaryTree {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public List<Double> averageOfLevels(TreeNode root) {

      List<Double> result = new ArrayList<>();
      if (root == null) {
        return result;
      }

      Queue<TreeNode> myQueue = new LinkedList<>();
      myQueue.offer(root);
      myQueue.offer(null);

      int sum = 0, count = 0;
      while (!myQueue.isEmpty()) {
        TreeNode curr = myQueue.poll();
        if (curr == null) {
          result.add((double) sum / count);
          count = 0;
          sum = 0;
          if (!myQueue.isEmpty()) {
            myQueue.offer(null);
          }
        } else {
          ++count;
          sum += curr.val;
          if (curr.left != null) {
            myQueue.offer(curr.left);
          }
          if (curr.right != null) {
            myQueue.offer(curr.right);
          }
        }
      }

      return result;
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<TreeNode> c = root -> log.info(s.averageOfLevels(root));

    c.accept(TreeNode.createTree("3,9,20,#,#,15,7"));
  }
}
