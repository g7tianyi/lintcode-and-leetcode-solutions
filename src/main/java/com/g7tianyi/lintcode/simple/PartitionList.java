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
 * <p>Problem link: https://www.lintcode.com/problem/partition-list/description
 */
public class PartitionList {

  private static final Log log = new Log();

  public class Solution {

    public ListNode partition(ListNode head, int x) {

      if (head == null || head.next == null) {
        return head;
      }

      ListNode prev = null;
      ListNode curr = head;
      while (curr != null && curr.val < x) {
        prev = curr;
        curr = curr.next;
      }
      if (curr == null) { // (*)
        return head;
      }

      // 输入: 1->4->3->2->5->2->null, x = 3
      // 输出: 1->2->2->4->3->5->null

      ListNode small = prev;

      // 单链表，当我们把一个节点
      // 1) 移动到头部时：需要修改节点的前向指针的next指针、自身的next指针和头指针，见代码(#1)行
      // 2) 移动到另一节点P的后面：需要修改节点的前向指针的next、自身的next指针和P的next指针，见代码(#2)行
      while (curr != null) {
        ListNode next = curr.next;
        if (curr.val < x) {
          prev.next = curr.next; // 因为(*)行的检查，所以prev不可能会为null
          if (small == null) { // (#1)
            curr.next = head;
            small = curr;
            head = small;
          } else { // (#2)
            curr.next = small.next;
            small.next = curr;
            small = curr;
          }
        } else {
          prev = curr;
        }

        curr = next;
      }

      return head;
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
          log.info("x: %d", input.x);
          Console.log(input.head);
          Console.log(s.partition(input.head, input.x));
          log.info("\n");
        };

    runner.accept(new Input(null, 0));
    runner.accept(new Input(ListNode.makeRandomList(1), 1));
    runner.accept(new Input(ListNode.makeRandomList(2), 10));
    runner.accept(new Input(ListNode.makeRandomList(2), -1));
    runner.accept(new Input(ListNode.makeRandomList(2), 1));
    runner.accept(new Input(ListNode.makeRandomList(16), 5));
    runner.accept(new Input(ListNode.makeRandomList(16), 0));
    runner.accept(new Input(ListNode.makeRandomList(15), 100000));
    runner.accept(new Input(ListNode.makeListFrom(1, 4, 3, 2, 5, 2), 3));
    runner.accept(
        new Input(
            ListNode.makeListFrom(15, 4, 9, 1, 11, 12, 15, 4, 2, 0, 15, 1, 14, 11, 16, 7), 5));
    runner.accept(
        new Input(
            ListNode.makeListFrom(8, 11, 13, 7, 11, 3, 6, 8, 3, 10, 12, 1, 14, 5, 11, 0), 5));
  }
}
