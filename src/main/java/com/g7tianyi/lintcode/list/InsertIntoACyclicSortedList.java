package com.g7tianyi.lintcode.list;

import com.g7tianyi.common.ListNode;
import com.g7tianyi.util.Logger;

/**
 * Created by g7tianyi on Oct 02, 2019
 *
 * @link https://www.lintcode.com/problem/insert-into-a-cyclic-sorted-list/description
 */
public class InsertIntoACyclicSortedList {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public ListNode insert(ListNode head, int value) {

      if (head == null) {
        head = new ListNode(value);
        head.next = head;
        return head;
      }

      ListNode curr = head.next, prev = head;
      while (curr != head) {
        if (value <= curr.val && value >= prev.val) {
          break;
        }
        if (prev.val > curr.val // 旋转点
            && (value < curr.val || value > prev.val)) {
          break;
        }
        prev = curr;
        curr = curr.next;
      }

      ListNode node = new ListNode(value);
      node.next = curr;
      prev.next = node;
      return node;
    }
  }
}
