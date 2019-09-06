package com.g7tianyi.lintcode.list;

import com.g7tianyi.common.ListNode;
import com.g7tianyi.common.Strings;
import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 06, 2019
 *
 * @link https://www.lintcode.com/problem/sort-list/description
 */
public class SortList {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public ListNode sortList(ListNode head) {

      if (head == null || head.next == null) {
        return head;
      }

      ListNode first = head, second;
      ListNode mid = getMiddle(head);
      second = mid.next;
      mid.next = null; // separate listNode
      first = sortList(first);
      second = sortList(second);
      return merge(first, second);
    }

    private ListNode merge(ListNode first, ListNode second) {
      if (first == null) {
        return second;
      }
      if (second == null) {
        return first;
      }

      ListNode result = new ListNode(0);
      ListNode curr = result;

      while (first != null && second != null) {
        if (first.val < second.val) {
          curr.next = first;
          curr = curr.next;
          first = first.next;
        } else {
          curr.next = second;
          curr = curr.next;
          second = second.next;
        }
      }

      if (first != null) {
        curr.next = first;
      }
      if (second != null) {
        curr.next = second;
      }
      return result.next;
    }

    private ListNode getMiddle(ListNode head) {
      ListNode slow = head, fast = head.next;

      while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
      }
      return slow; // return 1 (2) 3 or 1 (2) 3 4
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<ListNode> c = listNode -> log.info(Strings.format(s.sortList(listNode)));

    c.accept(ListNode.randomListOf(10, 100));
    c.accept(ListNode.randomListOf(7, 100));
    c.accept(ListNode.randomListOf(1, 100));
    c.accept(ListNode.randomListOf(2, 100));
  }
}
