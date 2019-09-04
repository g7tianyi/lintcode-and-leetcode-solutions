package com.g7tianyi.lintcode.tree;

import com.g7tianyi.common.TreeNode;
import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 04, 2019
 *
 * @link https://www.lintcode.com/problem/construct-string-from-binary-tree/description
 */
public class ConstructStringFromBinaryTree {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public String tree2str(TreeNode root) {

      if (root == null) {
        return "";
      }

      StringBuilder sb = new StringBuilder();
      sb.append(root.val);

      String left = tree2str(root.left);
      String right = tree2str(root.right);

      boolean leftEmpty = left.equals("");
      boolean rightEmpty = right.equals("");

      if (!leftEmpty && rightEmpty) {
        sb.append("(").append(left).append(")");
      }

      if (leftEmpty && !rightEmpty) {
        sb.append("()").append("(").append(right).append(")");
      }

      if (!leftEmpty && !rightEmpty) {
        sb.append("(").append(left).append(")(").append(right).append(")");
      }

      return sb.toString();
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<TreeNode> c = root -> log.info(s.tree2str(root));

    c.accept(TreeNode.createTree("1,2,3,4"));
    c.accept(TreeNode.createTree("1,2,3,#,4"));
  }
}
