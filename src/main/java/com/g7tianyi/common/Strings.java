package com.g7tianyi.common;

import java.util.List;

/** Created by g7tianyi on Aug 26, 2019 */
public final class Strings {

  private Strings() {}

  public static String prePad(String s, int length, char ch) {
    StringBuilder sb = new StringBuilder(s);
    sb.reverse();

    while (sb.length() < length) {
      sb.append(ch);
    }
    sb.reverse();
    return sb.toString();
  }

  public static String from(char ch, int length) {
    StringBuilder sb = new StringBuilder();
    while (sb.length() < length) {
      sb.append(ch);
    }
    return sb.toString();
  }

  public static String format(ListNode listNode) {
    StringBuilder sb = new StringBuilder("[");
    while (listNode != null) {
      sb.append(listNode.val).append("-");
      listNode = listNode.next;
    }
    if (sb.length() > 1) {
      sb.deleteCharAt(sb.length() - 1);
    }
    sb.append("]");
    return sb.toString();
  }

  public static <T> String format(List<T> list) {
    StringBuilder sb = new StringBuilder("[");
    if (list != null && !list.isEmpty()) {
      list.forEach(elem -> sb.append(elem).append(" "));
    }
    if (sb.length() > 1) {
      sb.deleteCharAt(sb.length() - 1);
    }
    sb.append("]");
    return sb.toString();
  }

  public static String format(int[] array) {
    return format(array, array.length);
  }

  public static String format(int[] array, int len) {
    StringBuilder sb = new StringBuilder("[");
    if (array != null && len != 0) {
      for (int i = 0; i < len; i++) {
        sb.append(array[i]).append(" ");
      }
    }

    if (sb.length() > 1) {
      sb.deleteCharAt(sb.length() - 1);
    }
    sb.append("]");
    return sb.toString();
  }

  public static String format(int[] array, int len, int except) {
    StringBuilder sb = new StringBuilder("[");
    if (array != null && len != 0) {
      for (int i = 0; i < len; i++) {
        if (array[i] != except) {
          sb.append(array[i]).append(" ");
        }
      }
    }

    if (sb.length() > 1) {
      sb.deleteCharAt(sb.length() - 1);
    }
    sb.append("]");
    return sb.toString();
  }

  public static String format(char[] array) {
    return new String(array);
  }
}
