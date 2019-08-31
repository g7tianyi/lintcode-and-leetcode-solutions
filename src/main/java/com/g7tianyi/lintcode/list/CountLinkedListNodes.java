package com.g7tianyi.lintcode.list;

import com.g7tianyi.common.ListNode;
import com.g7tianyi.util.Logger;

/**
 * Created by g7tianyi on Sep 1, 2019
 *
 * @link https://www.lintcode.com/problem/count-linked-list-nodes/description
 */
public class CountLinkedListNodes {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    // 注意，下面并没有考虑环的问题
    public int countNodes(ListNode head) {

      int result = 0;
      if (head == null) {
        return result;
      }

      while (head != null) {
        ++result;
        head = head.next;
      }
      return result;
    }
  }
}
