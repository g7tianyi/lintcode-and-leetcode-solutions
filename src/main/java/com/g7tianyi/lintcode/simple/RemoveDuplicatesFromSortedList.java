package com.g7tianyi.lintcode.simple;

import com.g7tianyi.lintcode.common.ListNode;
import com.g7tianyi.lintcode.util.Log;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 24, 2019
 *
 * <p>Problem link: https://www.lintcode.com/problem/remove-duplicates-from-sorted-list/description
 */
public class RemoveDuplicatesFromSortedList {

  private static final Log log = new Log();

  public class Solution {

    public ListNode deleteDuplicates(ListNode head) {

      if (head == null || head.next == null) {
        return head;
      }

      // 输入: 1->1->2->3->3->null
      // 输出: 1->2->3->null

      int value = head.val;
      ListNode prev = head;
      ListNode curr = head.next;
      ListNode next;

      while (curr != null) {
        next = curr.next;
        if (curr.val == value) {
          prev.next = next;
        } else {
          prev = curr;
          value = curr.val;
        }
        curr = next;
      }

      return head;
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<ListNode> runner =
        listNode -> {
          ListNode.print(listNode);
          ListNode.print(s.deleteDuplicates(listNode));
          log.info("\n");
        };

    runner.accept(null);
    runner.accept(ListNode.makeRandomSortedList(1));
    runner.accept(ListNode.makeRandomSortedList(4));
    runner.accept(ListNode.makeRandomSortedList(4));
    runner.accept(ListNode.makeRandomSortedList(5));
    runner.accept(ListNode.makeRandomSortedList(10));
    runner.accept(ListNode.makeRandomSortedList(10));
    runner.accept(ListNode.makeRandomSortedList(10));
    runner.accept(ListNode.makeListFrom(1, 1, 2));
    runner.accept(ListNode.makeListFrom(1, 1, 2, 3, 3));
  }
}
