package com.g7tianyi.lintcode.tree;

import com.g7tianyi.common.TreeNode;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 04, 2019
 *
 * @link https://www.lintcode.com/problem/longest-univalue-path/description
 */
public class LongestUnivaluePath {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int longestUnivaluePath(TreeNode root) {
      Info info = traverse(root, null);
      return Math.max(info.maxSubLen, info.maxRootLen);
    }

    class Info {
      int maxSubLen;
      int maxRootLen;

      public Info() {
        this(0, 0);
      }

      public Info(int maxSubLen, int maxRootLen) {
        this.maxSubLen = maxSubLen;
        this.maxRootLen = maxRootLen;
      }
    }

    private Info traverse(TreeNode curr, TreeNode prev) {

      if (curr == null) {
        return new Info();
      }

      Info left = traverse(curr.left, curr);
      Info right = traverse(curr.right, curr);

      int maxRootLeftLen = 0, maxRootRightLen = 0;
      if (curr.left != null && curr.val == curr.left.val) {
        maxRootLeftLen = left.maxRootLen + 1;
      }
      if (curr.right != null && curr.val == curr.right.val) {
        maxRootRightLen = right.maxRootLen + 1;
      }

      int maxRootLen = 0;
      if (prev != null && prev.val == curr.val) {
        maxRootLen = Math.max(maxRootLeftLen, maxRootRightLen);
      }

      int maxSubLen =
          Math.max(
              Math.max(left.maxSubLen, right.maxSubLen), Math.max(maxRootLeftLen, maxRootRightLen));

      if (curr.left != null
          && curr.val == curr.left.val
          && curr.right != null
          && curr.val == curr.right.val) {
        maxSubLen = Math.max(maxSubLen, left.maxRootLen + right.maxRootLen + 2);
      }

      return new Info(maxSubLen, maxRootLen);
    }
  }

  @AllArgsConstructor
  class Case {
    String tree;
    int expect;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c =
        aCase -> {
          log.info(aCase.tree);
          int result = s.longestUnivaluePath(TreeNode.createTree(aCase.tree));
          log.info(result);
          Assert.assertEquals(aCase.expect, result);
        };

    c.accept(new Case("3,3,3", 2));
    c.accept(new Case("5,4,5,1,1,#,5", 2));
    c.accept(new Case("1,4,5,4,4,#,5", 2));
    c.accept(new Case("1,4,5,4,4,#,5,4,#,#,4", 4));
    c.accept(new Case("4,4,4,4,4,#,4,#,#,4", 5));
  }
}
