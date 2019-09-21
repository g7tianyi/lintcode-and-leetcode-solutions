package com.g7tianyi.lintcode.list;

import com.g7tianyi.common.ListNode;
import com.g7tianyi.common.Strings;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 21, 2019
 *
 * @link https://www.lintcode.com/problem/rotate-list/description
 */
public class RotateList {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public ListNode rotateRight(ListNode head, int k) {

      int len = getListLength(head);
      if (len < 2 || k % len == 0) {
        return head;
      }

      // 1. 移动到新的头结点
      k %= len;
      int count = 0;
      ListNode prev = null, curr = head;
      while (count < len - k) {
        prev = curr;
        curr = curr.next;
        ++count;
      }

      // 2. 新的头结点的前一个结点变为链表尾，即next变为空
      ListNode result = curr;
      if (prev != null) {
        prev.next = null;
      }

      // 3. 找到尾结点，并将尾结点指向原来的头结点
      ListNode tail = curr;
      while (tail.next != null) {
        tail = tail.next;
      }
      tail.next = head;

      return result;
    }

    private int getListLength(ListNode head) {
      ListNode curr = head;
      int len = 0;
      while (curr != null) {
        curr = curr.next;
        ++len;
      }
      return len;
    }
  }

  @AllArgsConstructor
  private class Case {
    ListNode head;
    int k;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c =
        aCase -> {
          log.info("%s | %d", Strings.format(aCase.head), aCase.k);
          log.info(Strings.format(s.rotateRight(aCase.head, aCase.k)));
          log.info();
        };

    c.accept(new Case(ListNode.from(1, 2, 3, 4, 5), 1));
    c.accept(new Case(ListNode.from(1, 2, 3, 4, 5), 2));
    c.accept(new Case(ListNode.from(1, 2, 3, 4, 5), 3));
    c.accept(new Case(ListNode.from(1, 2, 3, 4, 5), 5));
    c.accept(new Case(ListNode.from(1, 2, 3, 4, 5), 6));
    c.accept(new Case(ListNode.from(1, 2, 3, 4, 5), 9));

    c.accept(new Case(ListNode.from(3, 2, 1), 1));
    c.accept(new Case(ListNode.from(3, 9), 10));
    c.accept(new Case(ListNode.from(3, 9), 3));
    c.accept(new Case(ListNode.from(3), 1));
    c.accept(new Case(ListNode.from(3), 10));
    c.accept(new Case(null, 1));
  }
}
