package com.g7tianyi.lintcode.greedy;

import com.g7tianyi.common.Strings;
import com.g7tianyi.util.Logger;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 31, 2019
 *
 * @link https://www.lintcode.com/problem/lemonade-change/description
 */
public class LemonadeChange {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public boolean lemonadeChange(List<Integer> bills) {
      int fives = 0;
      int tens = 0;
      for (Integer bill : bills) {
        if (bill == 5) {
          ++fives;
          continue;
        }
        if (bill == 10) {
          if (fives == 0) {
            return false;
          }
          --fives;
          ++tens;
        }
        if (bill == 20) {
          if (tens > 0 && fives > 0) {
            --tens;
            --fives;
          } else if (fives > 3) {
            fives -= 3;
          } else {
            return false;
          }
        }
      }
      return true;
    }
  }

  @AllArgsConstructor
  public static class Case {

    private List<Integer> elems;
    private boolean res;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> runner =
        aCase -> {
          log.info(Strings.format(aCase.elems));
          boolean result = s.lemonadeChange(aCase.elems);
          log.info(result);
          Assert.assertEquals(result, aCase.res);
          log.info();
        };

    runner.accept(new Case(Lists.newArrayList(5, 5, 5, 10, 20), true));
    runner.accept(new Case(Lists.newArrayList(5, 5, 10, 10, 20), false));
  }
}
