package com.g7tianyi.lintcode.stack;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.Stack;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 03, 2019
 *
 * @link https://www.lintcode.com/problem/mock-hanoi-tower-by-stacks/description
 */
public class MockHanoiTowerByStack {

  private static final Logger log = Logger.getInstance();

  public class Tower {

    private Stack<Integer> disks;

    public Tower(int i) {
      disks = new Stack<>();
    }

    public void add(int d) {
      if (!disks.isEmpty() && disks.peek() <= d) {
        System.out.println("Error placing disk " + d);
      } else {
        disks.push(d);
      }
    }

    public void moveTopTo(Tower t) {
      t.add(disks.pop());
    }

    public void moveDisks(int n, Tower destination, Tower buffer) {
      if (n > 0) {
        moveDisks(n - 1, buffer, destination);
        moveTopTo(destination);
        buffer.moveDisks(n - 1, destination, this);
      }
    }

    public Stack<Integer> getDisks() {
      return disks;
    }
  }
}
