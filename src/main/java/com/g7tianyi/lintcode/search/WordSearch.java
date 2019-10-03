package com.g7tianyi.lintcode.search;

import com.g7tianyi.util.Logger;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.List;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Oct 03, 2019
 *
 * @link https://www.lintcode.com/problem/word-search/description
 */
public class WordSearch {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public boolean exist(char[][] board, String word) {

      if (board == null || board.length == 0 || board[0].length == 0 || word == null) {
        return false;
      }

      return new Resolver(board, word.toCharArray()).resolve();
    }

    private class Resolver {

      private char[] word;
      private char[][] board;
      private boolean[][] visit; // 每个单元中的字母最多只能使用一次

      public Resolver(char[][] board, char[] word) {
        this.word = word;
        this.board = board;
        this.visit = new boolean[board.length][board[0].length];
      }

      public boolean resolve() {
        for (int i = 0; i < board.length; ++i) {
          for (int j = 0; j < board[i].length; ++j) {
            if (board[i][j] == word[0] && search(1, i, j)) {
              return true;
            }
          }
        }
        return false;
      }

      private boolean search(int index, int x, int y) {
        if (index == word.length) {
          return true;
        }

        visit[x][y] = true;

        char ch = word[index];
        if (x > 0 && !visit[x - 1][y] && board[x - 1][y] == ch && search(index + 1, x - 1, y)) {
          return true;
        }

        if (x < board.length - 1
            && !visit[x + 1][y]
            && board[x + 1][y] == ch
            && search(index + 1, x + 1, y)) {
          return true;
        }

        if (y > 0 && !visit[x][y - 1] && board[x][y - 1] == ch && search(index + 1, x, y - 1)) {
          return true;
        }

        if (y < board[0].length - 1
            && !visit[x][y + 1]
            && board[x][y + 1] == ch
            && search(index + 1, x, y + 1)) {
          return true;
        }

        visit[x][y] = false;

        return false;
      }
    }
  }

  private final Solution s = new Solution();

  @AllArgsConstructor
  private class Case {
    private List<String> words;
    private String word;
  }

  private final Consumer<Case> c =
      aCase -> {
        char[][] board = new char[aCase.words.size()][];
        int i = 0;
        for (String s : aCase.words) {
          board[i++] = s.toCharArray();
        }
        log.info(s.exist(board, aCase.word));
      };

  @Test
  public void test() {
    c.accept(new Case(Lists.newArrayList("ABCE", "SFCS", "ADEE"), "ABCCED"));
  }
}
