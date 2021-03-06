package com.g7tianyi.lintcode.list;

import com.g7tianyi.common.ListNode;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 24, 2019
 *
 * @link https://www.lintcode.com/problem/delete-node-in-a-linked-list/description
 */
public class DeleteNodeInALinkedList {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public void deleteNode(ListNode node) {
      if (node == null) {
        return;
      }

      ListNode next = node.next;
      node.val = next.val;
      node.next = next.next;
      next.next = null;
    }
  }

  @AllArgsConstructor
  private static class Input {

    private ListNode head;

    private int n;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Input> runner =
        input -> {
          log.info(input.head);

          ListNode node = input.head;
          for (int i = 0; i < input.n; i++) {
            node = node.next;
          }
          s.deleteNode(node);

          log.info(input.head);
          log.info("");
        };

    for (int i = 1; i < 9; i++) {
      ListNode head = ListNode.sortedListOf(10);
      runner.accept(new Input(head, i));
    }

    s.deleteNode(null);
  }
}
