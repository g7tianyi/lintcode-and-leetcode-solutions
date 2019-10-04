package com.g7tianyi.lintcode.dp;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Oct 04, 2019
 *
 * @link https://www.lintcode.com/problem/stone-game/description
 */
public class StoneGame {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int stoneGame(int[] stones) {

      if (stones == null || stones.length == 0) {
        return 0;
      }

      // G[i,j]表示第i堆石头到第j堆石头的总重量
      int[][] G = new int[stones.length][stones.length];
      for (int i = 0; i < stones.length; ++i) {
        for (int j = i; j < stones.length; ++j) {
          G[i][j] = (j > 0 ? G[i][j - 1] : 0) + stones[j];
        }
      }

      // F[i,j]表示合并从第i堆石头到第j堆石头的最小的开销
      // F[i,j] = MIN(F[i][k] + F[k + 1][j] + G[i][k] + G[k + 1][j] | i <= k <= j)
      int[][] F = new int[stones.length][stones.length];
      for (int l = 2; l <= stones.length; ++l) {
        for (int i = 0, j; i + l <= stones.length; ++i) {
          j = i + l - 1;
          F[i][j] = Integer.MAX_VALUE;
          for (int k = i; k < j; ++k) {
            F[i][j] = Math.min(F[i][j], F[i][k] + F[k + 1][j] + G[i][k] + G[k + 1][j]);
          }
        }
      }
      return F[0][stones.length - 1];
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.stoneGame(Arrays.from(3, 4, 3)));
    log.info(s.stoneGame(Arrays.from(4, 1, 1, 4)));
  }
}
