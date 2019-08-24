package com.g7tianyi.lintcode.array;

import com.g7tianyi.lintcode.util.Console;
import com.g7tianyi.lintcode.util.Log;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 24, 2019
 *
 * <p>Problem link: https://www.lintcode.com/problem/two-sum/description
 */
public class TwoSum {

  private static final Log log = new Log();

  public class Solution {

    private class Elem {

      private int value;

      private int index;
    }

    public int[] twoSum(int[] arr, int sum) {

      Elem[] elems = new Elem[arr.length];
      for (int i = 0; i < arr.length; i++) {
        Elem elem = new Elem();
        elem.value = arr[i];
        elem.index = i;
        elems[i] = elem;
      }

      Arrays.sort(
          elems,
          new Comparator<Elem>() {
            @Override
            public int compare(Elem o1, Elem o2) {
              return -Integer.compare(o2.value, o1.value);
            }
          });

      for (int i = 0; i < elems.length - 1; i++) {
        int index = binarySearch(elems, i + 1, sum - elems[i].value);
        if (index != -1) {
          if (elems[i].index < index) {
            return new int[] {elems[i].index, index};
          } else {
            return new int[] {index, elems[i].index};
          }
        }
      }
      return new int[] {};
    }

    private int binarySearch(Elem[] arr, int former, int value) {
      int latter = arr.length - 1;
      while (former <= latter) {
        int middle = (former + latter) >> 1;
        if (arr[middle].value == value) {
          return arr[middle].index;
        }

        if (arr[middle].value < value) {
          former = middle + 1;
        } else {
          latter = middle - 1;
        }
      }
      return -1;
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
          log.info(Console.stringify(input.arr) + " " + input.sum);
          Console.log(s.twoSum(input.arr, input.sum));
          log.info();
        };

    runner.accept(new Input(new int[] {2, 7, 11, 15}, 9));
    runner.accept(new Input(new int[] {15, 2, 7, 11}, 9));
  }
}
