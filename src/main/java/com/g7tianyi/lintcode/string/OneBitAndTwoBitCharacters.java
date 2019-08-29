package com.g7tianyi.lintcode.string;

import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 29, 2019
 *
 * @link https://www.lintcode.com/problem/1-bit-and-2-bit-characters/description
 */
public class OneBitAndTwoBitCharacters {

  public class Solution {

    public boolean isOneBitCharacter(int[] bits) {

      if (bits == null) {
        return false;
      }

      int len = bits.length;
      if (len == 0) {
        return false;
      }
      if (bits[len - 1] != 0) {
        return false;
      }

      int i = 0;
      while (i < len - 1) {
        if (bits[i] == 0) {
          ++i;
        } else {
          i += 2;
        }
      }

      return i == len - 1;
    }
  }

  @AllArgsConstructor
  private static class Case {

    private int[] bits;

    private boolean result;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c = aCase -> Assert.assertEquals(s.isOneBitCharacter(aCase.bits), aCase.result);

    c.accept(new Case(new int[] {1, 0, 0}, true));
    c.accept(new Case(new int[] {1, 1, 1, 0}, false));
  }
}
