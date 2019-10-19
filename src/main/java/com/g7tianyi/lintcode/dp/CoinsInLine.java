package com.g7tianyi.lintcode.dp;

import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Oct 19, 2019
 *
 * @link https://www.lintcode.com/problem/coins-in-a-line/description
 */
public class CoinsInLine {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public boolean firstWillWin(int n) {

      if (n <= 0) {
        return false;
      }

      // F(i)表示从位置i开始，是否可以先手毕必胜
      // F(i) = !F(i+1) || !F(i+2)
      // !F(i+1)表示这一轮我拿走第i个硬币，对手从i+1开始取
      // !F(i+2)表示这一轮我拿走第i和第i+1个硬币
      // 因为当前状态仅与之前的的两个状态有关，所以可以优化为常数级的空间
      // 和斐波那契序列是一个意思
      boolean F1 = true, F2 = true;
      for (int i = n - 2; i > 0; --i) {
        boolean F = !F1 || !F2;
        F2 = F1;
        F1 = F;
      }
      return F1;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    for (int i = 1; i < 100; ++i) {
      log.info("%d %s", i, s.firstWillWin(i));
    }
  }
}
