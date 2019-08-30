package com.g7tianyi.lintcode.search;

import com.g7tianyi.common.Strings;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 30, 2019
 *
 * @link https://www.lintcode.com/problem/letter-case-permutation/description
 */
public class LetterCasePermutation {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public List<String> letterCasePermutation(String s) {
      if (s == null) {
        return null;
      }

      List<String> result = new ArrayList<>();
      char[] source = s.toCharArray();
      char[] output = new char[source.length];
      dfs(source, 0, output, result);
      return result;
    }

    private void dfs(char[] source, int pos, char[] output, List<String> result) {
      if (pos == source.length) {
        result.add(new String(output));
        return;
      }

      char ch = source[pos];
      if (ch >= 'a' && ch <= 'z') {
        output[pos] = ch;
        dfs(source, pos + 1, output, result);

        output[pos] = Character.toUpperCase(ch);
        dfs(source, pos + 1, output, result);
      } else if (ch >= 'A' && ch <= 'Z') {
        output[pos] = ch;
        dfs(source, pos + 1, output, result);

        output[pos] = Character.toLowerCase(ch);
        dfs(source, pos + 1, output, result);
      } else {
        output[pos] = ch;
        dfs(source, pos + 1, output, result);
      }
    }
  }

  @AllArgsConstructor
  public static class Case {

    private String s;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c =
        aCase -> {
          log.info(aCase.s);

          List<String> result = s.letterCasePermutation(aCase.s);
          log.info("%d %s", result.size(), Strings.format(result));

          log.info();
        };

    c.accept(new Case(""));
    c.accept(new Case("a1b2"));
    c.accept(new Case("3z4"));
    c.accept(new Case("12345"));
  }
}
