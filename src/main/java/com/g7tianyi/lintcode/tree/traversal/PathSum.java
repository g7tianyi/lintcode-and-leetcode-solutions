package com.g7tianyi.lintcode.tree.traversal;

import com.g7tianyi.common.TreeNode;
import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Oct 24, 2019
 *
 * @link https://www.lintcode.com/problem/path-sum/description
 */
public class PathSum {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public boolean pathSum(TreeNode root, int sum) {
      return recursive(root, 0, sum);
    }

    private boolean recursive(TreeNode node, int prev, int sum) {
      if (node == null) {
        return prev == sum;
      }

      if (recursive(node.left, prev + node.val, sum)) {
        return true;
      }

      return recursive(node.right, prev + node.val, sum);
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.pathSum(TreeNode.createTree("5,4,8,11,#,13,4,7,2,#,#,#,#,#,1"), 22));
    log.info(s.pathSum(TreeNode.createTree("5,4,8"), 18));
  }
}
