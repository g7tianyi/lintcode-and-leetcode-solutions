package com.g7tianyi.lintcode.tree.bst;

import com.g7tianyi.common.TreeNode;
import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by g7tianyi on Sep 19, 2019
 *
 * @link https://www.lintcode.com/problem/search-range-in-binary-search-tree/description
 */
public class SearchRangeInBinarySearchTree {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public List<Integer> searchRange(TreeNode root, int k1, int k2) {

      if (root == null) {
        return new ArrayList<>();
      }

      if (k1 > k2) {
        int t = k1;
        k1 = k2;
        k2 = t;
      }

      if (root.val < k1) {
        return searchRange(root.right, k1, k2);
      }

      if (root.val > k2) {
        return searchRange(root.left, k1, k2);
      }

      List<Integer> ls = searchRange(root.left, k1, k2);
      List<Integer> rs = searchRange(root.right, k1, k2);

      ls.add(root.val);
      ls.addAll(rs);
      return ls;
    }
  }

  @Test
  public void test() {
    Solution s = new Solution();
    log.info(s.searchRange(TreeNode.createTree("20,8,22,4,12"), 10, 22));
    log.info(s.searchRange(TreeNode.createTree("5"), 6, 10));
    log.info(s.searchRange(null, 6, 10));
  }
}
