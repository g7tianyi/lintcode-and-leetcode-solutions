package com.g7tianyi.lintcode.others;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 1, 2019
 *
 * @link https://www.lintcode.com/problem/judge-route-circle/description
 */
public class JudgeRouteCircle {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public boolean judgeCircle(String moves) {

      int x = 0, y = 0;

      char[] moveChars = moves.toCharArray();
      for (char ch : moveChars) {
        if (ch == 'L') {
          ++x;
        } else if (ch == 'R') {
          --x;
        } else if (ch == 'U') {
          ++y;
        } else if (ch == 'D') {
          --y;
        }
      }

      return x == 0 && y == 0;
    }
  }

  @AllArgsConstructor
  public static class Case {
    private String moves;
    private boolean result;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> runner =
        aCase -> {
          boolean result = s.judgeCircle(aCase.moves);
          log.info(result);
          log.info();
          Assert.assertEquals(aCase.result, result);
        };

    runner.accept(new Case("UD", true));
    runner.accept(new Case("LL", false));
  }
}
