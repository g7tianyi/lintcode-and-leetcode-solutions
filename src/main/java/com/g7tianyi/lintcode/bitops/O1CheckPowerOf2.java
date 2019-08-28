package com.g7tianyi.lintcode.bitops;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 25, 2019
 *
 * @link https://www.lintcode.com/problem/o1-check-power-of-2/description
 */
public class O1CheckPowerOf2 {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public boolean checkPowerOf2(int num) {
      return num > 0 && (num & (num - 1)) == 0;
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Integer> c = num -> log.info("%d => %s", num, s.checkPowerOf2(num));

    c.accept(4);
    c.accept(5);
    c.accept(64);
    c.accept(0);
  }
}
