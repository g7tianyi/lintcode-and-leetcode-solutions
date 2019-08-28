package com.g7tianyi.lintcode.list;

import com.g7tianyi.common.ListNode;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 24, 2019
 *
 * @link https://www.lintcode.com/problem/swap-nodes-in-pairs/description
 */
public class SwapNodesInPairs {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public ListNode swapPairs(ListNode head) {
      if (head == null) {
        return null;
      }

      // 输入：1->2->3->4->null
      // 输出：2->1->4->3->null
      // 输入：5->null
      // 输出：5->null

      ListNode firstPrev = null; // 第一个节点的前置指针
      ListNode first = head;
      ListNode second = head.next;
      ListNode nextFirst;
      ListNode nextSecond;

      while (first != null && second != null) {
        nextFirst = first.next.next; // first.next也就是second，second绝不会为空的
        if (second.next != null) {
          nextSecond = second.next.next;
        } else {
          nextSecond = null;
        }

        if (firstPrev == null) {
          head = second;
          first.next = second.next;
          second.next = first;
        } else {
          firstPrev.next = second;
          first.next = second.next;
          second.next = first;
        }

        firstPrev = first;
        first = nextFirst;
        second = nextSecond;
      }

      return head;
    }
  }

  @AllArgsConstructor
  private static final class Input {

    private ListNode head;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Input> runner =
        input -> {
          log.info(input.head);
          log.info(s.swapPairs(input.head));
          log.info("");
        };

    runner.accept(new Input(null));
    runner.accept(new Input(ListNode.from(1, 2, 3, 4)));
    runner.accept(new Input(ListNode.from(5)));
    runner.accept(new Input(ListNode.randomListOf(1)));
    runner.accept(new Input(ListNode.randomListOf(2)));
    runner.accept(new Input(ListNode.randomListOf(3)));
    runner.accept(new Input(ListNode.randomListOf(6)));
    runner.accept(new Input(ListNode.randomListOf(7)));
  }
}
