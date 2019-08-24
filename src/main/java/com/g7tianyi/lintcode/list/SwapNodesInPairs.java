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
 * <p>Problem link: https://www.lintcode.com/problem/swap-nodes-in-pairs/description
 */
public class SwapNodesInPairs {

  private static final Log log = new Log();

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
          Console.log(input.head);
          Console.log(s.swapPairs(input.head));
          log.info("");
        };

    runner.accept(new Input(null));
    runner.accept(new Input(ListNode.makeListFrom(1, 2, 3, 4)));
    runner.accept(new Input(ListNode.makeListFrom(5)));
    runner.accept(new Input(ListNode.makeRandomList(1)));
    runner.accept(new Input(ListNode.makeRandomList(2)));
    runner.accept(new Input(ListNode.makeRandomList(3)));
    runner.accept(new Input(ListNode.makeRandomList(6)));
    runner.accept(new Input(ListNode.makeRandomList(7)));
  }
}
