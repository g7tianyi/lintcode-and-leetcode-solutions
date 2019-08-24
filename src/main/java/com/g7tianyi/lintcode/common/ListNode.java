package com.g7tianyi.lintcode.common;

import com.g7tianyi.lintcode.util.Log;

import java.util.Arrays;

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

  public static ListNode makeBeautifulList(int len) {
    int[] elems = new int[len];
    for (int i = 0; i < len; i++) {
      elems[i] = i + 1;
    }
    return makeListFrom(elems);
  }

  public static ListNode makeRandomList(int len) {
    int[] elems = new int[len];
    for (int i = 0; i < len; i++) {
      elems[i] = randomInt(len + 1);
    }
    return makeListFrom(elems);
  }

  public static ListNode makeRandomSortedList(int len) {
    int[] elems = new int[len];
    for (int i = 0; i < len; i++) {
      elems[i] = randomInt(len + 1);
    }
    Arrays.sort(elems);
    return makeListFrom(elems);
  }

  public static ListNode makeRandomSortedList(int len, int min) {
    int[] elems = new int[len];
    for (int i = 0; i < len; i++) {
      elems[i] = randomInt(len + 1) + min;
    }
    Arrays.sort(elems);
    return makeListFrom(elems);
  }

  public static ListNode makeListFrom(int... args) {
    if (args.length == 0) {
      return null;
    }

    ListNode listNode = new ListNode(0);
    ListNode currNode = listNode;
    for (int arg : args) {
      currNode.next = new ListNode(arg);
      currNode = currNode.next;
    }
    return listNode.next;
  }

  private static int randomInt(int max) {
    return (int) (Math.random() * max);
  }
}
