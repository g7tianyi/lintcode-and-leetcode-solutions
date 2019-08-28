package com.g7tianyi.lintcode.binarysearch;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.common.Strings;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 24, 2019
 *
 * @link https://www.lintcode.com/problem/first-position-of-target/description
 */
public class FirstPositionOfTarget {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    // 找到value第一次出现的下标（从0开始）
    // 移动former和latter的关键在于，移动后的区间一定要包含答案
    public int binarySearch(int[] arr, int value) {

      long former = 0, latter = arr.length;
      int result = -1;
      while (former <= latter) {
        int middle = (int) ((former + latter) >> 1);
        if (arr[middle] > value) {
          latter = middle - 1;
        } else if (arr[middle] < value) {
          former = middle + 1;
        } else {
          latter = middle - 1;
          result = middle;
        }
      }
      return result;
    }
  }

  @AllArgsConstructor
  public static class Input {

    private int[] arr;
    private int value;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Input> runner =
        input -> {
          log.info(Strings.format(input.arr) + " " + input.value);
          log.info(s.binarySearch(input.arr, input.value));
          log.info("");
        };

    runner.accept(new Input(Arrays.from(1, 4, 4, 5, 7, 7, 8, 9, 9, 10), 1));
    runner.accept(new Input(Arrays.from(1, 2, 3, 3, 4, 5, 10), 3));
    runner.accept(new Input(Arrays.from(1, 2, 3, 3, 4, 5, 10), 6));
    runner.accept(new Input(Arrays.from(1, 1, 1, 1, 1, 1, 1, 1), 1));
    runner.accept(new Input(Arrays.from(2, 6, 8, 13, 15, 17, 17, 18, 19, 20), 15));
    runner.accept(new Input(Arrays.from(1, 1), 1));
    runner.accept(new Input(Arrays.from(1), 1));
    runner.accept(new Input(Arrays.from(1), 0));
  }
}
