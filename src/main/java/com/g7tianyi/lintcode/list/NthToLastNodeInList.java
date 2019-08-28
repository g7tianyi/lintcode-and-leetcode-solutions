package com.g7tianyi.lintcode.list;

import com.g7tianyi.common.ListNode;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 24, 2019
 *
 * @link https://www.lintcode.com/problem/nth-to-last-node-in-list/description
 */
public class NthToLastNodeInList {

  private static final Logger log = new Logger();

  public class Solution {

    public ListNode nthToLast(ListNode head, int n) {

      if (head == null) {
        return null;
      }

      ListNode fast = head;
      for (int i = 0; i < n; i++) {
        fast = fast.next;
      }

      ListNode slow = head;
      while (fast != null) {
        fast = fast.next;
        slow = slow.next;
      }

      return slow;
    }
  }

  @AllArgsConstructor
  private static final class Input {

    private ListNode head;

    private int x;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Input> runner =
        input -> {
          log.info(input.head);
          log.info("%d => %s", input.x, s.nthToLast(input.head, input.x));
          log.info("\n");
        };

    runner.accept(new Input(null, 0));
    runner.accept(new Input(ListNode.sortedListOf(1), 1));
    runner.accept(new Input(ListNode.sortedListOf(4), 2));
    runner.accept(new Input(ListNode.sortedListOf(4), 4));
    runner.accept(new Input(ListNode.sortedListOf(5), 1));
    runner.accept(new Input(ListNode.sortedListOf(10), 9));
    runner.accept(new Input(ListNode.sortedListOf(10), 1));
    runner.accept(new Input(ListNode.sortedListOf(10), 5));
    runner.accept(new Input(ListNode.from(3, 2, 1, 5), 2));
    runner.accept(new Input(ListNode.from(1, 2, 3), 3));
  }
}
