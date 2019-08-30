package com.g7tianyi.lintcode.array;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 30, 2019
 *
 * @link https://www.lintcode.com/problem/monotonic-array/description
 */
public class MonotonicArray {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public boolean isMonotonic(int[] elems) {
      if (elems == null) {
        return false;
      }
      if (elems.length == 1) {
        return true;
      }

      int len = elems.length;
      int mono = elems[1] - elems[0];
      int i = 2, curr;
      while (i < len) {
        curr = elems[i] - elems[i - 1];
        ++i;

        if (curr == 0) {
          continue;
        }

        if (mono == 0) {
          mono = curr;
        } else if ((mono > 0 && curr < 0) || (mono < 0 && curr > 0)) {
          return false;
        }
      }

      return true;
    }
  }

  @AllArgsConstructor
  private static class Case {
    private int[] elems;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c =
        aCase -> {
          log.info(s.isMonotonic(aCase.elems));
          log.info();
        };

    c.accept(new Case(new int[] {1, 2, 2, 3}));
    c.accept(new Case(new int[] {1, 3, 2}));
  }
}
