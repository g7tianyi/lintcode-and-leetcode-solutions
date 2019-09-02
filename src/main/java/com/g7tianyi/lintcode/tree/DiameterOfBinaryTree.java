package com.g7tianyi.lintcode.tree;

import com.g7tianyi.common.TreeNode;
import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 03, 2019
 *
 * @link https://www.lintcode.com/problem/diameter-of-binary-tree/description
 */
public class DiameterOfBinaryTree {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    class Info {
      int maxLen;
      int maxDiameter;

      public Info(int maxLen, int maxDiameter) {
        this.maxLen = maxLen;
        this.maxDiameter = maxDiameter;
      }
    }

    public int diameterOfBinaryTree(TreeNode root) {
      return traverse(root).maxDiameter;
    }

    private Info traverse(TreeNode root) {

      if (root == null) {
        return new Info(0, 0);
      }

      Info leftInfo = traverse(root.left);
      Info rightInfo = traverse(root.right);
      int maxLen = Math.max(leftInfo.maxLen, rightInfo.maxLen) + 1;
      int maxDiameter =
          Math.max(
              Math.max(leftInfo.maxDiameter, rightInfo.maxDiameter),
              leftInfo.maxLen + rightInfo.maxLen);
      return new Info(maxLen, maxDiameter);
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<TreeNode> c = treeNode -> log.info(s.diameterOfBinaryTree(treeNode));

    c.accept(TreeNode.createTree("1,2,3,4,5")); // 3
    c.accept(TreeNode.createTree("2,3,#,1")); // 2
  }
}
