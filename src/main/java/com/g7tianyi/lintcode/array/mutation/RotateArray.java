package com.g7tianyi.lintcode.array.mutation;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 03, 2019
 *
 * @link https://www.lintcode.com/problem/rotate-array/description
 */
public class RotateArray {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    // How to make it within O(1) Space
    /** @see com.g7tianyi.lintcode.string.RotateString; */
    public int[] rotate(int[] elems, int k) {
      if (k < 0) {
        return elems;
      }

      k %= elems.length;
      if (k == 0) {
        return elems;
      }

      int[] temp = new int[k];
      System.arraycopy(elems, elems.length - k, temp, 0, k);
      System.arraycopy(elems, 0, elems, k, elems.length - k);
      System.arraycopy(temp, 0, elems, 0, k);
      return elems;
    }
  }

  @AllArgsConstructor
  private class Case {
    int[] elems;
    int k;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c = aCase -> log.info(s.rotate(aCase.elems, aCase.k));

    c.accept(new Case(Arrays.from(1, 2, 3, 4, 5, 6, 7), 3));
    log.info();

    c.accept(new Case(Arrays.from(-1, -100, 3, 99), 1));
    c.accept(new Case(Arrays.from(-1, -100, 3, 99), 5));
    log.info();

    c.accept(new Case(Arrays.from(-1, -100, 3, 99), 2));
    c.accept(new Case(Arrays.from(-1, -100, 3, 99), 6));
    log.info();

    c.accept(new Case(Arrays.from(-1, -100, 3, 99), 3));
    c.accept(new Case(Arrays.from(-1, -100, 3, 99), 7));
    log.info();

    c.accept(new Case(Arrays.from(-1, -100, 3, 99), 4));
    c.accept(new Case(Arrays.from(-1, -100, 3, 99), 8));
    log.info();
  }
}
