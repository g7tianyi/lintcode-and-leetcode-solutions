package com.g7tianyi.lintcode.simple;

import com.g7tianyi.lintcode.common.ListNode;
import com.g7tianyi.lintcode.util.Console;
import com.g7tianyi.lintcode.util.Log;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 24, 2019
 *
 * <p>Problem link: https://www.lintcode.com/problem/reverse-order-storage/description
 */
public class ReverseOrderStorage {

  private static final Log log = new Log();

  public class Solution {

    public List<Integer> reverseStore(ListNode head) {
      List<Integer> result = new ArrayList<>();
      if (head != null) {
        resolve(head, result);
      }
      return result;
    }

    private void resolve(ListNode node, List<Integer> result) {
      if (node.next != null) {
        resolve(node.next, result);
      }
      result.add(node.val);
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<ListNode> runner =
        listNode -> {
          // printList(rs.reverse(listNode));
          Console.log(s.reverseStore(listNode));
          log.info("\n");
        };

    runner.accept(null);
    runner.accept(ListNode.makeBeautifulList(1));
    runner.accept(ListNode.makeBeautifulList(8));
  }
}
