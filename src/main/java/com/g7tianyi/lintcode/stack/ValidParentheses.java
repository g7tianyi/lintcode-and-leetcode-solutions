package com.g7tianyi.lintcode.stack;

import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 28, 2019
 *
 * @link https://www.lintcode.com/problem/valid-parentheses/description
 */
public class ValidParentheses {

  public class Solution {

    public boolean isValidParentheses(String s) {
      Stack<Character> stack = new Stack<>();
      for (int i = 0; i < s.length(); ++i) {
        char curr = s.charAt(i);
        if (stack.isEmpty()) {
          stack.push(curr);
        } else {
          char prev = stack.peek();
          if ((prev == '(' && curr == ')')
              || (prev == '[' && curr == ']')
              || (prev == '{' && curr == '}')) {
            stack.pop();
          } else {
            stack.push(curr);
          }
        }
      }
      return stack.isEmpty();
    }
  }

  @AllArgsConstructor
  private static class Case {

    private String s;

    private boolean result;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c = aCase -> Assert.assertEquals(aCase.result, s.isValidParentheses(aCase.s));

    c.accept(new Case("([)]", false));
    c.accept(new Case("()[]{}", true));
  }
}
