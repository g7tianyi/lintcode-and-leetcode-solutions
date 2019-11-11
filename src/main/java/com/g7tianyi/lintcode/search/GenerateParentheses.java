package com.g7tianyi.lintcode.search;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Nov 11, 2019
 *
 * @link https://www.lintcode.com/problem/generate-parentheses/description
 */
public class GenerateParentheses {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public List<String> generateParenthesis(int n) {
      char[] chars = new char[n * 2];
      chars[0] = '(';
      List<String> result = new ArrayList<>();
      search(chars, 1, 1, result);
      return result;
    }

    private void search(char[] chars, int pos, int left, List<String> result) {
      if (pos == chars.length) {
        result.add(new String(chars));
        return;
      }

      int totalLeft = chars.length >> 1;
      int right = pos - left;

      if (left < totalLeft && left >= right) { // 左括号还没消耗完
        chars[pos] = '(';
        search(chars, pos + 1, left + 1, result);
      }
      if (left > right) {
        chars[pos] = ')';
        search(chars, pos + 1, left, result);
      }
    }
  }

  private final Solution s = new Solution();

  private final Consumer<Integer> c =
      num -> {
        List<String> result = s.generateParenthesis(num);
        result.forEach(log::info);
        log.info();
      };

  @Test
  public void test() {
    c.accept(1);
    c.accept(2);
    c.accept(3);
    c.accept(4);
  }
}
