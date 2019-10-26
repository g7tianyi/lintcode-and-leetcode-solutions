package com.g7tianyi.lintcode.tree.build;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.common.TreeNode;
import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Oct 26, 2019
 *
 * @link
 *     https://www.lintcode.com/problem/construct-binary-tree-from-preorder-and-postorder-traversal/description
 */
public class ConstructBinaryTreeFromPreorderAndPostorderTraversal {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public TreeNode constructFromPrePost(int[] pre, int[] post) {
      if (pre == null || pre.length == 0 || post == null || post.length == 0) {
        return null;
      }
      return build(pre, 0, pre.length - 1, post, 0, post.length - 1);
    }

    private TreeNode build(
        int[] pre, int preStart, int preEnd, int[] post, int postStart, int postEnd) {

      if (preStart == preEnd) {
        return new TreeNode(pre[preStart]);
      }

      TreeNode root = new TreeNode(pre[preStart]);

      int index = postStart;
      for (; index <= postEnd; ++index) {
        if (post[index] == pre[preStart + 1]) {
          break;
        }
      }

      root.left =
          build(
              pre,
              preStart + 1,
              preStart + 1 + (index - postStart),
              post,
              postStart,
              postStart + (index - postStart));

      if (preStart + 1 + (index - postStart) + 1 <= preEnd
          && postStart + (index - postStart) <= postEnd - 1) {
        root.right =
            build(
                pre,
                preStart + 1 + (index - postStart) + 1,
                preEnd,
                post,
                postStart + (index - postStart) + 1,
                postEnd - 1);
      }

      return root;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(
        s.constructFromPrePost(Arrays.from(1, 2, 4, 3, 6, 7), Arrays.from(4, 2, 6, 7, 3, 1))
            .serialize());
    log.info(
        s.constructFromPrePost(Arrays.from(1, 2, 3, 4, 5), Arrays.from(5, 4, 3, 2, 1)).serialize());
  }
}
