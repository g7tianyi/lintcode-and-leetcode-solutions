package com.g7tianyi.lintcode.queue;

import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Oct 02, 2019
 *
 * @link https://www.lintcode.com/problem/implement-queue-by-circular-array/description
 */
public class ImplementQueueByCircularArray {

  private static final Logger log = Logger.getInstance();

  public class CircularQueue {

    private int[] values;
    private int start;
    private int next;
    private int size;

    public CircularQueue(int n) {
      this.values = new int[n];
      this.start = 0;
      this.next = 0;
      this.size = 0;
    }

    public boolean isFull() {
      return size == values.length;
    }

    public boolean isEmpty() {
      return size == 0;
    }

    public void enqueue(int element) {
      ++size;
      values[next] = element;
      next = (next + 1) % values.length;
    }

    public int dequeue() {
      --size;
      int value = values[start];
      start = (start + 1) % values.length;
      return value;
    }
  }

  @Test
  public void test0() {
    CircularQueue circularQueue = new CircularQueue(5);
    log.info(circularQueue.isFull());
    log.info(circularQueue.isEmpty());
    circularQueue.enqueue(1);
    circularQueue.enqueue(2);
    log.info(circularQueue.dequeue());
  }

  @Test
  public void test1() {
    CircularQueue circularQueue = new CircularQueue(5);
    log.info(circularQueue.isFull());
    log.info(circularQueue.isEmpty());
    circularQueue.enqueue(1);
    circularQueue.enqueue(2);
    log.info(circularQueue.dequeue());
    log.info(circularQueue.dequeue());
    circularQueue.enqueue(1);
    circularQueue.enqueue(2);
    circularQueue.enqueue(3);
    circularQueue.enqueue(4);
    circularQueue.enqueue(5);
    log.info(circularQueue.isFull());
    log.info(circularQueue.dequeue());
    log.info(circularQueue.dequeue());
    log.info(circularQueue.dequeue());
    log.info(circularQueue.dequeue());
    log.info(circularQueue.dequeue());
  }
}
