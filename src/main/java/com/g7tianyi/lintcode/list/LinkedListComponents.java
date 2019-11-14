package com.g7tianyi.lintcode.list;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.common.ListNode;
import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Nov 14, 2019
 *
 * @link https://www.lintcode.com/problem/linked-list-components/description
 */
public class LinkedListComponents {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int numComponents(ListNode head, int[] G) {
      int len = listLength(head);
      if (len == 0) {
        return 0;
      }

      int[] nodeHash = new int[len];
      ListNode curr = head;
      int pos = 0;
      while (curr != null) {
        nodeHash[curr.val] = pos++;
        curr = curr.next;
      }

      boolean[] flags = new boolean[len];
      for (int num : G) {
        flags[nodeHash[num]] = true;
      }

      int result = 0, i = 0;
      while (i < len) {
        while (i < len && !flags[i]) {
          ++i;
        }
        if (i < len) {
          ++result;
        }
        while (i < len && flags[i]) {
          ++i;
        }
      }

      return result;
    }

    private int listLength(ListNode head) {
      int len = 0;
      while (head != null) {
        head = head.next;
        ++len;
      }
      return len;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.numComponents(null, Arrays.from(0, 1, 3)));
    log.info(s.numComponents(ListNode.from(0, 1, 2, 3), Arrays.from(0, 1, 3)));
    log.info(s.numComponents(ListNode.from(0, 1, 2, 3, 4), Arrays.from(0, 3, 1, 4)));
    log.info(
        s.numComponents(
            ListNode.from(11, 13, 6, 4, 3, 18, 14, 19, 8, 10, 1, 0, 16, 7, 9, 2, 12, 5, 15, 17),
            Arrays.from(10, 2, 9, 5, 3, 13, 19, 17, 16, 8, 18, 7, 1)));
  }
}
