package com.g7tianyi.lintcode.hashmap;

import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 30, 2019
 *
 * @link https://www.lintcode.com/problem/distribute-candies/description
 */
public class DistributeCandies {

  public class Solution {

    public int distributeCandies(int[] candies) {

      // The number in given array is in range [-100000, 100000].
      // (100000 + 1 + 100000) = 200001 < 6251 * 32 bits
      int mask[] = new int[6251];
      for (int i = 0; i < 6251; ++i) {
        mask[i] = 0;
      }

      // to locate the mask index: num / 32 => num >> 5
      // to locate the bit in a number: num & 0x11111 => num & 31
      int index, pos, result = 0;
      for (int candy : candies) {
        index = candy >> 5;
        pos = 1 << (candy & 31);
        if ((mask[index] & pos) == 0) {
          mask[index] |= pos;
          ++result;
        }
      }

      return Math.min(result, candies.length >> 1);
    }
  }

  @AllArgsConstructor
  private static class Case {

    private int[] elems;
    private int result;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c = aCase -> Assert.assertEquals(aCase.result, s.distributeCandies(aCase.elems));

    c.accept(new Case(new int[] {1, 1, 2, 2, 3, 3}, 3));
  }
}
