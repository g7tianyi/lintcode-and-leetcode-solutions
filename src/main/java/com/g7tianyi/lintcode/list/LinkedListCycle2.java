package com.g7tianyi.lintcode.list;

import com.g7tianyi.common.ListNode;
import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Sep 21, 2019
 *
 * @link https://www.lintcode.com/problem/linked-list-cycle-ii/description
 */
public class LinkedListCycle2 {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public ListNode detectCycle(ListNode head) {
      if (head == null || head.next == null) {
        return null;
      }

      ListNode slow = head;
      ListNode fast = head.next;

      while (fast.next != null && fast.next.next != null) {
        if (slow == fast) {
          break;
        }
        slow = slow.next;
        fast = fast.next.next;
      }
      if (slow != fast) {
        return null;
      }

      ListNode node = head;
      while (node != slow.next) {
        node = node.next;
        slow = slow.next;
      }
      return node;
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    log.info(s.detectCycle(ListNode.circularList(1, 0)));
    log.info(s.detectCycle(ListNode.circularList(10, 6)));
    log.info(s.detectCycle(ListNode.circularList(11, 6)));
  }
}
