package com.g7tianyi.lintcode.bitops;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 03, 2019
 *
 * @link https://www.lintcode.com/problem/hamming-distance/description
 */
public class HammingDistance {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int hammingDistance(int x, int y) {
      // write your code here
      return countOnes(x ^ y);
    }

    public int countOnes(int num) {
      int value = num;
      if (value < 0) {
        value = Math.abs(value + 1);
      }

      int result = 0;
      while (value != 0) {
        value &= (value - 1);
        ++result;
      }

      if (num >= 0) {
        return result;
      } else {
        return 32 - result;
      }
    }
  }

  @AllArgsConstructor
  private class Case {
    int x, y;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c =
        aCase -> {
          Assert.assertEquals(
              Integer.bitCount(aCase.x ^ aCase.y), s.hammingDistance(aCase.x, aCase.y));
        };

    c.accept(new Case(1, 4));
    c.accept(new Case(5, 2));
    c.accept(new Case(-111, 111));
  }
}
