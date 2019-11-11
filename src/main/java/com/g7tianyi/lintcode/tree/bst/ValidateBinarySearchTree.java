package com.g7tianyi.lintcode.tree.bst;

import com.g7tianyi.common.TreeNode;
import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Nov 11, 2019
 *
 * @link https://www.lintcode.com/problem/validate-binary-search-tree/description
 */
public class ValidateBinarySearchTree {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    class Info {
      boolean valid;
      long min, max;
    }

    public boolean isValidBST(TreeNode root) {
      return validate(root).valid;
    }

    private Info validate(TreeNode root) {
      Info info = new Info();
      if (root == null) {
        info.valid = true;
        info.max = Long.MIN_VALUE;
        info.min = Long.MAX_VALUE;
        return info;
      }

      Info left = validate(root.left);
      if (!left.valid) {
        info.valid = false;
        return info;
      }

      Info right = validate(root.right);
      if (!right.valid) {
        info.valid = false;
        return info;
      }

      info.valid = root.val > left.max && root.val < right.min;
      if (!info.valid) {
        return info;
      }

      info.min = Math.min(root.val, left.min);
      info.max = Math.max(root.val, right.max);
      return info;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.isValidBST(null));
    log.info(s.isValidBST(TreeNode.createTree("2147483647")));
    log.info(s.isValidBST(TreeNode.createTree("2,1,4,#,#,3,5")));
    log.info(s.isValidBST(TreeNode.createTree("2,1,4,#,#,6,5")));
  }
}
