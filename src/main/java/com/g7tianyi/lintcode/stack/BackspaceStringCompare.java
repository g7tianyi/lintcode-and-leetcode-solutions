package com.g7tianyi.lintcode.stack;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 03, 2019
 *
 * @link https://www.lintcode.com/problem/backspace-string-compare/description
 */
public class BackspaceStringCompare {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public boolean backspaceCompare(String S, String T) {
      S = parseText(S);
      T = parseText(T);
      log.info(S);
      log.info(T);
      return S.equals(T);
    }

    private String parseText(String s) {
      Stack<Character> stack = new Stack<>();
      char[] chars = s.toCharArray();
      for (char ch : chars) {
        if (ch == '#') {
          if (!stack.isEmpty()) {
            stack.pop();
          }
        } else {
          stack.push(ch);
        }
      }

      StringBuilder sb = new StringBuilder();
      while (!stack.isEmpty()) {
        sb.append(stack.pop());
      }
      return sb.toString();
    }
  }

  @AllArgsConstructor
  private class Case {
    String S;
    String T;
    boolean expect;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c =
        aCase -> Assert.assertEquals(aCase.expect, s.backspaceCompare(aCase.S, aCase.T));

    c.accept(
        new Case(
            "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa####################################################################################################",
            "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb####################################################################################################",
            true));
    c.accept(new Case("ab#c", "ad#c", true));
    c.accept(new Case("a#c", "b", false));

    log.info("ABC".charAt(0));
  }
}
