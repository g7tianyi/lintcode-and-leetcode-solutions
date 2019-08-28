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
 * @link https://www.lintcode.com/problem/sort-integers-ii/description
 */
public class SortIntegers2 {

  private static final Logger log = new Logger();

  static class Solution {

    public void sortIntegers2(int[] elems) {
      quickSort(elems, 0, elems.length - 1);
    }

    private static void quickSort(int[] elems, int former, int latter) {
      if (former >= latter) {
        return;
      }

      int split = partition(elems, former, latter);
      quickSort(elems, former, split - 1);
      quickSort(elems, split + 1, latter);
    }

    private static int partition(int[] elems, int former, int latter) {
      int pivot = elems[former];
      while (former < latter) {
        // 在尾部找到一个小于pivot的数字
        while (latter > former && elems[latter] >= pivot) {
          --latter;
        }
        // 然后将找到的这个数字放到前面来
        if (latter > former) {
          elems[former] = elems[latter];
        }

        // 再在前面找到一个大于pivot的数字
        while (former < latter && elems[former] <= pivot) {
          ++former;
        }
        // 然后将找到的这个数字放到后面去
        if (former < latter) {
          elems[latter] = elems[former];
        }
      }
      elems[latter] = pivot;
      return latter;
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
          s.sortIntegers2(input.elems);
          log.info(Strings.format(input.elems));
          log.info("");
        };

    runner.accept(new Input(new int[] {}));
    runner.accept(new Input(new int[] {1}));
    runner.accept(new Input(new int[] {1, 2, 3, -1, -1}));
    runner.accept(new Input(new int[] {3, 2, 1, 4, 5}));
    runner.accept(new Input(new int[] {1, 1, 2, 1, 1}));
    runner.accept(new Input(Arrays.of(10, 100)));
    runner.accept(
        new Input(
            new int[] {
              19, -10, -2, 40, 3, 36, 57, 25, 66, 51, 5, 40, -8, 43, 9, -5, 0, 4, 55, 28, 63, 67,
              -2, 35, 57, 0, 0, 30, 17, 55, 22, 34, -4, 42, 57, 52, 4, 65, 6, -2, 8, 12, 31, 43, 26,
              34, 31, 19, -3, 36, 34, 11, 58, 23, 13, 7, 17, 10, 33, -5, 10, 53, 14, 56, 18, 8, -6,
              -2, 37, 7
            }));
  }
}
