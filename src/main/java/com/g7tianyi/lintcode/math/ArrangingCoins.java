package com.g7tianyi.lintcode.math;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 30, 2019
 *
 * @link https://www.lintcode.com/problem/arranging-coins/description
 */
public class ArrangingCoins {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int arrangeCoins(int n) {
      // k * (k + 1) / 2 <= n
      return (int) Math.floor((Math.sqrt((long) n * 8 + 1) - 1) / 2);
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Integer> c = num -> log.info("%d => %d", num, s.arrangeCoins(num));

    c.accept(0);
    c.accept(1);
    c.accept(3);
    c.accept(5);
    c.accept(8);
    c.accept(10);
    c.accept(846930886);
    c.accept(Integer.MAX_VALUE);
  }
}
