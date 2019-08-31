package com.g7tianyi.lintcode.array;

import com.g7tianyi.common.Strings;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 1, 2019
 *
 * @link https://www.lintcode.com/problem/maximize-distance-to-closest-person/description
 */
public class MaximizeDistanceToClosestPerson {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int maxDistToClosest(int[] seats) {

      int res = 0, i = 0, j;
      while (i < seats.length) {
        while (i < seats.length && seats[i] == 1) {
          ++i;
        }
        if (i == seats.length) {
          break;
        }

        j = i;
        while (j < seats.length && seats[j] == 0) {
          ++j;
        }

        if (i == 0 || j == seats.length) {
          res = Math.max(res, j - i);
        } else {
          res = Math.max(res, (j - i + 1) >> 1);
        }
        i = j;
      }

      return res;
    }
  }

  @AllArgsConstructor
  public static class Case {

    private int[] seats;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> runner =
        aCase -> {
          log.info(Strings.format(aCase.seats));
          log.info(s.maxDistToClosest(aCase.seats));
          log.info();
        };

    runner.accept(new Case(new int[] {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0}));
    runner.accept(new Case(new int[] {1, 0, 0, 0, 1, 0, 1}));
    runner.accept(new Case(new int[] {0, 0, 1, 0, 0}));
    runner.accept(new Case(new int[] {1, 0, 0, 0}));
    runner.accept(new Case(new int[] {0, 0, 0, 1}));
    runner.accept(new Case(new int[] {1, 1, 0, 1}));
  }
}
