package com.g7tianyi.lintcode.string;

import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 30, 2019
 *
 * @link https://www.lintcode.com/problem/jewels-and-stones/description
 */
public class JewelsAndStones {

  public class Solution {

    public int numJewelsInStones(String J, String S) {

      char[] pos = new char[256];
      for (char i = 0; i < 256; ++i) {
        pos[i] = '0';
      }

      char[] jChars = J.toCharArray();
      for (char ch : jChars) {
        pos[ch] = '1';
      }

      int num = 0;
      char[] sChars = S.toCharArray();
      for (char ch : sChars) {
        if (pos[ch] == '1') {
          ++num;
        }
      }

      return num;
    }
  }

  @AllArgsConstructor
  private static class Case {

    private String J;
    private String S;
    private int num;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c =
        aCase -> Assert.assertEquals(aCase.num, s.numJewelsInStones(aCase.J, aCase.S));

    c.accept(new Case("aA", "aAAbbbb", 3));
    c.accept(new Case("z", "ZZ", 0));
  }
}
