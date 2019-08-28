package com.g7tianyi.lintcode.binarysearch;

import com.g7tianyi.common.Strings;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 24, 2019
 *
 * @link https://www.lintcode.com/problem/search-insert-position/description
 */
public class SearchInsertPosition {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int searchInsert(int[] arr, int num) {
      int former = 0, latter = arr.length - 1, middle;
      while (former <= latter) {
        middle = former + ((latter - former) >> 1);
        if (arr[middle] == num) {
          return middle;
        }

        if (middle > former && arr[middle] > num && arr[middle - 1] < num) {
          return middle;
        }

        if (arr[middle] > num) {
          latter = middle - 1;
        } else {
          former = middle + 1;
        }
      }

      return former;
    }
  }

  @AllArgsConstructor
  public static class Input {

    private int[] arr;

    private int sum;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Input> runner =
        input -> {
          log.info(Strings.format(input.arr) + " " + input.sum);
          log.info(s.searchInsert(input.arr, input.sum));
          log.info();
        };

    runner.accept(new Input(new int[] {1, 3, 5, 6}, 5));
    runner.accept(new Input(new int[] {1, 3, 5, 6}, 2));
    runner.accept(new Input(new int[] {1, 3, 5, 6}, 7));
    runner.accept(new Input(new int[] {1, 3, 5, 6}, 0));

    runner.accept(new Input(new int[] {1, 3, 5, 6, 8}, 6));
    runner.accept(new Input(new int[] {1, 3, 5, 6, 8}, 9));
    runner.accept(new Input(new int[] {1, 3, 5, 6, 8}, 2));
    runner.accept(new Input(new int[] {1, 3, 5, 6, 8}, 0));
  }
}
