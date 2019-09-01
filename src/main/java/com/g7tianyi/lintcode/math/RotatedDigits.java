package com.g7tianyi.lintcode.math;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 02, 2019
 *
 * @link
 */
public class RotatedDigits {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int rotatedDigits(int N) {
      int result = 0;
      for (int i = 1, j, k, required, illegal; i <= N; ++i) {
        required = 0;
        illegal = 0;
        j = i;
        while (j != 0) {
          k = j % 10;
          j /= 10;
          if (k == 3 || k == 4 || k == 7) {
            illegal = 1;
            break;
          } else if (k == 2 || k == 5 || k == 6 || k == 9) {
            ++required;
          }
        }
        if (illegal == 0 && required > 0) {
          ++result;
        }
      }
      return result;
    }
  }

  @AllArgsConstructor
  private class Case {
    int N;
    int expect;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c = aCase -> Assert.assertEquals(aCase.expect, s.rotatedDigits(aCase.N));

    c.accept(new Case(10, 4));
    c.accept(new Case(5, 2));
  }
}
