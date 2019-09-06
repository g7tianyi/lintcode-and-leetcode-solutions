package com.g7tianyi.common;

import java.util.Arrays;

import static com.g7tianyi.common.Numbers.nextInt;

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

  public static ListNode sortedListOf(int len) {
    int[] elems = new int[len];
    for (int i = 0; i < len; i++) {
      elems[i] = i + 1;
    }
    return from(elems);
  }

  public static ListNode randomListOf(int len) {
    return randomListOf(len, len + 1);
  }

  public static ListNode randomListOf(int len, int max) {
    int[] elems = new int[len];
    for (int i = 0; i < len; i++) {
      elems[i] = nextInt(max);
    }
    return from(elems);
  }

  public static ListNode randomSortedListOf(int len) {
    int[] elems = new int[len];
    for (int i = 0; i < len; i++) {
      elems[i] = nextInt(len + 1);
    }
    Arrays.sort(elems);
    return from(elems);
  }

  public static ListNode randomSortedListOf(int len, int min) {
    return randomSortedListOf(len, min, min + len + 1);
  }

  public static ListNode randomSortedListOf(int len, int min, int max) {
    int[] elems = new int[len];
    for (int i = 0; i < len; i++) {
      elems[i] = nextInt(max - min) + min;
    }
    Arrays.sort(elems);
    return from(elems);
  }

  public static ListNode from(int... args) {
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
