package com.g7tianyi.lintcode.list;

import com.g7tianyi.common.ListNode;
import com.g7tianyi.common.Strings;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 30, 2019
 *
 * @link https://www.lintcode.com/problem/add-two-numbers-ii/description
 */
public class AddTwoNumbers2 {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public ListNode addLists2(ListNode l1, ListNode l2) {
      if (l1 == null) {
        return l2;
      }
      if (l2 == null) {
        return l1;
      }

      ListNode node1 = reverseList(l1);
      ListNode node2 = reverseList(l2);

      int carry = 0, sum;
      ListNode sentry = new ListNode(0);
      ListNode curr = sentry;
      while (node1 != null && node2 != null) {
        sum = node1.val + node2.val + carry;
        carry = sum / 10;
        curr.next = new ListNode(sum % 10);
        curr = curr.next;

        node1 = node1.next;
        node2 = node2.next;
      }

      while (node1 != null) {
        sum = node1.val + carry;
        carry = sum / 10;
        curr.next = new ListNode(sum % 10);
        curr = curr.next;

        node1 = node1.next;
      }

      while (node2 != null) {
        sum = node2.val + carry;
        carry = sum / 10;
        curr.next = new ListNode(sum % 10);
        curr = curr.next;

        node2 = node2.next;
      }

      if (carry != 0) {
        curr.next = new ListNode(carry);
      }

      return reverseList(sentry.next);
    }

    private ListNode reverseList(ListNode node) {
      ListNode prev = null, curr = node, next, head;
      while (true) {
        next = curr.next;
        curr.next = prev;
        prev = curr;
        curr = next;
        if (curr == null) {
          head = prev;
          break;
        }
      }
      return head;
    }
  }

  private final Solution s = new Solution();

  @AllArgsConstructor
  private class Case {
    private ListNode l1;
    private ListNode l2;
  }

  private final Consumer<Case> c =
      aCase -> {
        log.info(Strings.format(aCase.l1));
        log.info(Strings.format(aCase.l2));
        log.info(Strings.format(s.addLists2(aCase.l1, aCase.l2)));
        log.info();
      };

  @Test
  public void test() {
    c.accept(new Case(ListNode.from(1), ListNode.from(9, 9, 9, 9)));
    c.accept(new Case(ListNode.from(6, 1, 7), ListNode.from(2, 9, 5)));
    c.accept(new Case(ListNode.from(1, 2, 3), ListNode.from(2, 3, 4)));
  }
}
