package com.g7tianyi.lintcode.list;

import com.g7tianyi.common.ListNode;
import com.g7tianyi.common.Strings;
import com.g7tianyi.util.Logger;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 06, 2019
 *
 * @link https://www.lintcode.com/problem/merge-k-sorted-lists/description
 */
public class MergeKSortedLists {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public ListNode mergeKLists(List<ListNode> lists) {

      PriorityQueue<ListNode> pQueue =
          new PriorityQueue<>(
              new Comparator<ListNode>() {
                @Override
                public int compare(ListNode o1, ListNode o2) {
                  return Integer.compare(o1.val, o2.val);
                }
              });

      for (ListNode listNode : lists) {
        if (listNode != null) {
          pQueue.offer(listNode);
        }
      }

      ListNode head = null, curr = null;
      while (!pQueue.isEmpty()) {
        ListNode listNode = pQueue.poll();
        if (listNode.next != null) {
          pQueue.offer(listNode.next);
        }
        listNode.next = null;

        if (head == null) {
          head = listNode;
          curr = head;
        } else {
          curr.next = listNode;
          curr = curr.next;
        }
      }

      return head;
    }
  }

  @AllArgsConstructor
  private class Case {
    List<ListNode> lists;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c =
        aCase -> {
          for (ListNode listNode : aCase.lists) {
            log.info(Strings.format(listNode));
          }
          log.info(Strings.format(s.mergeKLists(aCase.lists)));
          log.info();
        };

    c.accept(
        new Case(
            Lists.newArrayList(
                ListNode.randomSortedListOf(5, 10, 100),
                ListNode.randomSortedListOf(1, 10, 100),
                ListNode.randomSortedListOf(2, 10, 100),
                ListNode.randomSortedListOf(10, 10, 100),
                ListNode.randomSortedListOf(11, 10, 100))));
  }
}
