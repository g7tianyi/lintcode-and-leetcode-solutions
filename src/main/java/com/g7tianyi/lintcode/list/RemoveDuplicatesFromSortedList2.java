package com.g7tianyi.lintcode.list;

import com.g7tianyi.common.ListNode;
import com.g7tianyi.common.Strings;
import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 24, 2019
 *
 * @link https://www.lintcode.com/problem/remove-duplicates-from-sorted-list/description
 */
public class RemoveDuplicatesFromSortedList2 {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public ListNode deleteDuplicates(ListNode head) {

      if (head == null || head.next == null) {
        return head;
      }

      // 输入: 1->1->2->3->3->4->null
      // 输出: 2->4->null
      ListNode sentry = new ListNode(head.val + 1);
      sentry.next = head;

      ListNode former = sentry;
      ListNode curr = head, next;
      while (curr != null) {
        next = curr.next;
        while (next != null && next.val == curr.val) {
          next = next.next;
        }
        if (curr.next != next) {
          former.next = next;
          curr = next;
        } else {
          former = curr;
          curr = curr.next;
        }
      }
      return sentry.next;
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<ListNode> runner =
        listNode -> {
          log.info(Strings.format(listNode));
          log.info(Strings.format(s.deleteDuplicates(listNode)));
          log.info();
        };

    runner.accept(null);
    runner.accept(ListNode.from(1));
    runner.accept(ListNode.from(1, 1, 2));
    runner.accept(ListNode.from(1, 1, 2, 3, 4, 5, 5, 5));
    runner.accept(ListNode.from(1, 1, 2, 3, 3, 3, 4, 5));
    runner.accept(ListNode.from(1, 1, 2, 2, 3, 3, 4, 4));
    runner.accept(ListNode.from(1, 2, 3, 4, 5, 6, 7, 8, 9));
  }
}
