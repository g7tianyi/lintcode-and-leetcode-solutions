package com.g7tianyi.lintcode.search;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 27, 2019
 *
 * @link https://www.lintcode.com/problem/n-queens/description
 */
public class NQueens {

  private static final Logger log = Logger.getInstance();

  public static class Solution {

    public List<List<String>> solveNQueens(int n) {

      char[][] board = new char[n][n];
      for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n; ++j) {
          board[i][j] = '.';
        }
      }

      List<List<String>> result = new ArrayList<>();

      Queue<char[][]> myQueue = new LinkedList<>();
      myQueue.offer(board);
      myQueue.offer(null);
      int row = 0;
      while (!myQueue.isEmpty()) {
        board = myQueue.poll();
        if (board == null) {
          ++row;

          if (!myQueue.isEmpty()) {
            myQueue.offer(null);
          }
        } else if (row == n) {
          saveResult(board, result);
        } else {
          for (int i = 0; i < n; ++i) {
            if (board[row][i] == '.') {
              myQueue.offer(markPositions(row, i, board));
            }
          }
        }
      }

      return result;
    }

    private static char[][] markPositions(int x, int y, char[][] board) {

      int i, j, n = board.length;

      char[][] next = new char[n][n];
      for (i = 0; i < n; ++i) {
        for (j = 0; j < n; ++j) {
          next[i][j] = board[i][j];
        }
      }

      for (i = 0; i < n; ++i) {
        if (i != x) {
          next[i][y] = 'X';
        }
        if (i != y) {
          next[x][i] = 'X';
        }
      }

      i = x - 1;
      j = y - 1;
      while (i >= 0 && j >= 0) {
        next[i--][j--] = 'X';
      }

      i = x - 1;
      j = y + 1;
      while (i >= 0 && j < n) {
        next[i--][j++] = 'X';
      }

      i = x + 1;
      j = y - 1;
      while (i < n && j >= 0) {
        next[i++][j--] = 'X';
      }

      i = x + 1;
      j = y + 1;
      while (i < n && j < n) {
        next[i++][j++] = 'X';
      }

      next[x][y] = 'Q';

      return next;
    }

    private static void saveResult(char[][] board, List<List<String>> result) {
      int len = board.length;
      List<String> list = new ArrayList<>();
      for (char[] chars : board) {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < len; ++j) {
          sb.append(chars[j] == 'Q' ? 'Q' : '.');
        }
        list.add(sb.toString());
      }
      result.add(list);
    }
  }

  public static class RecursiveSolution {

    public List<List<String>> solveNQueens(int n) {
      return new Resolver(n).resolve();
    }

    private class Resolver {

      private List<List<String>> result;

      private int[][] board;

      private int n;

      public Resolver(int n) {
        this.n = n;
        this.board = new int[n][n];
        this.result = new ArrayList<>();
      }

      private List<List<String>> resolve() {
        resolve(0);
        return result;
      }

      private void resolve(int row) {
        if (row == n) {
          saveResult();
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

      private void saveResult() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
          StringBuilder sb = new StringBuilder();
          for (int j = 0; j < n; ++j) {
            sb.append(board[i][j] == -1 ? 'Q' : '.');
          }
          list.add(sb.toString());
        }
        result.add(list);
      }
    }
  }

  private final Solution s = new Solution();

  private final Consumer<Integer> c =
      n -> {
        List<List<String>> nQueens = s.solveNQueens(n);
        for (List<String> nQueen : nQueens) {
          for (String row : nQueen) {
            log.info(row);
          }
          log.info();
        }
      };

  @Test
  public void test() {

    c.accept(0);
    c.accept(1);
    c.accept(4);
  }
}
