package com.g7tianyi.lintcode.array;

import com.g7tianyi.lintcode.util.Console;
import com.g7tianyi.lintcode.util.Log;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 25, 2019
 *
 * <p>Problem link: https://www.lintcode.com/problem/move-zeroes/description
 */
public class MoveZeros {

  private static final Log log = new Log();

  public class Solution {

    public void moveZeroes(int[] arr) {

      int zero = 0;
      int good = -1;
      int temp;

      while (zero < arr.length) {

        while (zero < arr.length && arr[zero] != 0) {
          zero++;
        }
        if (zero == arr.length) {
          break;
        }

        if (good == -1) {
          good = zero + 1;
        }
        while (good < arr.length && arr[good] == 0) {
          good++;
        }
        if (zero > good || good == arr.length) {
          break;
        }

        temp = arr[zero];
        arr[zero] = arr[good];
        arr[good] = temp;
      }
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
          s.moveZeroes(input.arr);
          log.info(Console.stringify(input.arr));
          log.info();
        };

    runner.accept(new Input(new int[] {0, 1, 0, 3, 12}));
    runner.accept(new Input(new int[] {0, 0, 0, 3, 1}));
    runner.accept(new Input(new int[] {0, 0, 7, 3, 0}));
    runner.accept(new Input(new int[] {7, 0, 3, 0, 0}));
    runner.accept(new Input(new int[] {7, 1, 3, 0, 0}));
  }
}
