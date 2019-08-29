package com.g7tianyi.lintcode.array;

import com.g7tianyi.common.Strings;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.Arrays;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 30, 2019
 *
 * @link https://www.lintcode.com/problem/array-partition-i/description
 */
public class ArrayPartition1 {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int arrayPairSum(int[] elems) {

      Arrays.sort(elems);

      int result = 0;
      for (int i = 0; i < elems.length; i += 2) {
        result += elems[i];
      }

      return result;
    }
  }

  @AllArgsConstructor
  public static class Case {

    private int[] elems;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> runner =
        aCase -> {
          log.info(Strings.format(aCase.elems));
          log.info(s.arrayPairSum(aCase.elems));
          log.info("");
        };

    runner.accept(new Case(new int[] {1, 4, 3, 2}));
    runner.accept(new Case(new int[] {5, 6}));
  }
}
