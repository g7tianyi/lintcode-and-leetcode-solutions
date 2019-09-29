package com.g7tianyi.lintcode.list;

import com.g7tianyi.common.ListNode;
import com.g7tianyi.common.Strings;
import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 29, 2019
 *
 * @link https://www.lintcode.com/problem/odd-even-linked-list/description
 */
public class OddEvenLinkedList {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    // 1->2->3->4->5->NULL
    // 1->3->5->2->4->NULL
    public ListNode oddEvenList(ListNode head) {
      if (head == null || head.next == null) {
        return head;
      }

      ListNode p1 = head, p2 = head.next, p3 = head.next;
      ListNode curr = p2.next;
      boolean odd = true;
      while (curr != null) {
        if (odd) {
          p1.next = curr;
          p1 = p1.next;
        } else {
          p2.next = curr;
          p2 = p2.next;
        }

        curr = curr.next;
        odd = !odd;
      }
      p1.next = p3;
      p2.next = null;

      return head;
    }
  }

  private final Solution s = new Solution();

  private final Consumer<ListNode> c =
      head -> {
        log.info(Strings.format(head));
        log.info(Strings.format(s.oddEvenList(head)));
      };

  @Test
  public void test() {
    c.accept(ListNode.from(1, 2, 3, 4, 5));
    c.accept(ListNode.from(1, 2, 3, 4));
    c.accept(ListNode.from(1, 2));
  }
}
