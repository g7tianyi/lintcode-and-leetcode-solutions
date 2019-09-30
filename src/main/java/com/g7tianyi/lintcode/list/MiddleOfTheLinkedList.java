package com.g7tianyi.lintcode.list;

import com.g7tianyi.common.ListNode;
import com.g7tianyi.common.Strings;
import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 30, 2019
 *
 * @link https://www.lintcode.com/problem/middle-of-the-linked-list/description
 */
public class MiddleOfTheLinkedList {

  private static final Logger log = Logger.getInstance();

  public class Solution {
    public ListNode middleNode(ListNode head) {
      ListNode slow = head, fast = head;
      while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
      }
      return slow;
    }
  }

  private final Solution s = new Solution();

  private final Consumer<ListNode> c =
      head -> {
        log.info(Strings.format(head));
        log.info(Strings.format(s.middleNode(head)));
        log.info();
      };

  @Test
  public void test() {
    c.accept(null);
    c.accept(ListNode.from(1));
    c.accept(ListNode.from(1, 2));
    c.accept(ListNode.from(1, 2, 3));
    c.accept(ListNode.from(1, 2, 3, 4, 5));
    c.accept(ListNode.from(1, 2, 3, 4, 5, 6));
  }
}
