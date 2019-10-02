package com.g7tianyi.lintcode.array.subsequence;

import com.g7tianyi.common.Strings;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 31, 2019
 *
 * @link https://www.lintcode.com/problem/shortest-unsorted-continuous-subarray/description
 */
public class ShortestUnsortedContinuousSubArray {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int findUnsortedSubarray(int[] elems) {

      if (elems == null || elems.length < 2) {
        return 0;
      }

      int[] sorted = new int[elems.length];
      System.arraycopy(elems, 0, sorted, 0, elems.length);
      Arrays.sort(sorted);

      int i = 0;
      while (i < elems.length && elems[i] == sorted[i]) {
        ++i;
      }

      int j = elems.length - 1;
      while (j > i && elems[j] == sorted[j]) {
        --j;
      }

      return j - i + 1;
    }
  }

  @AllArgsConstructor
  public static class Case {

    private int[] elems;

    private int result;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> runner =
        aCase -> {
          log.info(Strings.format(aCase.elems));
          int result = s.findUnsortedSubarray(aCase.elems);
          log.info(result);
          log.info();
          Assert.assertEquals(aCase.result, result);
        };

    runner.accept(new Case(new int[] {2, 6, 4, 8, 10, 9, 15}, 5));
    runner.accept(new Case(new int[] {2, 8, 15}, 0));
    runner.accept(new Case(new int[] {2, 8, 7, 15}, 2));
    runner.accept(new Case(new int[] {2, 1}, 2));
    runner.accept(new Case(new int[] {5, 4, 3, 2, 1}, 5));
  }
}
