package com.g7tianyi.lintcode.math.fibonacci;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 03, 2019
 *
 * @link https://www.lintcode.com/problem/fibonacci-easy/description
 */
public class FibonacciEasy {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int fibonacci(int n) {

      if (n == 1) {
        return 0;
      }
      if (n == 2) {
        return 1;
      }

      int a = 0, b = 1, c, i = 2;
      while (i != n) {
        c = a + b;
        a = b;
        b = c;
        ++i;
      }
      return b;
    }
  }
  
   @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Integer> c = num -> log.info("%d: %d", num, s.fibonacci(num));

    c.accept(1);
    c.accept(2);
    c.accept(10);
  }
}
