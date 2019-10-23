package com.g7tianyi.lintcode.hashmap;

import com.g7tianyi.common.ListNode;
import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Oct 23, 2019
 *
 * @link https://www.lintcode.com/problem/rehashing/description
 */
public class Rehashing {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public ListNode[] rehashing(ListNode[] hashTable) {

      int capacity = hashTable.length * 2;
      ListNode[] result = new ListNode[capacity];

      for (ListNode node : hashTable) {
        while (node != null) {
          int pos = (node.val % capacity + capacity) % capacity;
          ListNode next = new ListNode(node.val);
          if (result[pos] == null) {
            result[pos] = next;
          } else {
            ListNode curr = result[pos];
            while (curr.next != null) {
              curr = curr.next;
            }
            curr.next = next;
          }

          node = node.next;
        }
      }

      return result;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {}
}
