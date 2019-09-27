package com.g7tianyi.lintcode.stack;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.LinkedList;

/**
 * Created by g7tianyi on Sep 27, 2019
 *
 * @link https://www.lintcode.com/problem/max-stack/description
 */
public class MaxStackProblem {

  private static final Logger log = Logger.getInstance();

  public class MaxStack {

    class Node {
      private int value;
      private boolean popped;

      public Node(int value) {
        this.value = value;
        this.popped = false;
      }

      @Override
      public String toString() {
        return String.valueOf(value);
      }
    }

    private LinkedList<Node> stack;

    private LinkedList<Node> max;

    public MaxStack() {
      this.stack = new LinkedList<>();
      this.max = new LinkedList<>();
    }

    public void push(int x) {
      Node next = new Node(x);
      stack.addFirst(next);

      int pos = 0;
      for (Node curr : max) {
        if (curr.value > next.value) {
          ++pos;
        } else {
          break;
        }
      }
      max.add(pos, next);
    }

    public int pop() {
      Node node = pop(stack);
      node.popped = true;
      return node.value;
    }

    public int top() {
      Node node = pop(stack);
      stack.addFirst(node);
      return node.value;
    }

    public int peekMax() {
      Node node = pop(max);
      max.addFirst(node);
      return node.value;
    }

    public int popMax() {
      Node node = pop(max);
      node.popped = true;
      return node.value;
    }

    private Node pop(LinkedList<Node> list) {
      Node node;
      do {
        node = list.removeFirst();
      } while (node.popped);
      return node;
    }
  }

  @Test
  public void test() {

    MaxStack maxStack = new MaxStack();
    maxStack.push(5);
    maxStack.push(1);
    maxStack.push(5);
    log.info(maxStack.top()); // 5
    log.info(maxStack.popMax()); // 5
    log.info(maxStack.top()); // 1
    log.info(maxStack.peekMax()); // 5
    log.info(maxStack.pop()); // 1
    log.info(maxStack.top()); // 5
  }
}
