package com.g7tianyi.lintcode.tree;

import com.g7tianyi.common.TreeNode;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 02, 2019
 *
 * @link https://www.lintcode.com/problem/binary-tree-tilt/description
 */
public class BinaryTreeTilt {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int findTilt(TreeNode root) {
      List<Integer> result = dfs(root);
      return result.get(1);
    }

    private List<Integer> dfs(TreeNode node) {
      List<Integer> result = new ArrayList<>();
      if (node == null) {
        result.add(0);
        result.add(0);
        return result;
      }

      List<Integer> left = dfs(node.left);
      List<Integer> right = dfs(node.right);

      int sum = node.val + left.get(0) + right.get(0);
      result.add(sum);

      int tilt = left.get(1) + right.get(1) + Math.abs(left.get(0) - right.get(0));
      result.add(tilt);
      return result;
    }
  }

  @AllArgsConstructor
  private static final class Case {

    private TreeNode root;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c = aCase -> log.info("%d", s.findTilt(aCase.root));

    c.accept(new Case(TreeNode.createTree("1,2,3")));
    c.accept(new Case(TreeNode.createTree("1,1,#,2,3")));
  }
}
