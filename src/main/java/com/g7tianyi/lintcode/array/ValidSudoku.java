package com.g7tianyi.lintcode.array;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 30, 2019
 *
 * @link https://www.lintcode.com/problem/valid-sudoku/description
 */
public class ValidSudoku {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public boolean isValidSudoku(char[][] board) {

      int[] pos = new int[9];

      // valid row
      for (char[] aBoard : board) {
        reset(pos);
        for (int j = 0, k; j < 9; ++j) {
          k = aBoard[j] - '1';
          if (k >= 0 && k < 9) {
            ++pos[k];
            if (pos[k] > 1) {
              return false;
            }
          }
        }
      }

      // valid column
      for (int i = 0; i < 9; ++i) {
        reset(pos);
        for (int j = 0, k; j < 9; ++j) {
          k = board[j][i] - '1';
          if (k >= 0 && k < 9) {
            ++pos[k];
            if (pos[k] > 1) {
              return false;
            }
          }
        }
      }

      // valid small sudoku-cell
      for (int c = 0; c < 9; ++c) {

        reset(pos);

        int si = c / 3 * 3, sj = c % 3 * 3;
        for (int i = si; i < si + 3; ++i) {
          for (int j = sj, k; j < sj + 3; ++j) {
            k = board[i][j] - '1';
            if (k >= 0 && k < 9) {
              ++pos[k];
              if (pos[k] > 1) {
                return false;
              }
            }
          }
        }
      }

      return true;
    }

    private void reset(int[] pos) {
      for (int i = 0; i < pos.length; ++i) {
        pos[i] = 0;
      }
    }
  }

  @AllArgsConstructor
  public static class Case {

    private char[][] board;

    private boolean result;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> runner =
        aCase -> Assert.assertEquals(s.isValidSudoku(aCase.board), aCase.result);

    runner.accept(
        new Case(
            new char[][] {
              {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
              {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
              {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
              {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
              {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
              {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
              {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
              {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
              {'.', '.', '.', '.', '8', '.', '.', '7', '9'},
            },
            true));

    runner.accept(
        new Case(
            new char[][] {
              {'5', '3', '.', '.', '7', '5', '.', '.', '.'},
              {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
              {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
              {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
              {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
              {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
              {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
              {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
              {'.', '.', '.', '.', '8', '.', '.', '7', '9'},
            },
            false));

    runner.accept(
        new Case(
            new char[][] {
              {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
              {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
              {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
              {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
              {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
              {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
              {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
              {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
              {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
            },
            true));
  }
}
