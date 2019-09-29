package com.g7tianyi.lintcode.list;

import com.g7tianyi.common.ListNode;
import com.g7tianyi.common.Strings;
import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 29, 2019
 *
 * @link https://www.lintcode.com/problem/plus-one-linked-list/description
 */
public class PlusOneLinkedList {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public ListNode plusOne(ListNode head) {
      if (head == null) {
        return null;
      }

      ListNode newHead = reverseList(head);

      int carry = 1, sum;
      ListNode curr = newHead, prev = null;
      while (curr != null) {
        sum = curr.val + carry;
        curr.val = sum % 10;
        carry = sum / 10;

        prev = curr;
        curr = curr.next;
      }

      if (carry == 1 && prev != null) {
        prev.next = new ListNode(1);
      }

      return reverseList(newHead);
    }

    private ListNode reverseList(ListNode head) {

      ListNode newHead = head;

      ListNode prev = null, curr = head, next;
      while (curr != null) {
        next = curr.next;
        curr.next = prev;
        prev = curr;
        curr = next;

        if (curr == null) {
          newHead = prev;
        }
      }

      return newHead;
    }
  }

  private final Solution s = new Solution();

  private final Consumer<ListNode> c =
      head -> {
        log.info(Strings.format(head));
        log.info(Strings.format(s.plusOne(head)));
      };

  @Test
  public void test() {

    c.accept(ListNode.from(1, 2, 3));
    c.accept(ListNode.from(9, 9));
    c.accept(ListNode.from(1, 2, 9));
    c.accept(ListNode.from(3));
    c.accept(null);
  }
}
