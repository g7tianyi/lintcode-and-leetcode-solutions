package com.g7tianyi.lintcode.binarysearch;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 20, 2019
 *
 * @link https://www.lintcode.com/problem/3sum/description
 */
public class ThreeSum {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public List<List<Integer>> threeSum(int[] values) {
      Arrays.sort(values);
      List<List<Integer>> results = new ArrayList<>();
      findSum(values, 0, values.length - 1, 3, 0, new LinkedList<>(), results);
      return results;
    }

    private void findSum(
        int[] values,
        int former,
        int latter,
        int count,
        int value,
        LinkedList<Integer> numbers,
        List<List<Integer>> results) {

      if (count == 1) {
        if (contains(values, former, latter, value)) {
          List<Integer> result = new ArrayList<>(numbers);
          result.add(value);
          result.sort(Integer::compareTo);
          results.add(result);
        }
        return;
      }

      for (int i = former; i < latter; ++i) {
        if (i > former && values[i] == values[i - 1]) {
          continue;
        }
        numbers.addLast(values[i]);
        findSum(values, i + 1, latter, count - 1, value - values[i], numbers, results);
        numbers.removeLast();
      }
    }

    private boolean contains(int[] values, int former, int latter, int value) {
      int middle;
      while (former <= latter) {
        middle = former + ((latter - former) >> 1);
        if (values[middle] == value) {
          return true;
        }
        if (values[middle] < value) {
          former = middle + 1;
        } else {
          latter = middle - 1;
        }
      }
      return false;
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<int[]> c =
        values -> {
          log.info(values);
          s.threeSum(values).forEach(log::info);
          log.info();
        };

    c.accept(com.g7tianyi.common.Arrays.from(-1, 0, 1));
    c.accept(com.g7tianyi.common.Arrays.from(2, 7, 11, 15));
    c.accept(com.g7tianyi.common.Arrays.from(-1, 0, 1, 2, -1, -4));
  }
}
