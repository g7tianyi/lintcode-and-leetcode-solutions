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
 * <p>Problem link: https://www.lintcode.com/problem/delete-node-in-a-linked-list/description
 */
public class DeleteNodeInALinkedList {

  private static final Log log = new Log();

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
          Console.log(input.head);

          ListNode node = input.head;
          for (int i = 0; i < input.n; i++) {
            node = node.next;
          }
          s.deleteNode(node);

          Console.log(input.head);
          log.info("");
        };

    for (int i = 1; i < 9; i++) {
      ListNode head = ListNode.makeBeautifulList(10);
      runner.accept(new Input(head, i));
    }

    s.deleteNode(null);
  }
}
