package com.g7tianyi.lintcode.array;

import com.g7tianyi.common.Strings;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 30, 2019
 *
 * @link https://www.lintcode.com/problem/find-pivot-index/description
 */
public class FindPivotIndex {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int pivotIndex(int[] elems) {

      int len = elems.length;
      for (int i = 1; i < len; ++i) {
        elems[i] += elems[i - 1];
      }

      for (int i = 0, l, r; i < len; ++i) {
        l = i > 0 ? elems[i - 1] : 0;
        r = elems[len - 1] - elems[i];
        if (l == r) {
          return i;
        }
      }

      return -1;
    }
  }

  @AllArgsConstructor
  public static class Input {

    private int[] elems;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Input> runner =
        input -> {
          log.info(Strings.format(input.elems));
          log.info(s.pivotIndex(input.elems));
          log.info("");
        };

    runner.accept(new Input(new int[] {-1, -1, -1, 0, 1, 1}));
    runner.accept(new Input(new int[] {-1, -1, 0, 1, 1, 0}));
    runner.accept(new Input(new int[] {1, 7, 3, 6, 5, 6}));
    runner.accept(new Input(new int[] {1, 2, 3}));
  }
}
