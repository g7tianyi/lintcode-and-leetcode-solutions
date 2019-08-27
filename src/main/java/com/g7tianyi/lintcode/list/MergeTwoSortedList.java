package com.g7tianyi.lintcode.list;

import com.g7tianyi.lintcode.common.ListNode;
import com.g7tianyi.lintcode.util.Log;
import com.g7tianyi.lintcode.util.Console;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 24, 2019
 *
 * @link https://www.lintcode.com/problem/merge-two-sorted-lists/description
 */
public class MergeTwoSortedList {

  private static final Log log = new Log();

  public class Solution {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
      if (l1 == null) {
        return l2;
      }
      if (l2 == null) {
        return l1;
      }

      ListNode head, node1 = l1, node2 = l2;
      if (l1.val < l2.val) {
        head = l1;
        node1 = node1.next;
      } else {
        head = l2;
        node2 = node2.next;
      }

      ListNode curr = head;
      while (node1 != null && node2 != null) {
        if (node1.val < node2.val) {
          curr.next = node1;
          node1 = node1.next;
        } else {
          curr.next = node2;
          node2 = node2.next;
        }
        curr = curr.next;
      }

      if (node1 != null) {
        curr.next = node1;
      }
      if (node2 != null) {
        curr.next = node2;
      }

      return head;
    }
  }

  @AllArgsConstructor
  public static class Input {

    private ListNode l1;
    private ListNode l2;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Input> runner =
        input -> {
          Console.log(input.l1);
          Console.log(input.l2);
          Console.log(s.mergeTwoLists(input.l1, input.l2));
          log.info("\n");
        };

    runner.accept(new Input(null, ListNode.makeListFrom(0, 3, 3)));
    runner.accept(new Input(ListNode.makeListFrom(0, 3, 3), null));
    runner.accept(new Input(ListNode.makeListFrom(1, 3, 8, 11, 15), ListNode.makeListFrom(2)));
    runner.accept(new Input(ListNode.makeRandomSortedList(1), ListNode.makeRandomSortedList(7)));
    runner.accept(new Input(ListNode.makeRandomSortedList(7), ListNode.makeRandomSortedList(1)));
    runner.accept(new Input(ListNode.makeRandomSortedList(10), ListNode.makeRandomSortedList(10)));
  }
}
