package com.g7tianyi.lintcode.array;

import com.g7tianyi.lintcode.util.Console;
import com.g7tianyi.lintcode.util.Log;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 24, 2019
 *
 * <p>Problem link: https://www.lintcode.com/problem/median/description
 */
public class Median {

  private static final Log log = new Log();

  public class Solution {

    public int median(int[] arr) {

      int index = arr.length >> 1;
      if ((arr.length & 1) == 0) {
        index--;
      }

      int former = 0, latter = arr.length - 1, pivot;
      while (true) {
        pivot = partition(arr, former, latter);
        if (pivot == index) {
          break;
        }

        if (pivot < index) {
          former = pivot + 1;
        } else {
          latter = pivot - 1;
        }
      }

      return arr[pivot];
    }

    // 仿QuickSort的partition过程，过程结束后，返回一个坐标
    // 该坐标左边的数字全部小于原输入数组的第一个元素，右边的都大于这个元素
    // https://blog.csdn.net/morewindows/article/details/6684558
    // https://www.cnblogs.com/foreverking/articles/2234225.html
    private int partition(int[] arr, int former, int latter) {

      int value = arr[former];

      while (former != latter) {
        while (former < latter && arr[latter] >= value) {
          latter--;
        }
        if (latter > former) {
          arr[former] = arr[latter];
        }

        while (former < latter && arr[former] <= value) {
          former++;
        }
        if (former < latter) {
          arr[latter] = arr[former];
        }
      }

      arr[former] = value;

      return former;
    }
  }

  @AllArgsConstructor
  public static class Input {

    private int[] arr;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Input> runner =
        input -> {
          log.info(Console.stringify(input.arr));
          log.info(s.median(input.arr));
          log.info("");
        };

    runner.accept(new Input(new int[] {4, 5, 1, 2, 3}));
    runner.accept(new Input(new int[] {7, 9, 4, 5}));
    runner.accept(new Input(new int[] {1, 2}));
    runner.accept(new Input(new int[] {1}));
  }
}
