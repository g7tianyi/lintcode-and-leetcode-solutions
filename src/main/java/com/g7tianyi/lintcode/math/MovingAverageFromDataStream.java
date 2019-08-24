package com.g7tianyi.lintcode.math;

import com.g7tianyi.lintcode.util.Log;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by g7tianyi on Aug 24, 2019
 *
 * <p>Problem link: https://www.lintcode.com/problem/moving-average-from-data-stream/description
 */
public class MovingAverageFromDataStream {

  private static final Log log = new Log();

  public class MovingAverage {

    private Queue<Integer> myQueue = new LinkedList<>();

    private int size;

    private long sum;

    public MovingAverage(int size) {
      this.size = size;
      this.sum = 0;
    }

    public double next(int val) {
      if (myQueue.size() >= size) {
        Integer pop = myQueue.poll();
        if (pop != null) {
          sum -= pop;
        }
      }

      myQueue.offer(val);
      sum += val;

      return (double) sum / myQueue.size();
    }
  }

  @Test
  public void test() {
    MovingAverage ma;

    ma = new MovingAverage(3);
    log.info(ma.next(1));
    log.info(ma.next(10));
    log.info(ma.next(3));
    log.info(ma.next(5));

    ma = new MovingAverage(1);
    log.info(ma.next(1));
    log.info(ma.next(10));
    log.info(ma.next(3));
    log.info(ma.next(5));
  }
}
