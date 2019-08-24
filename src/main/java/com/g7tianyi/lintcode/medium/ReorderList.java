package com.g7tianyi.lintcode.medium;

import com.g7tianyi.lintcode.common.ListNode;
import com.g7tianyi.lintcode.util.Log;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 23, 2019
 *
 * <p>Problem link: https://www.lintcode.com/problem/reorder-list/description
 */
public class ReorderList {

  private static final Log log = new Log();

  public class Solution {

    public void reorderList(ListNode head) {
      if (head == null) {
        return;
      }

      int len = getListLength(head);
      if (len < 3) {
        return;
      }

      List<ListNode> nodes = new ArrayList<>();
      ListNode curr = head;
      ListNode rail;
      int index = 1;
      while (true) {

        // 1 2 3 4 5 6 7 8
        //       |
        if (len % 2 == 0 && index == len / 2) {
          rail = curr.next.next;
          curr.next.next = null;
          break;
        }

        // 1 2 3 4 5 6 7
        //       |
        if (len % 2 == 1 && index == len / 2 + 1) {
          rail = curr.next;
          curr.next = null;
          break;
        }

        index++;
        nodes.add(curr);
        curr = curr.next;
      }

      index = nodes.size() - 1;
      while (index >= 0) {
        ListNode node = nodes.get(index);
        ListNode next = rail.next; // 后半部指定往后移动
        node.next = rail;
        rail.next = curr;
        curr = node;
        rail = next;
        index--; // 相当于前半部分指针往前移动
      }
    }

    private int getListLength(ListNode head) {
      ListNode curr = head;
      int len = 1;
      while (curr.next != null) {
        ++len;
        curr = curr.next;
      }
      return len;
    }
  }

  // Exception in thread "main" java.lang.StackOverflowError
  // 不能递归，也是恶心
  public class SolutionRuntimeError {

    public void reorderList(ListNode head) {
      if (head == null) {
        return;
      }

      ListNode curr = head;
      int len = 1;
      while (curr.next != null) {
        ++len;
        curr = curr.next;
      }
      if (len < 3) {
        return;
      }

      reorder(head, 1, len);
    }

    private NodeInfo reorder(ListNode node, int index, int len) {

      // 1 2 3 4 5 6 7 8
      //       |
      if (len % 2 == 0 && index == len / 2) {
        ListNode latter = node.next.next;
        node.next.next = null;

        NodeInfo nodeInfo = new NodeInfo();
        nodeInfo.latter = latter;
        nodeInfo.sublist = node;
        return nodeInfo;
      }

      // 1 2 3 4 5 6 7
      //       |
      if (len % 2 == 1 && index == len / 2 + 1) {
        ListNode latter = node.next;
        node.next = null;

        NodeInfo nodeInfo = new NodeInfo();
        nodeInfo.latter = latter;
        nodeInfo.sublist = node;
        return nodeInfo;
      }

      NodeInfo nodeInfo = reorder(node.next, index + 1, len);

      ListNode latter = nodeInfo.latter.next;
      node.next = nodeInfo.latter;
      node.next.next = nodeInfo.sublist;

      nodeInfo.latter = latter;
      nodeInfo.sublist = node;

      return nodeInfo;
    }

    private final class NodeInfo {
      private ListNode latter;
      private ListNode sublist;
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<ListNode> runner =
        listNode -> {
          ListNode.print(listNode);
          s.reorderList(listNode);
          ListNode.print(listNode);
          log.info("");
        };

    runner.accept(null);
    runner.accept(ListNode.makeList(1));
    runner.accept(ListNode.makeList(2));
    runner.accept(ListNode.makeList(3));
    runner.accept(ListNode.makeList(4));
    runner.accept(ListNode.makeList(5));
    runner.accept(ListNode.makeList(7));
    runner.accept(ListNode.makeList(8));
  }
}
