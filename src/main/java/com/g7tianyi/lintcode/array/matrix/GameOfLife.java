package com.g7tianyi.lintcode.array.matrix;

import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Oct 19, 2019
 *
 * @link https://www.lintcode.com/problem/game-of-life/description
 */
public class GameOfLife {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    // 输入是个int类型的矩阵，所以就可以存储除了0和1之外的、其他的数字，大概意思如下：
    // - 初始状态是0，更新后的状态是0，新状态用0表示
    // - 初始状态是0，更新后的状态是1，新状态用-1表示
    // - 初始状态是1，更新后的状态是0，新状态用-2表示
    // - 初始状态是1，更新后的状态是1，新状态用1表示
    public void gameOfLife(int[][] board) {

      if (board == null || board.length == 0 || board[0].length == 0) {
        return;
      }

      int H = board.length, W = board[0].length;
      for (int i = 0; i < H; ++i) {
        for (int j = 0; j < W; ++j) {
          boolean t = i > 0, d = i < H - 1, l = j > 0, r = j < W - 1;
          int surroundings =
              (t ? translate(board[i - 1][j]) : 0)
                  + (d ? translate(board[i + 1][j]) : 0)
                  + (l ? translate(board[i][j - 1]) : 0)
                  + (r ? translate(board[i][j + 1]) : 0)
                  + (t && l ? translate(board[i - 1][j - 1]) : 0)
                  + (t && r ? translate(board[i - 1][j + 1]) : 0)
                  + (d && l ? translate(board[i + 1][j - 1]) : 0)
                  + (d && r ? translate(board[i + 1][j + 1]) : 0);
          if (surroundings < 2 || surroundings > 3) { // 0和-2都表示死亡，
            board[i][j] = board[i][j] == 0 ? 0 : -2; // 0表示死亡到死亡，-2表示活着到死亡
          } else if (surroundings == 3) { //
            board[i][j] = board[i][j] == 0 ? -1 : 1; // -1表示复活(0->1)，1表示继续活着(1->1)
          }
        }
      }

      for (int i = 0; i < H; ++i) {
        for (int j = 0; j < W; ++j) {
          board[i][j] = format(board[i][j]);
        }
      }
    }

    // 还原为先前的值
    private int translate(int value) {
      if (value == 0 || value == 1) {
        return value;
      }
      if (value == -1) {
        return 0;
      }
      return 1;
    }

    // 将临时代表的值变换为新的值
    private int format(int value) {
      if (value == 0 || value == 1) {
        return value;
      }
      if (value == -1) {
        return 1;
      }
      return 0;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    int[][] matrix =
        new int[][] {
          {0, 1, 0},
          {0, 0, 1},
          {1, 1, 1},
          {0, 0, 0},
        };
    s.gameOfLife(matrix);
    for (int[] row : matrix) {
      log.info(row);
    }
  }
}
