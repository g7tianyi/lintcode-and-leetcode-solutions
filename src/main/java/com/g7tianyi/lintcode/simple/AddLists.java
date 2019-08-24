package com.g7tianyi.lintcode.simple;

import com.g7tianyi.lintcode.common.ListNode;
import com.g7tianyi.lintcode.util.Log;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 24, 2019
 *
 * <p>Problem link: https://www.lintcode.com/problem/add-two-numbers/description
 */
public class AddLists {

  private static final Log log = new Log();

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
          ListNode.print(input.l1);
          ListNode.print(input.l2);
          ListNode.print(s.addLists(input.l1, input.l2));
          log.info("\n");
        };

    runner.accept(new Input(null, ListNode.makeListFrom(0, 3, 3)));
    runner.accept(new Input(ListNode.makeListFrom(0, 3, 3), null));
    runner.accept(new Input(ListNode.makeListFrom(9, 9), ListNode.makeListFrom(9)));
    runner.accept(new Input(ListNode.makeListFrom(9), ListNode.makeListFrom(9, 9, 9, 9, 9, 9)));
    runner.accept(new Input(ListNode.makeListFrom(7, 1, 6), ListNode.makeListFrom(5, 9, 2)));
    runner.accept(new Input(ListNode.makeListFrom(3, 1, 5), ListNode.makeListFrom(5, 9, 2)));
    runner.accept(new Input(ListNode.makeListFrom(1, 3, 5), ListNode.makeRandomSortedList(2, 3)));
    runner.accept(new Input(ListNode.makeListFrom(2, 0, 3), ListNode.makeRandomSortedList(0, 3)));
  }
}
