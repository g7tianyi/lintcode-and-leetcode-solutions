package com.g7tianyi.lintcode.search;

import com.g7tianyi.util.Logger;
import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 22, 2019
 *
 * @link https://www.lintcode.com/problem/word-break-ii/description
 */
public class WordBreak2 {

  private static final Logger log = Logger.getInstance();

  public static class Solution {

    // 如果DP的话：
    // F(i)表示，给从0到i的子串添加space的所有可能的方案，从0到i是前闭后开的区间
    // 例如：s = abc, words = [ a, b, c, ab, ab, ac ]
    // F[1] = [ [a] ]
    // F[2] = [ [a, b], [ab] ]
    // F[3] = [ [a, b, c], [ab, c], [ab, c], [abc] ]
    //
    // 转移方程为：F[i] = [ F[j]与S[j+1, i]的笛卡尔乘积, 1 <= j < i ]
    public List<String> wordBreak(String s, Set<String> words) {
      List<String>[] pos = new ArrayList[s.length() + 1];
      pos[0] = new ArrayList<>();

      for (int i = 0; i < s.length(); i++) {
        if (pos[i] == null) {
          continue;
        }
        for (int j = i + 1; j <= s.length(); j++) {
          String word = s.substring(i, j);
          if (words.contains(word)) {
            if (pos[j] == null) {
              List<String> myWords = new ArrayList<>();
              myWords.add(word);
              pos[j] = myWords;
            } else {
              pos[j].add(word);
            }
          }
        }
      }

      if (pos[s.length()] == null) {
        return new ArrayList<>();
      } else {
        List<String> result = new ArrayList<>();
        dfs(pos, result, "", s.length());
        return result;
      }
    }

    public void dfs(List<String>[] pos, List<String> result, String curr, int i) {
      if (i == 0) {
        result.add(curr.trim());
        return;
      }

      for (String s : pos[i]) {
        dfs(pos, result, s + " " + curr, i - s.length());
      }
    }
  }

  // 还是会超时，本质上还是DFS，虽然有小部分减枝，但扛不住极端情况
  public class Solution_TLE2 {

    public List<String> wordBreak(String s, Set<String> words) {
      ArrayList<String> result = new ArrayList<>();
      dfs(words, s, 0, 1, new LinkedList<>(), result);
      return result;
    }

    public void dfs(
        Set<String> words,
        String s,
        int start,
        int end,
        LinkedList<String> splits,
        ArrayList<String> result) {

      if (end > s.length()) {

        if (splits.isEmpty()) {
          return;
        }

        StringBuilder sb = new StringBuilder();
        for (String split : splits) {
          sb.append(split).append(' ');
        }
        if (sb.length() > 0) {
          sb.deleteCharAt(sb.length() - 1);
        }
        result.add(sb.toString());

      } else {

        for (int i = end; i <= s.length(); ++i) {
          String word = s.substring(start, i);
          if (words.contains(word)) {
            splits.addLast(word);
            dfs(words, s, i, i + 1, splits, result);
            splits.removeLast();
          }
        }
      }
    }
  }

  public class Solution_TLE {

    public List<String> wordBreak(String s, Set<String> words) {

      List<String> result = new ArrayList<>();

      if (s.length() == 0) {
        return result;
      }

      // F(i, j)表示s中从i到j的子字符串是否可以构成一个单词
      boolean[][] positions = getSplitMatrix(s, words);

      char[] chars = s.toCharArray();
      dfs(chars, positions, 0, new HashSet<>(), result);

      return result;
    }

    private boolean[][] getSplitMatrix(String s, Set<String> words) {

      int len = s.length();

      boolean[][] F = new boolean[len][len];
      for (int i = 0; i < len; ++i) {
        F[i] = new boolean[len];
        for (int j = 0; j < len; ++j) {
          F[i][j] = false;
        }
      }

      for (int i = 0; i < len; ++i) {
        StringBuilder sb = new StringBuilder();
        for (int j = i; j < len; ++j) {
          sb.append(s.charAt(j));
          if (words.contains(sb.toString())) {
            F[i][j] = true;
          }
        }
      }

      return F;
    }

    private void dfs(
        char[] chars,
        boolean[][] positions,
        int start,
        Set<Integer> spacePositions,
        List<String> result) {

      if (start == chars.length) {
        if (!spacePositions.isEmpty()) {
          StringBuilder sb = new StringBuilder();
          for (int i = 0; i < chars.length; ++i) {
            sb.append(chars[i]);
            if (spacePositions.contains(i)) {
              sb.append(' ');
            }
          }
          if (sb.charAt(sb.length() - 1) == ' ') {
            sb.deleteCharAt(sb.length() - 1);
          }
          result.add(sb.toString());
        }
        return;
      }

      for (int i = start; i < chars.length; ++i) {
        if (positions[start][i]) {
          spacePositions.add(i);
          dfs(chars, positions, i + 1, spacePositions, result);
          spacePositions.remove(i);
        }
      }
    }
  }

  @AllArgsConstructor
  private class Case {
    private String s;
    private Set<String> words;
    private boolean print;
  }

  private final Solution s = new Solution();

  private final Consumer<Case> c =
      aCase -> {
        log.info("========================================");
        List<String> sentences = s.wordBreak(aCase.s, aCase.words);
        if (aCase.print) {
          for (String sentence : sentences) {
            log.info(sentence);
          }
        }
      };

  @Test
  public void test() {
    c.accept(
        new Case("lintcode", Sets.newHashSet("de", "ding", "co", "code", "lint", "cod"), true));
    c.accept(
        new Case(
            "lintcode", Sets.newHashSet("de", "ding", "co", "code", "lint", "cod", "e"), true));
    c.accept(new Case("abc", Sets.newHashSet("a", "b", "c", "ab", "ac", "bc", "abc"), true));
  }

  @Test
  public void testScaryCase() {

    Set<String> words =
        Sets.newHashSet(
            "a",
            "aa",
            "aaa",
            "aaaa",
            "aaaaa",
            "aaaaaa",
            "aaaaaaa",
            "aaaaaaaa",
            "aaaaaaaaa",
            "aaaaaaaaaa");

    c.accept(
        new Case(
            "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
            words,
            false));

    c.accept(new Case("aaaaaaaaaaaaaaaaaaaaaaaa", words, false));

    c.accept(
        new Case(
            "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
            words,
            false));
  }
}
