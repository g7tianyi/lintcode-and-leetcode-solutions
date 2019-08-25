package com.g7tianyi.lintcode.util;

import com.g7tianyi.lintcode.common.ListNode;

import java.util.List;

/** Created by g7tianyi on Aug 24, 2019 */
public final class Console {

  private static final Log log = new Log();

  private Console() {}

  public static String stringify(ListNode listNode) {
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

  public static void log(ListNode listNode) {
    log.info(stringify(listNode));
  }

  public static <T> String stringify(List<T> list) {
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

  public static <T> void log(List<T> list) {
    log.info(stringify(list));
  }

  public static String stringify(int[] array) {
    StringBuilder sb = new StringBuilder("[");
    if (array != null && array.length != 0) {
      for (int elem : array) {
        sb.append(elem).append(" ");
      }
    }

    if (sb.length() > 1) {
      sb.deleteCharAt(sb.length() - 1);
    }
    sb.append("]");
    return sb.toString();
  }

  public static void log(int[] array) {
    log.info(stringify(array));
  }
}
