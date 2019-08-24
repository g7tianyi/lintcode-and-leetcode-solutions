package com.g7tianyi.lintcode.medium;

import com.g7tianyi.lintcode.common.ListNode;
import com.g7tianyi.lintcode.util.Log;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 23, 2019
 *
 * <p>Problem link: https://www.lintcode.com/problem/reverse-linked-list-ii/description
 */
public class ReverseLinkedList2 {

  private static final Log log = new Log();

  // 链表反转，意味着一定是没有环的
  public class Solution {

    public ListNode reverseBetween(ListNode head, int m, int n) {
      if (head == null) {
        return null;
      }

      if (m == n) {
        return head;
      }

      ListNode sentry = new ListNode(0); // 哨兵节点
      sentry.next = head;

      ListNode left = sentry;
      int index = 1;
      while (index != m) {
        index++;
        left = left.next;
      }

      ListNode curr = left.next;
      ListNode prev = null;
      ListNode next;

      while (index != n + 1) {
        index++;

        next = curr.next;
        curr.next = prev;
        prev = curr;
        curr = next;
      }

      left.next.next = curr;
      left.next = prev;

      return sentry.next;
    }
  }

  @AllArgsConstructor
  private static final class Input {

    private ListNode head;

    private int m, n;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Input> runner =
        input -> {
          log.info("range(%d, %d)", input.m, input.n);
          ListNode.print(input.head);
          ListNode.print(s.reverseBetween(input.head, input.m, input.n));
          log.info("");
        };

    runner.accept(new Input(null, 3, 5));
    runner.accept(new Input(ListNode.makeList(1), 1, 1));
    runner.accept(new Input(ListNode.makeList(2), 1, 2));
    runner.accept(new Input(ListNode.makeList(3), 2, 3));
    runner.accept(new Input(ListNode.makeList(8), 3, 5));
    runner.accept(new Input(ListNode.makeList(8), 3, 4));
    runner.accept(new Input(ListNode.makeList(8), 3, 3));
    runner.accept(new Input(ListNode.makeList(8), 3, 8));
    runner.accept(new Input(ListNode.makeList(8), 1, 5));
    runner.accept(new Input(ListNode.makeList(8), 1, 8));
  }
}
