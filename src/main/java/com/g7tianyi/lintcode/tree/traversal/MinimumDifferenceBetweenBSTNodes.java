package com.g7tianyi.lintcode.tree.traversal;

import com.g7tianyi.common.TreeNode;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 03, 2019
 *
 * @link https://www.lintcode.com/problem/minimum-difference-between-bst-nodes/description
 */
public class MinimumDifferenceBetweenBSTNodes {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int minDiffInBST(TreeNode root) {

      List<Integer> values = traverse(root);
      if (values.size() < 2) {
        return 0;
      }

      int minDifference = values.get(1) - values.get(0);
      for (int i = 2; i < values.size(); ++i) {
        if (minDifference > values.get(i) - values.get(i - 1)) {
          minDifference = values.get(i) - values.get(i - 1);
        }
      }
      return minDifference;
    }

    private List<Integer> traverse(TreeNode root) {

      List<Integer> result = new ArrayList<>();

      if (root == null) {
        return result;
      }

      List<Integer> left = traverse(root.left);
      result.addAll(left);

      result.add(root.val);

      List<Integer> right = traverse(root.right);
      result.addAll(right);

      return result;
    }
  }

  @AllArgsConstructor
  private class Case {
    TreeNode root;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c = aCase -> log.info("%d", s.minDiffInBST(aCase.root));

    c.accept(new Case(TreeNode.createTree("5,1,8")));
    c.accept(new Case(TreeNode.createTree("4,2,6,1,3,#,#")));
  }
}
