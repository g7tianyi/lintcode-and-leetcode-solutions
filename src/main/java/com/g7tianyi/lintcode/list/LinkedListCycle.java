package com.g7tianyi.lintcode.list;

import com.g7tianyi.common.ListNode;
import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Sep 29, 2019
 *
 * @link https://www.lintcode.com/problem/linked-list-cycle/description
 */
public class LinkedListCycle {

  private static final Logger log = Logger.getInstance();

  public class Solution {
    public boolean hasCycle(ListNode head) {
      if (head == null || head.next == null) {
        return false;
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
      return slow == fast;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.hasCycle(ListNode.circularList(10)));
    log.info(s.hasCycle(ListNode.randomListOf(10)));
  }
}
