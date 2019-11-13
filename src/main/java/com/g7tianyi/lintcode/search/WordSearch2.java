package com.g7tianyi.lintcode.search;

import com.g7tianyi.util.Logger;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by g7tianyi on Nov 13, 2019
 *
 * @link https://www.lintcode.com/problem/word-search-ii/description
 */
public class WordSearch2 {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public List<String> wordSearchII(char[][] board, List<String> words) {
      List<String> result = new ArrayList<>();
      for (String w : words) {
        if (search(board, w)) {
          result.add(w);
        }
      }
      return result;
    }

    private boolean search(char[][] board, String word) {

      int i, j;
      for (i = 0; i < board.length; ++i) {
        for (j = 0; j < board[i].length; ++j) {
          if (board[i][j] == word.charAt(0)) {
            boolean[][] visit = new boolean[board.length][board[0].length];
            if (search(i, j, board, visit, word, 1)) {
              return true;
            }
          }
        }
      }

      return false;
    }

    private boolean search(int i, int j, char[][] board, boolean[][] visit, String word, int pos) {
      if (pos == word.length()) {
        return true;
      }

      visit[i][j] = true;

      if (i + 1 < visit.length
          && !visit[i + 1][j]
          && board[i + 1][j] == word.charAt(pos)
          && search(i + 1, j, board, visit, word, pos + 1)) {
        return true;
      }

      if (i > 0
          && !visit[i - 1][j]
          && board[i - 1][j] == word.charAt(pos)
          && search(i - 1, j, board, visit, word, pos + 1)) {
        return true;
      }

      if (j + 1 < visit[i].length
          && !visit[i][j + 1]
          && board[i][j + 1] == word.charAt(pos)
          && search(i, j + 1, board, visit, word, pos + 1)) {
        return true;
      }

      if (j > 0
          && !visit[i][j - 1]
          && board[i][j - 1] == word.charAt(pos)
          && search(i, j - 1, board, visit, word, pos + 1)) {
        return true;
      }

      visit[i][j] = false;
      return false;
    }
  }

  public class MLE_Solution {

    public List<String> wordSearchII(char[][] board, List<String> words) {
      return new Resolver().resolve(board, words);
    }

    private class Resolver {
      char[][] board;
      boolean[][] visit;
      int maxLength;
      Map<String, Boolean> words;

      public List<String> resolve(char[][] board, List<String> words) {
        this.board = board;
        this.visit = new boolean[board.length][board[0].length];

        this.maxLength = 0;
        this.words = new HashMap<>();
        for (String word : words) {
          this.maxLength = Math.max(this.maxLength, word.length());
          this.words.put(word, false);
        }

        for (int i = 0; i < this.board.length; ++i) {
          for (int j = 0; j < this.board[i].length; ++j) {
            search(i, j, new StringBuilder());
          }
        }

        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Boolean> entry : this.words.entrySet()) {
          if (entry.getValue()) {
            result.add(entry.getKey());
          }
        }
        return result;
      }

      private void search(int i, int j, StringBuilder sb) {
        visit[i][j] = true;
        sb.append(board[i][j]);
        String w = sb.toString();
        if (this.words.containsKey(w)) {
          this.words.put(w, true);
        }

        if (w.length() < maxLength) {
          if (i + 1 < board.length && !visit[i + 1][j]) {
            search(i + 1, j, sb);
          }
          if (i > 0 && !visit[i - 1][j]) {
            search(i - 1, j, sb);
          }
          if (j + 1 < board[i].length && !visit[i][j + 1]) {
            search(i, j + 1, sb);
          }
          if (j > 0 && !visit[i][j - 1]) {
            search(i, j - 1, sb);
          }
        }
        sb.deleteCharAt(sb.length() - 1);
        visit[i][j] = false;
      }
    }
  }

  @Test
  public void test() {
    log.info(
        new Solution()
            .wordSearchII(
                new char[][] {"doaf".toCharArray(), "agai".toCharArray(), "dcan".toCharArray()},
                Lists.newArrayList("dog", "dad", "dgdg", "can", "again")));

    log.info(
        new Solution()
            .wordSearchII(
                new char[][] {"abce".toCharArray(), "sfcs".toCharArray(), "adee".toCharArray()},
                Lists.newArrayList("see", "se")));
  }
}
