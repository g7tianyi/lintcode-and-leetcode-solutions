package com.g7tianyi.lintcode.tree;

import com.g7tianyi.common.ListNode;
import com.g7tianyi.common.TreeNode;
import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Nov 12, 2019
 *
 * @link https://www.lintcode.com/problem/convert-sorted-list-to-binary-search-tree/description
 */
public class ConvertSortedListToBinarySearchTree {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public TreeNode sortedListToBST(ListNode head) {
      int len = getLength(head);
      if (len == 0) {
        return null;
      }
      if (len == 1) {
        return new TreeNode(head.val);
      }

      ListNode preMiddle = null, middle = head;
      int pos = 0;
      while (pos != (len >> 1)) {
        preMiddle = middle;
        middle = middle.next;
        ++pos;
      }
      TreeNode root = new TreeNode(middle.val);

      preMiddle.next = null;
      root.left = sortedListToBST(head);
      root.right = sortedListToBST(middle.next);
      return root;
    }

    private int getLength(ListNode head) {
      int len = 0;
      while (head != null) {
        head = head.next;
        ++len;
      }
      return len;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.sortedListToBST(ListNode.from(1)).serialize());
    log.info(s.sortedListToBST(ListNode.from(1, 2)).serialize());
    log.info(s.sortedListToBST(ListNode.from(1, 2, 3)).serialize());
    log.info(s.sortedListToBST(ListNode.from(1, 2, 3, 4)).serialize());
    log.info(s.sortedListToBST(ListNode.from(1, 2, 3, 4, 5)).serialize());
  }
}
