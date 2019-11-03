package com.g7tianyi.lintcode.dp;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by g7tianyi on Nov 03, 2019
 *
 * @link https://www.lintcode.com/problem/perfect-squares/description
 */
public class PerfectSquare {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int numSquares(int value) {

      List<Integer> squares = new ArrayList<>();
      for (int i = 1; i * i <= value; ++i) {
        squares.add(i * i);
      }

      // F(i)表示i最少多少个完全平方数的和等于i
      // F(i) = MIN{ F(k) + 1 | 1 <= k < i, 且i-k是个平方数}
      int[] F = new int[value + 1];
      F[1] = 1;

      for (int i = 2; i <= value; ++i) {
        for (int square : squares) {
          if (square > i) {
            break;
          }
          if (F[i] == 0 || F[i] > F[i - square] + 1) {
            F[i] = F[i - square] + 1;
          }
        }
      }
      return F[value];
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.numSquares(12));
    log.info(s.numSquares(13));
    log.info(s.numSquares(16));
  }
}
