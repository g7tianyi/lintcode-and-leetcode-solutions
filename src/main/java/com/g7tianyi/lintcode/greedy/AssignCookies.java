package com.g7tianyi.lintcode.greedy;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 31, 2019
 *
 * @link https://www.lintcode.com/problem/assign-cookies/description
 */
public class AssignCookies {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int findContentChildren(int[] g, int[] s) {

      Arrays.sort(g);
      Arrays.sort(s);

      int gi = 0, si = 0;
      int result = 0;
      while (gi < g.length && si < s.length) {
        while (si < s.length && s[si] < g[gi]) {
          ++si;
        }
        if (si == s.length) {
          break;
        }

        ++result;
        ++gi;
        ++si;
      }
      return result;
    }
  }

  @AllArgsConstructor
  public static class Case {

    private int[] g;
    private int[] s;
    private int res;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> runner =
        aCase -> {
          int result = s.findContentChildren(aCase.g, aCase.s);
          log.info(result);
          Assert.assertEquals(result, aCase.res);
          log.info();
        };

    runner.accept(new Case(new int[] {1, 2, 3}, new int[] {1, 1}, 1));
    runner.accept(new Case(new int[] {1, 2}, new int[] {1, 2, 3}, 2));
    runner.accept(new Case(new int[] {1, 2, 2, 3, 5}, new int[] {1, 2, 1, 2, 3}, 4));
  }
}
