package com.g7tianyi.lintcode.list;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.common.DoublyListNode;
import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by g7tianyi on Sep 30, 2019
 *
 * @link https://www.lintcode.com/problem/connected-components-in-list/description
 */
public class ConnectedComponentsInList {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int blockNumber(DoublyListNode head, int[] nodes) {

      if (head == null) {
        return 0;
      }

      Set<Integer> set = new HashSet<>();
      for (int node : nodes) {
        set.add(node);
      }

      int result = 1;
      DoublyListNode prev = head, curr = head.next;
      while (curr != null) {
        if (set.contains(prev.val) && !set.contains(curr.val)) {
          ++result;
        }
        prev = curr;
        curr = curr.next;
      }
      return result;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.blockNumber(null, Arrays.from(1)));
  }
}
