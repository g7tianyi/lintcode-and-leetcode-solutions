package com.g7tianyi.lintcode.array;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.common.Strings;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 28, 2019
 *
 * @link https://www.lintcode.com/problem/sort-integers/description
 */
public class SortIntegers {

  private static final Logger log = new Logger();

  public class Solution {

    public void sortIntegers(int[] elems) {
      // 选择排序
      int len = elems.length, m, temp;
      for (int i = 0; i < len - 1; i++) {
        m = i;
        for (int j = i + 1; j < len; j++) {
          if (elems[m] > elems[j]) {
            m = j;
          }
        }
        temp = elems[m];
        elems[m] = elems[i];
        elems[i] = temp;
      }
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
          s.sortIntegers(input.elems);
          log.info(Strings.format(input.elems));
          log.info("");
        };

    runner.accept(new Input(new int[] {}));
    runner.accept(new Input(new int[] {1}));
    runner.accept(new Input(new int[] {1, 2, 3, -1, -1}));
    runner.accept(new Input(new int[] {3, 2, 1, 4, 5}));
    runner.accept(new Input(new int[] {1, 1, 2, 1, 1}));
    runner.accept(new Input(Arrays.of(10, 100)));
  }
}
