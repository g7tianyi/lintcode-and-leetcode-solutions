package com.g7tianyi.lintcode.list;

import com.g7tianyi.common.ListNode;
import com.g7tianyi.common.Strings;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 29, 2019
 *
 * @link https://www.lintcode.com/problem/swap-two-nodes-in-linked-list/description
 */
public class SwapTwoNodesInLinkedList {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public ListNode swapNodes(ListNode head, int v1, int v2) {
      if (head == null || head.next == null) {
        return head;
      }

      ListNode sentry = new ListNode(0);
      sentry.next = head;

      ListNode prev1 = null, prev2 = null;
      ListNode curr = head, prev = sentry;

      while (curr != null) {
        if (curr.val == v1 && prev1 == null) {
          prev1 = prev;
        } else if (curr.val == v2) {
          prev2 = prev;
        }
        if (prev1 != null && prev2 != null) {
          break;
        }
        prev = curr;
        curr = curr.next;
      }

      if (prev1 != null && prev2 != null) {

        if (prev2.next == prev1) {
          ListNode temp = prev2;
          prev2 = prev1;
          prev1 = temp;
        }

        ListNode curr1 = prev1.next;
        ListNode curr2 = prev2.next;
        ListNode next2 = curr2.next;
        if (prev1.next == prev2) {
          prev1.next = curr2;
          curr2.next = curr1;
          curr1.next = next2;
        } else {
          prev1.next = curr2;
          curr2.next = curr1.next;

          prev2.next = curr1;
          curr1.next = next2;
        }
      }

      return sentry.next;
    }
  }

  private final Solution s = new Solution();

  @AllArgsConstructor
  private class Case {
    ListNode head;
    int v1, v2;
  }

  private final Consumer<Case> c =
      aCase -> {
        log.info("%s | %d %d", Strings.format(aCase.head), aCase.v1, aCase.v2);
        log.info("%s", Strings.format(s.swapNodes(aCase.head, aCase.v1, aCase.v2)));
      };

  @Test
  public void test() {
    c.accept(new Case(ListNode.from(1, 2, 3, 4), 2, 4));
    c.accept(new Case(ListNode.from(1), 2, 1));

    c.accept(new Case(ListNode.from(10, 1, 2, 3), 1, 2));
    c.accept(new Case(ListNode.from(10, 1, 2, 3), 2, 1));
    c.accept(new Case(ListNode.from(1, 2, 3, 4), 2, 1));
  }
}
