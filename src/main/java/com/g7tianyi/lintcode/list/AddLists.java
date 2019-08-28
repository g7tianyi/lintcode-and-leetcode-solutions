package com.g7tianyi.lintcode.list;

import com.g7tianyi.common.ListNode;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 24, 2019
 *
 * @link https://www.lintcode.com/problem/add-two-numbers/description
 */
public class AddLists {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public ListNode addLists(ListNode l1, ListNode l2) {
      if (l1 == null) {
        return l2;
      }
      if (l2 == null) {
        return l1;
      }

      int sum, carry = 0;
      ListNode head = new ListNode(0);
      ListNode curr = head;

      while (l1 != null && l2 != null) {

        sum = l1.val + l2.val + carry;

        if (sum >= 10) {
          sum = sum % 10;
          carry = 1;
        } else {
          carry = 0;
        }

        curr.next = new ListNode(sum);
        curr = curr.next;

        l1 = l1.next;
        l2 = l2.next;
      }

      ListNode l = null;
      if (l1 != null) {
        l = l1;
      } else if (l2 != null) {
        l = l2;
      }

      while (l != null) {
        sum = l.val + carry;
        if (sum >= 10) {
          sum = sum % 10;
          carry = 1;
        } else {
          carry = 0;
        }
        curr.next = new ListNode(sum);
        curr = curr.next;

        l = l.next;
      }

      if (carry != 0) {
        curr.next = new ListNode(carry);
      }

      return head.next;
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
          log.info(input.l1);
          log.info(input.l2);
          log.info(s.addLists(input.l1, input.l2));
          log.info("\n");
        };

    runner.accept(new Input(null, ListNode.from(0, 3, 3)));
    runner.accept(new Input(ListNode.from(0, 3, 3), null));
    runner.accept(new Input(ListNode.from(9, 9), ListNode.from(9)));
    runner.accept(new Input(ListNode.from(9), ListNode.from(9, 9, 9, 9, 9, 9)));
    runner.accept(new Input(ListNode.from(7, 1, 6), ListNode.from(5, 9, 2)));
    runner.accept(new Input(ListNode.from(3, 1, 5), ListNode.from(5, 9, 2)));
    runner.accept(new Input(ListNode.from(1, 3, 5), ListNode.randomSortedListOf(2, 3)));
    runner.accept(new Input(ListNode.from(2, 0, 3), ListNode.randomSortedListOf(0, 3)));
  }
}
