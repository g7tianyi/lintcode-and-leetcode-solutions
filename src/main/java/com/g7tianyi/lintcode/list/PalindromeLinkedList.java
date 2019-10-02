package com.g7tianyi.lintcode.list;

import com.g7tianyi.common.ListNode;
import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Oct 02, 2019
 *
 * @link https://www.lintcode.com/problem/palindrome-linked-list/description
 */
public class PalindromeLinkedList {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public boolean isPalindrome(ListNode head) {
      if (head == null) {
        return true;
      }

      // 1. 找到中点
      ListNode slow = head, fast = head.next; // fast从第二个节点开始，注意这些细节
      while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
      }

      // 2. 后半截翻转
      ListNode latter = reverse(slow.next);

      ListNode former = head;
      while (former != null && latter != null && former.val == latter.val) {
        former = former.next;
        latter = latter.next;
      }
      return latter == null;
    }

    private ListNode reverse(ListNode head) {
      ListNode prev = null, curr = head, next;
      while (curr != null) {
        next = curr.next;
        curr.next = prev;
        prev = curr;
        curr = next;
      }
      return prev;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.isPalindrome(ListNode.from(1, 2, 3, 3, 2, 1)));
    log.info(s.isPalindrome(ListNode.from(1, 2, 3, 2, 1)));
    log.info(s.isPalindrome(ListNode.from(1, 2, 3, 1)));
    log.info(s.isPalindrome(ListNode.from(1, 2, 2, 3)));
    log.info(s.isPalindrome(ListNode.from(1, 2, 3, 2, 3)));
    log.info(s.isPalindrome(ListNode.from(1, 2, 3, 3, 1)));
  }
}
