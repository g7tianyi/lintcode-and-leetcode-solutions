package com.g7tianyi.lintcode.list;

import com.g7tianyi.common.ListNode;
import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 24, 2019
 *
 * @link https://www.lintcode.com/problem/insertion-sort-list/description
 */
public class InsertionSortList {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public ListNode insertionSortList(ListNode head) {
      if (head == null) {
        return null;
      }

      ListNode prev = head;
      ListNode curr = head.next;
      ListNode next;

      int size = 1;

      // 1, 3, 2, 0
      while (curr != null) {
        next = curr.next;

        ListNode sPrev = null; // sorted prev
        ListNode sCurr = head; // sorted curr
        int index = 0;
        while (index < size) {
          if (sCurr.val >= curr.val) {
            prev.next = next;
            if (sPrev == null) { // 插到已排序部分的头部
              curr.next = head;
              head = curr;
            } else { // 插在已排序部分的中间
              sPrev.next = curr;
              curr.next = sCurr;
            }
            break;
          }

          index++;
          sPrev = sCurr;
          sCurr = sCurr.next;
        }

        if (index == size) { // 插在已排序部分的尾部，相当于没有对当前节点做任何移动
          prev = curr;
        }

        curr = next;
        size++;
      }

      return head;
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<ListNode> runner =
        listNode -> {
          log.info(listNode);
          log.info(s.insertionSortList(listNode));
          log.info("");
        };

    runner.accept(null);
    runner.accept(ListNode.from(0));
    runner.accept(ListNode.from(2, 1));
    runner.accept(ListNode.from(1, 1, 1, 1, 1, 1, 1));
    runner.accept(ListNode.from(9, 8, 7, 6, 5, 4, 3));
    runner.accept(ListNode.from(3, 4, 5, 6, 7, 8));
    runner.accept(ListNode.from(1, 3, 2, 0));
    runner.accept(ListNode.from(3, 1, 7, 0));

    for (int i = 0; i < 10; i++) {
      runner.accept(ListNode.randomListOf(10));
    }
  }
}
