package com.g7tianyi.lintcode.array;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.common.Numbers;
import com.g7tianyi.common.Strings;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by g7tianyi on Aug 28, 2019
 *
 * @link https://www.lintcode.com/problem/remove-element/description
 */
public class RemoveElement {

  private static final Logger log = new Logger();

  public class Solution {

    public int removeElement(int[] elems, int value) {
      if (elems == null || elems.length == 0) {
        return 0;
      }

      int i = 0, j = elems.length - 1;
      while (i < j) {
        while (i < j && elems[i] != value) {
          i++;
        }
        if (i == j) {
          break;
        }

        while (j > i && elems[j] == value) {
          --j;
        }
        if (j == i) {
          break;
        }

        int temp = elems[i];
        elems[i] = elems[j];
        elems[j] = temp;
      }

      for (i = 0; i < elems.length; i++) {
        if (elems[i] == value) {
          break;
        }
      }

      return i;
    }
  }

  @AllArgsConstructor
  public static class Case {
    private int[] elems;
    private int value;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> runner =
        aCase -> {
          log.info(aCase.value);
          log.info(Strings.format(aCase.elems));
          int len = s.removeElement(aCase.elems, aCase.value);
          log.info(Strings.format(aCase.elems, len));
          log.info("");
        };

    runner.accept(new Case(new int[] {}, 0));
    runner.accept(new Case(new int[] {2}, 3));
    runner.accept(new Case(new int[] {0, 4, 4, 0, 0, 2, 4, 4}, 4));
    runner.accept(new Case(new int[] {4, 4, 4, 4, 4, 4, 4, 4}, 4));

    Function<Integer, Case> caseMaker =
        len -> {
          int[] elems = Arrays.of(len, len >> 1);
          int value = elems[Numbers.nextInt(elems.length - 1)];
          return new Case(elems, value);
        };

    for (int i = 1; i < 21; i++) {
      runner.accept(caseMaker.apply(i));
    }
  }
}
