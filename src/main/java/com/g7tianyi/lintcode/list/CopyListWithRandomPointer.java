package com.g7tianyi.lintcode.list;

import com.g7tianyi.common.RandomListNode;
import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Sep 29, 2019
 *
 * @link https://www.lintcode.com/problem/copy-list-with-random-pointer/description
 */
public class CopyListWithRandomPointer {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public RandomListNode copyRandomList(RandomListNode head) {
      if (head == null) {
        return null;
      }

      RandomListNode newHead = null;

      RandomListNode curr = head;
      // 1 -> 7 -> 9 -> 2
      // 1 -> 1' -> 7 -> 7' -> 9 -> 9' -> 2 -> 2'
      while (curr != null) {
        RandomListNode node = new RandomListNode(curr.label);
        RandomListNode next = curr.next;
        curr.next = node;
        node.next = next;
        curr = next;
        if (newHead == null) {
          newHead = node;
        }
      }

      curr = head;
      while (curr != null) {
        RandomListNode node = curr.next;
        if (curr.random != null) {
          node.random = curr.random.next;
        }
        curr = curr.next.next;
      }

      RandomListNode curr1 = head, curr2 = newHead;
      while (curr2.next != null) {

        RandomListNode next1 = curr1.next.next;
        RandomListNode next2 = curr2.next.next;

        curr1.next = next1;
        curr2.next = next2;

        curr1 = next1;
        curr2 = next2;
      }

      return newHead;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {}
}
