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

  public static ListNode makeList(int len) {

    ListNode listNode = new ListNode(1);
    ListNode currNode = listNode;
    for (int i = 2; i <= len; i++) {
      currNode.next = new ListNode(i);
      currNode = currNode.next;
    }
    return listNode;
  }

  public static ListNode makeRandomList(int len) {

    ListNode listNode = new ListNode(randomInt(len + 1));
    ListNode currNode = listNode;
    for (int i = 2; i <= len; i++) {
      currNode.next = new ListNode(randomInt(len + 1));
      currNode = currNode.next;
    }
    return listNode;
  }

  public static ListNode makeListFrom(int ...args) {
    if (args.length == 0) {
      return null;
    }

    ListNode listNode = new ListNode(0);
    ListNode currNode = listNode;
    for (int arg: args) {
      currNode.next = new ListNode(arg);
      currNode = currNode.next;
    }
    return listNode.next;
  }

  private static int randomInt(int max) {
    return (int) (Math.random() * max);
  }
}
