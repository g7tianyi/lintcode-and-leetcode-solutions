package com.g7tianyi.lintcode.tree.traversal;

import com.g7tianyi.common.TreeNode;
import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Nov 11, 2019
 *
 * @link https://www.lintcode.com/problem/binary-tree-maximum-path-sum/description
 */
public class BinaryTreeMaximumPathSum {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    class Info {
      long maxSum;
      long maxPath;

      public Info(long maxSum, long maxPath) {
        this.maxSum = maxSum;
        this.maxPath = maxPath;
      }
    }

    public int maxPathSum(TreeNode root) {
      return (int) maxInfo(root).maxSum;
    }

    private Info maxInfo(TreeNode root) {

      if (root == null) {
        return new Info(Integer.MIN_VALUE, Integer.MIN_VALUE);
      }

      Info leftInfo = maxInfo(root.left);
      Info rightInfo = maxInfo(root.right);

      long leftMaxPath = Math.max(leftInfo.maxPath, 0);
      long rightMaxPath = Math.max(rightInfo.maxPath, 0);
      long maxPath = Math.max(leftMaxPath, rightMaxPath) + root.val;

      long maxSum = Math.max(leftInfo.maxSum, rightInfo.maxSum);
      maxSum = Math.max(maxSum, leftMaxPath + rightMaxPath + root.val);

      return new Info(maxSum, maxPath);
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.maxPathSum(TreeNode.createTree("-1")));
    log.info(s.maxPathSum(TreeNode.createTree("-4,2,5")));
    log.info(s.maxPathSum(TreeNode.createTree("-9,3,2")));
    log.info(s.maxPathSum(TreeNode.createTree("5,-1,1")));
    log.info(s.maxPathSum(TreeNode.createTree("5,-1,-9")));
  }
}
