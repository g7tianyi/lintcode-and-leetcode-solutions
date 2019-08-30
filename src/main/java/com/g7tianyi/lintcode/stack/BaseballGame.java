package com.g7tianyi.lintcode.stack;

import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 30, 2019
 *
 * @link https://www.lintcode.com/problem/baseball-game/description
 */
public class BaseballGame {

  public class Solution {

    public int calPoints(String[] operations) {

      Stack<Integer> stack = new Stack<>();
      for (String operation : operations) {
        if ("+".equals(operation)) {
          Integer score1 = stack.pop();
          Integer score2 = stack.pop();
          stack.push(score2);
          stack.push(score1);
          stack.push(score1 + score2);
        } else if ("C".equals(operation)) {
          stack.pop();
        } else if ("D".equals(operation)) {
          Integer score = stack.pop();
          stack.push(score);
          stack.push(score << 1);
        } else {
          stack.push(Integer.valueOf(operation));
        }
      }

      int result = 0;
      while (!stack.isEmpty()) {
        result += stack.pop();
      }
      return result;
    }
  }

  @AllArgsConstructor
  private static class Case {

    private String[] ops;

    private int result;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c = aCase -> Assert.assertEquals(aCase.result, s.calPoints(aCase.ops));

    c.accept(new Case(new String[] {"5", "2", "C", "D", "+"}, 30));
    c.accept(new Case(new String[] {"5", "-2", "4", "C", "D", "9", "+", "+"}, 27));
  }
}
