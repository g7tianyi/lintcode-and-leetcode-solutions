package com.g7tianyi.lintcode.search;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 27, 2019
 *
 * @link https://www.lintcode.com/problem/n-queens-ii/description
 */
public class NQueens2 {

  private static final Logger log = Logger.getInstance();

  public static class Solution {

    public int totalNQueens(int n) {
      return new Resolver(n).resolve();
    }

    private class Resolver {

      private int result;

      private int[][] board;

      private int n;

      public Resolver(int n) {
        this.n = n;
        this.board = new int[n][n];
      }

      private int resolve() {
        resolve(0);
        return result;
      }

      private void resolve(int row) {
        if (row == n) {
          ++result;
          return;
        }

        for (int j = 0; j < n; ++j) {
          if (board[row][j] == 0) {
            markPositions(row, j, 1);
            resolve(row + 1);
            markPositions(row, j, -1);
          }
        }
      }

      private void markPositions(int x, int y, int delta) {
        int i;
        for (i = 0; i < n; ++i) {
          if (i != x) {
            board[i][y] += delta;
          }
          if (i != y) {
            board[x][i] += delta;
          }
        }

        i = x - 1;
        int j = y - 1;
        while (i >= 0 && j >= 0) {
          board[i--][j--] += delta;
        }

        i = x - 1;
        j = y + 1;
        while (i >= 0 && j < n) {
          board[i--][j++] += delta;
        }

        i = x + 1;
        j = y - 1;
        while (i < n && j >= 0) {
          board[i++][j--] += delta;
        }

        i = x + 1;
        j = y + 1;
        while (i < n && j < n) {
          board[i++][j++] += delta;
        }

        if (delta == 1) {
          board[x][y] = -1;
        } else {
          board[x][y] = 0;
        }
      }
    }
  }

  private final Solution s = new Solution();

  private final Consumer<Integer> c = n -> log.info("%d => %d", n, s.totalNQueens(n));

  @Test
  public void test() {

    c.accept(0);
    c.accept(1);
    c.accept(4);
    c.accept(5);
  }
}
