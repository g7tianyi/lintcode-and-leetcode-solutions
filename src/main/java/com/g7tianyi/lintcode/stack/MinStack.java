package com.g7tianyi.lintcode.stack;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.LinkedList;

/**
 * Created by g7tianyi on Sep 19, 2019
 *
 * @link https://www.lintcode.com/problem/min-stack/description
 */
public class MinStack {

  private static final Logger log = Logger.getInstance();

  private final LinkedList<Integer> vals;
  private final LinkedList<Integer> mins;

  public MinStack() {
    this.vals = new LinkedList<>();
    this.mins = new LinkedList<>();
  }

  public void push(int num) {
    this.vals.add(num);
    if (this.mins.isEmpty() || this.mins.getLast() > num) {
      this.mins.add(num);
    } else {
      this.mins.add(this.mins.getLast());
    }
  }

  public int pop() {
    this.mins.removeLast();
    return this.vals.removeLast();
  }

  public int min() {
    return this.mins.getLast();
  }

  @Test
  public void test() {

    MinStack minStack = new MinStack();
    minStack.push(3);
    log.info(minStack.min());
    minStack.push(1);
    log.info(minStack.min());
    minStack.push(2);
    log.info(minStack.min());
    minStack.pop();
    log.info(minStack.min());
    minStack.pop();
    log.info(minStack.min());
  }
}
