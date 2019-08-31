package com.g7tianyi.lintcode.dp;

import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Sep 1, 2019
 *
 * @link https://www.lintcode.com/problem/fibonacci/description
 */
public class Fibonacci {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int fibonacci(int n) {
      if (n == 1) {
        return 0;
      }
      if (n == 2) {
        return 1;
      }

      n -= 2;
      int f1 = 0, f2 = 1, ft;
      while (n-- > 0) {
        ft = f1;
        f1 = f2;
        f2 += ft;
      }
      return f2;
    }
  }

  @Test
  public void test() {
    Solution s = new Solution();
    for (int i = 0; i < 20; ++i) {
      log.info(s.fibonacci(i + 1));
    }
  }
}
