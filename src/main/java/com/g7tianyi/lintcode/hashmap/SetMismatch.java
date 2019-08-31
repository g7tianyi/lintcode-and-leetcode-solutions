package com.g7tianyi.lintcode.hashmap;

import com.g7tianyi.common.Strings;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 31, 2019
 *
 * @link https://www.lintcode.com/problem/contains-duplicate-iii/description
 */
public class SetMismatch {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    // RadixSort
    public int[] findErrorNums(int[] elems) {

      char[] pos = new char[elems.length];
      for (int i = 0; i < pos.length; ++i) {
        pos[i] = '0';
      }

      for (int elem : elems) {
        if (pos[elem - 1] == '0') {
          pos[elem - 1] = '1';
        } else if (pos[elem - 1] == '1') {
          pos[elem - 1] = '2';
        }
      }

      int repeat = 0, missing = 0;
      for (int i = 0; i < pos.length; ++i) {
        if (pos[i] == '0') {
          missing = i + 1;
        } else if (pos[i] == '2') {
          repeat = i + 1;
        }
      }
      return new int[] {repeat, missing};
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
          log.info(Strings.format(aCase.elems));
          log.info(Strings.format(s.findErrorNums(aCase.elems)));
          log.info();
        };

    c.accept(new Case(new int[] {1, 1}));
    c.accept(new Case(new int[] {2, 2}));
    c.accept(new Case(new int[] {1, 2, 2, 4}));
    c.accept(new Case(new int[] {1, 3, 3, 4}));
  }
}
