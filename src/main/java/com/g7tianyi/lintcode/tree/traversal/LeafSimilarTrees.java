package com.g7tianyi.lintcode.tree.traversal;

import com.g7tianyi.common.TreeNode;
import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by g7tianyi on Oct 24, 2019
 *
 * @link https://www.lintcode.com/problem/leaf-similar-trees/description
 */
public class LeafSimilarTrees {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
      if (root1 == null && root2 == null) {
        return true;
      }
      if (root1 == null || root2 == null) {
        return false;
      }

      List<Integer> leafs1 = new ArrayList<>();
      leafs(root1, leafs1);

      List<Integer> leafs2 = new ArrayList<>();
      leafs(root2, leafs2);

      if (leafs1.size() != leafs2.size()) {
        return false;
      }

      for (int i = 0; i < leafs1.size(); ++i) {
        if (!leafs1.get(i).equals(leafs2.get(i))) {
          return false;
        }
      }
      return true;
    }

    private void leafs(TreeNode root, List<Integer> result) {
      if (root.left == null && root.right == null) {
        result.add(root.val);
        return;
      }

      if (root.left != null) {
        leafs(root.left, result);
      }

      if (root.right != null) {
        leafs(root.right, result);
      }
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {}
}
