package com.g7tianyi.lintcode.stack;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.Stack;

/**
 * Created by g7tianyi on Oct 02, 2019
 *
 * @link https://www.lintcode.com/problem/implement-queue-by-two-stacks/description
 */
public class ImplementQueueByTwoStacks {

  private static final Logger log = Logger.getInstance();

  public class MyQueue {

    private Stack<Integer> s1 = new Stack<>();
    private Stack<Integer> s2 = new Stack<>();

    public MyQueue() {}

    public void push(int element) {
      if (s1.isEmpty()) {
        rotateStack(s2, s1);
      }
      s1.push(element);
    }

    public int pop() {
      if (s2.isEmpty()) {
        rotateStack(s1, s2);
      }
      return s2.pop();
    }

    public int top() {
      if (s2.isEmpty()) {
        rotateStack(s1, s2);
      }
      return s2.peek();
    }

    private void rotateStack(Stack<Integer> from, Stack<Integer> to) {
      while (!from.isEmpty()) {
        to.push(from.pop());
      }
    }
  }

  public class MyQueue1 {

    private Stack<Integer> s1 = new Stack<>();
    private Stack<Integer> s2 = new Stack<>();

    public MyQueue1() {}

    public void push(int element) {
      s1.push(element);
      int count = s2.size();
      while (!s2.isEmpty()) {
        s1.push(s2.pop());
      }

      s2.push(element);
      while (count > 0) {
        s2.push(s1.pop());
        --count;
      }
    }

    public int pop() {
      int num = s2.pop();
      int count = s2.size();

      while (!s1.isEmpty()) {
        s2.push(s1.pop());
      }

      s2.pop();
      while (count > 0) {
        s1.push(s2.pop());
        --count;
      }

      return num;
    }

    public int top() {
      return s2.peek();
    }
  }

  @Test
  public void test0() {
    MyQueue myQueue = new MyQueue();
    myQueue.push(1);
    myQueue.push(2);
    log.info(myQueue.pop());
    myQueue.push(3);
    myQueue.push(4);
    myQueue.push(5);
    log.info(myQueue.pop());
    log.info(myQueue.pop());
    log.info(myQueue.pop());
  }

  @Test
  public void test1() {
    MyQueue myQueue = new MyQueue();
    myQueue.push(1);
    myQueue.push(2);
    myQueue.push(3);
    myQueue.push(4);
    myQueue.push(5);
    myQueue.push(6);
    myQueue.push(7);
    myQueue.push(8);
    myQueue.push(9);

    log.info(myQueue.pop());
    log.info(myQueue.pop());
    log.info(myQueue.pop());
    log.info(myQueue.pop());
    log.info(myQueue.pop());
    log.info(myQueue.pop());
    log.info(myQueue.pop());
    log.info(myQueue.pop());
    log.info(myQueue.pop());
  }
}
