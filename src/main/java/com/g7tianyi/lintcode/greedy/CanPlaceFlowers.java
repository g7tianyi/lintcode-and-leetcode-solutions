package com.g7tianyi.lintcode.greedy;

import com.g7tianyi.common.Strings;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 31, 2019
 *
 * @link https://www.lintcode.com/problem/can-place-flowers/description
 */
public class CanPlaceFlowers {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public boolean canPlaceFlowers(int[] elems, int num) {
      int i = 1, len = elems.length;
      while (num != 0) {
        while (i + 1 < len && !(elems[i - 1] == 0 && elems[i] == 0 && elems[i + 1] == 0)) {
          ++i;
        }
        if (i + 1 == len) {
          return false;
        }
        elems[i] = 1;
        --num;
      }
      return true;
    }
  }

  @AllArgsConstructor
  public static class Case {

    private int[] elems;
    private int num;
    private boolean res;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> runner =
        aCase -> {
          log.info(Strings.format(aCase.elems));
          boolean result = s.canPlaceFlowers(aCase.elems, aCase.num);
          log.info(result);
          Assert.assertEquals(result, aCase.res);
          log.info();
        };

    runner.accept(new Case(new int[] {1, 0, 0, 0, 1}, 1, true));
    runner.accept(new Case(new int[] {1, 0, 0, 0, 1}, 2, false));
  }
}
