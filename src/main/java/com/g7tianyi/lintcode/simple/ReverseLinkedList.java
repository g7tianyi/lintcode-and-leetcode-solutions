package com.g7tianyi.lintcode.simple;

import com.g7tianyi.lintcode.common.ListNode;
import com.g7tianyi.lintcode.util.Log;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 23, 2019
 *
 * <p>Problem link: https://www.lintcode.com/problem/reverse-linked-list/description
 */
public class ReverseLinkedList {

  private static final Log log = new Log();

  // 链表反转，意味着一定是没有环的
  public class Solution {

    public ListNode reverse(ListNode head) {

      if (head == null) {
        return null;
      }

      ListNode curr = head.next;
      ListNode prev = head;
      prev.next = null;
      ListNode next;

      while (curr != null) {
        next = curr.next;

        curr.next = prev;
        prev = curr;
        curr = next;
      }

      return prev;
    }
  }

  public class RecursiveSolution {

    public ListNode reverse(ListNode head) {

      if (head == null) {
        return null;
      }

      if (head.next == null) {
        return head;
      }

      return reverse(null, head);
    }

    private ListNode reverse(ListNode prev, ListNode curr) {
      ListNode head;
      if (curr.next == null) {
        head = curr;
      } else {
        head = reverse(curr, curr.next);
      }
      curr.next = prev;
      return head;
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    RecursiveSolution rs = new RecursiveSolution();

    Consumer<ListNode> runner =
        listNode -> {
          // printList(rs.reverse(listNode));
          ListNode.print(s.reverse(listNode));
          log.info("\n");
        };

    runner.accept(null);
    runner.accept(ListNode.makeList(1));
    runner.accept(ListNode.makeList(8));
  }
}
