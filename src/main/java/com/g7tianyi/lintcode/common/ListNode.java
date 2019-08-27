package com.g7tianyi.lintcode.common;

import java.util.Arrays;

import static com.g7tianyi.lintcode.common.Numbers.nextInt;

/** Created by g7tianyi on Aug 24, 2019 */
public class ListNode {

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
      elems[i] = nextInt(len + 1);
    }
    return makeListFrom(elems);
  }

  public static ListNode makeRandomSortedList(int len) {
    int[] elems = new int[len];
    for (int i = 0; i < len; i++) {
      elems[i] = nextInt(len + 1);
    }
    Arrays.sort(elems);
    return makeListFrom(elems);
  }

  public static ListNode makeRandomSortedList(int len, int min) {
    int[] elems = new int[len];
    for (int i = 0; i < len; i++) {
      elems[i] = nextInt(len + 1) + min;
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
}
