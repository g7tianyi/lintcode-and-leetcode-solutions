package com.g7tianyi.lintcode.common;

import com.g7tianyi.lintcode.util.Log;

/** Created by g7tianyi on Aug 24, 2019 */
public class ListNode {

  private static final Log log = new Log();

  public int val;

  public ListNode next;

  public ListNode(int x) {
    val = x;
    next = null;
  }

  @Override
  public String toString() {
    return String.valueOf(val);
  }

  public static ListNode makeList(int len) {

    ListNode listNode = new ListNode(1);
    ListNode currNode = listNode;
    for (int i = 2; i <= len; i++) {
      currNode.next = new ListNode(i);
      currNode = currNode.next;
    }
    return listNode;
  }

  public static void print(ListNode listNode) {
    StringBuilder sb = new StringBuilder("[");
    while (listNode != null) {
      sb.append(listNode.val).append("-");
      listNode = listNode.next;
    }
    if (sb.length() > 1) {
      sb.deleteCharAt(sb.length() - 1);
    }
    sb.append("]");
    log.info(sb.toString());
  }
}
