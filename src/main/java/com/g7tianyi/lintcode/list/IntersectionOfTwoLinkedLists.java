package com.g7tianyi.lintcode.list;

import com.g7tianyi.common.ListNode;
import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Oct 02, 2019
 *
 * @link https://www.lintcode.com/problem/intersection-of-two-linked-lists/description
 */
public class IntersectionOfTwoLinkedLists {

  private static final Logger log = Logger.getInstance();

  // 连接两个链表然后找环的入口，有点太麻烦了
  // 分别找出两个长度，让长的先走到剩余长度和短的一样地方
  // 然后让两个同时走到第一个node相同的地方, 返回结果即可
  public class Solution {

    public ListNode getIntersectionNode(ListNode head1, ListNode head2) {
      int len1 = getListLength(head1);
      int len2 = getListLength(head2);

      ListNode longer, shorter;
      if (len1 > len2) {
        longer = head1;
        shorter = head2;
      } else {
        longer = head2;
        shorter = head1;
      }

      for (int i = 0; i < Math.abs(len1 - len2); ++i) {
        longer = longer.next;
      }

      while (longer != shorter) {
        longer = longer.next;
        shorter = shorter.next;
      }
      return longer;
    }

    private int getListLength(ListNode head) {
      ListNode curr = head;
      int len = 0;
      while (curr != null) {
        curr = curr.next;
        ++len;
      }
      return len;
    }
  }
}
