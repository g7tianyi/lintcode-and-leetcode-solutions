package com.g7tianyi.lintcode.greedy;

import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Oct 21, 2019
 *
 * @link https://www.lintcode.com/problem/battleships-in-a-board/description
 */
public class BattleshipsInBoard {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    // 基于Swipe line的贪心策略
    public int countBattleships(char[][] board) {

      int H = board.length, W = board[0].length;
      int result = 0;
      for (int i = 0, j; i < H; ++i) {
        j = 0;
        while (j < W) { // 向右边扫描
          while (j < W && board[i][j] == '.') {
            ++j;
          }
          if (j == W) {
            break;
          }

          // 如果上一个是X，说明是下面的情况
          // ? ? . X . ? ?
          // ? ? . X . ? ?
          //       |
          //      这里
          if (i > 0 && board[i - 1][j] == 'X') {
            ++j;
            continue;
          }

          while (j < W && board[i][j] == 'X') {
            ++j;
          }
          ++result;
        }
      }

      return result;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(
        s.countBattleships(
            new char[][] {
              {'X', '.', '.', 'X'},
              {'.', '.', '.', 'X'},
              {'.', '.', '.', 'X'},
              {'.', '.', '.', 'X'},
            }));

    log.info(
        s.countBattleships(
            new char[][] {
              {'X', '.', 'X', 'X'},
              {'.', 'X', '.', '.'},
              {'.', '.', '.', 'X'},
              {'.', 'X', '.', 'X'},
            }));
    log.info(
        s.countBattleships(
            new char[][] {
                {'X', '.', 'X', '.', 'X'},
                {'.', '.', 'X', '.', 'X'},
                {'X', 'X', '.', '.', 'X'},
                {'.', '.', 'X', 'X', '.'},
            }));
  }
}
