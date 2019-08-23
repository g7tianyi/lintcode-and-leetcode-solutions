package com.g7tianyi.lintcode.simple;

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

  public static class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
      next = null;
    }
  }

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

  private static void printList(ListNode listNode) {
    StringBuilder sb = new StringBuilder("[");
    while (listNode != null) {
      sb.append(listNode.val).append("-");
      listNode = listNode.next;
    }
    if (sb.length() > 1) {
      sb.deleteCharAt(sb.length() - 1);
    }
    sb.append("]\n");
    log.info(sb.toString());
  }

  @Test
  public void test() {

    Solution s = new Solution();

    RecursiveSolution rs = new RecursiveSolution();

    Consumer<ListNode> runner =
        listNode -> {
          // printList(rs.reverse(listNode));
          printList(s.reverse(listNode));
          log.info("\n");
        };

    runner.accept(null);

    ListNode listNode = new ListNode(1);
    runner.accept(listNode);

    ListNode currNode = listNode;
    for (int i = 2; i < 10; i++) {
      currNode.next = new ListNode(i);
      currNode = currNode.next;
    }
    runner.accept(listNode);
  }
}
