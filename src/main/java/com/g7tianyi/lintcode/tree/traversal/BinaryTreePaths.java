package com.g7tianyi.lintcode.tree.traversal;

import com.g7tianyi.common.TreeNode;
import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 28, 2019
 *
 * @link https://www.lintcode.com/problem/binary-tree-paths/description
 */
public class BinaryTreePaths {

  private static final Logger log = Logger.getInstance();

  public static class Solution {

    public List<String> binaryTreePaths(TreeNode root) {
      // write your code here
      List<String> result = new ArrayList<>();
      if (root == null) {
        return result;
      }

      StringBuilder sb = new StringBuilder();
      dfs(root, result, sb);
      return result;
    }

    private static void dfs(TreeNode node, List<String> list, StringBuilder sb) {

      sb.append(node.val);

      if (node.left == null && node.right == null) {
        list.add(sb.toString());
      }

      if (node.left != null) {
        sb.append("->");
        dfs(node.left, list, sb);
        sb.delete(sb.length() - 2, sb.length());
      }

      if (node.right != null) {
        sb.append("->");
        dfs(node.right, list, sb);
        sb.delete(sb.length() - 2, sb.length());
      }

      while (sb.length() > 0 && sb.charAt(sb.length() - 1) != '>') {
        sb.deleteCharAt(sb.length() - 1);
      }
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<TreeNode> runner = root -> log.info("%s", s.binaryTreePaths(root));

    runner.accept(TreeNode.createTree("1,2,3,#,5"));
    runner.accept(TreeNode.createTree("1,2"));
    runner.accept(TreeNode.createTree("1,#,2,#,#,3"));
    runner.accept(TreeNode.createTree("1,2,3,#,#,4,5"));
  }
}
