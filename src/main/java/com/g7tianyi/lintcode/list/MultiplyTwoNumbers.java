package com.g7tianyi.lintcode.list;

import com.g7tianyi.common.ListNode;
import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Sep 30, 2019
 *
 * @link https://www.lintcode.com/problem/multiply-two-numbers/description
 */
public class MultiplyTwoNumbers {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public long multiplyLists(ListNode l1, ListNode l2) {
      return from(l1) * from(l2);
    }

    private long from(ListNode head) {
      long num = 0;
      while (head != null) {
        num = num * 10 + head.val;
        head = head.next;
      }
      return num;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.multiplyLists(ListNode.from(9, 4, 6), ListNode.from(8, 4)));
    log.info(s.multiplyLists(ListNode.from(3, 2, 1), ListNode.from(1, 2)));
  }
}
