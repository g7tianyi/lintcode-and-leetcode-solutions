package com.g7tianyi.lintcode.simple;

import com.g7tianyi.lintcode.common.ListNode;
import com.g7tianyi.lintcode.util.Log;
import com.g7tianyi.lintcode.util.Console;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 24, 2019
 *
 * <p>Problem link: https://www.lintcode.com/problem/nth-to-last-node-in-list/description
 */
public class NthToLastNodeInList {

  private static final Log log = new Log();

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
          Console.log(input.head);
          log.info("%d => %s", input.x, s.nthToLast(input.head, input.x));
          log.info("\n");
        };

    runner.accept(new Input(null, 0));
    runner.accept(new Input(ListNode.makeBeautifulList(1), 1));
    runner.accept(new Input(ListNode.makeBeautifulList(4), 2));
    runner.accept(new Input(ListNode.makeBeautifulList(4), 4));
    runner.accept(new Input(ListNode.makeBeautifulList(5), 1));
    runner.accept(new Input(ListNode.makeBeautifulList(10), 9));
    runner.accept(new Input(ListNode.makeBeautifulList(10), 1));
    runner.accept(new Input(ListNode.makeBeautifulList(10), 5));
    runner.accept(new Input(ListNode.makeListFrom(3, 2, 1, 5), 2));
    runner.accept(new Input(ListNode.makeListFrom(1, 2, 3), 3));
  }
}
