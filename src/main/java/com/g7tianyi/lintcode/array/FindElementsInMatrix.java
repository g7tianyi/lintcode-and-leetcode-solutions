package com.g7tianyi.lintcode.array;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 28, 2019
 *
 * @link https://www.lintcode.com/problem/partition-array-by-odd-and-even/description
 */
public class PartitionArrayByOddAndEven {

  private static final Logger log = new Logger();

  public class Solution {

    // Do it in-place
    // 😉😉😉
    public void partitionArray(int[] elems) {

      int i = 0, j = elems.length - 1, temp;

      while (i < j) {
        while (i < j && (elems[i] & 1) == 1) {
          ++i;
        }
        if (i == j) {
          break;
        }

        while (j > i && (elems[j] & 1) == 0) {
          --j;
        }
        if (j == i) {
          break;
        }

        temp = elems[i];
        elems[i] = elems[j];
        elems[j] = temp;
      }
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
          log.info(aCase.elems);
          s.partitionArray(aCase.elems);
          log.info(aCase.elems);
          log.info();
        };

    c.accept(new Case(new int[] {1, 2, 3, 4}));
    c.accept(new Case(new int[] {1, 4, 2, 3, 5, 6}));
  }
}
