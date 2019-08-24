package com.g7tianyi.lintcode.list;

import com.g7tianyi.lintcode.common.ListNode;
import com.g7tianyi.lintcode.util.Console;
import com.g7tianyi.lintcode.util.Log;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 24, 2019
 *
 * <p>Problem link: https://www.lintcode.com/problem/remove-linked-list-elements/description
 */
public class RemoveLinkedListElements {

  private static final Log log = new Log();

  public class Solution {

    public ListNode removeElements(ListNode head, int val) {
      if (head == null) {
        return null;
      }

      ListNode node = head;
      while (node != null && node.val == val) {
        node = node.next;
      }
      if (node == null) {
        return null;
      }

      ListNode prev = node;
      ListNode curr = node.next;
      while (curr != null) {
        if (curr.val == val) {
          prev.next = curr.next;
        } else {
          prev = curr;
        }
        curr = curr.next;
      }

      return head;
    }
  }

  @AllArgsConstructor
  private static final class Input {

    private ListNode head;

    private int val;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Input> runner =
        input -> {
          log.info(Console.stringify(input.head) + " " + input.val);
          Console.log(s.removeElements(input.head, input.val));
          log.info("");
        };

    runner.accept(new Input(ListNode.makeListFrom(1, 2, 3, 3, 4, 5, 3), 3));
    runner.accept(new Input(ListNode.makeListFrom(1, 1), 1));
    runner.accept(new Input(ListNode.makeListFrom(1, 2, 3, 4, 4, 5, 5), 6));
  }
}
