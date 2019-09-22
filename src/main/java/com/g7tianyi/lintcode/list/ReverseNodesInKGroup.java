package com.g7tianyi.lintcode.list;

import com.g7tianyi.common.ListNode;
import com.g7tianyi.common.Strings;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 21, 2019
 *
 * @link https://www.lintcode.com/problem/reverse-nodes-in-k-group/description
 */
public class ReverseNodesInKGroup {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    // 对于每个分组，都有一个front和rear
    // 分组内原先第一个元素将指向rear，而front将指向原来的最后一个元素
    //
    // F -> 1 -> 2 -> 3 -> R
    //
    //       ______________
    //      |              |
    // F    1 <- 2 <- 3    R
    // |              |
    //  ￣￣￣￣￣￣￣￣
    //
    // 保证代码简洁性与正确性的几个关键点是：
    // 0. 为头指针引入一个哨兵节点
    // 1. prev指针一开始是指向R的
    // 2. front指针更新为新指向R的指针，也就是上图中的1
    public ListNode reverseKGroup(ListNode head, int k) {

      if (k <= 1) {
        return head;
      }

      ListNode sentry = new ListNode(-1);
      sentry.next = head;

      ListNode front = sentry;
      ListNode prev, curr, next;

      int i;
      while (true) {
        i = 0;
        curr = front;
        while (i < k && curr != null) {
          curr = curr.next;
          ++i;
        }
        if (curr == null) {
          break;
        }

        // rear = curr.next;
        // prev = rear;
        prev = curr.next; // 参考上面两行被注释的代码，curr.next相当于当前分组的rear
        curr = front.next;
        // F -> | 1 -> 2 -> 3 | -> R
        // F -> | 3 -> 2 -> 1 | -> R
        for (i = 0; i < k; ++i) {
          next = curr.next;
          curr.next = prev;

          prev = curr;
          curr = next;
        }

        next = front.next;
        front.next = prev;
        front = next;
      }

      return sentry.next;
    }
  }

  @AllArgsConstructor
  private class Case {
    ListNode head;
    int k;
  }

  private final Solution s = new Solution();

  private final Consumer<Case> c =
      aCase -> {
        log.info("%s | %d", Strings.format(aCase.head), aCase.k);
        log.info(Strings.format(s.reverseKGroup(aCase.head, aCase.k)));
      };

  @Test
  public void test() {
    for (int k = 0; k < 7; ++k) {
      c.accept(new Case(ListNode.from(1, 2, 3, 4, 5), k));
    }
    log.info("----------------------------------------");
    for (int k = 0; k < 6; ++k) {
      c.accept(new Case(ListNode.from(1, 2, 3, 4), k));
    }
  }

  @Test
  public void test0() {
    c.accept(new Case(null, 0));
    c.accept(new Case(null, 1));
    c.accept(new Case(null, 2));
    c.accept(new Case(ListNode.from(1), 0));
    c.accept(new Case(ListNode.from(1), 1));
    c.accept(new Case(ListNode.from(1), 2));
  }
}
