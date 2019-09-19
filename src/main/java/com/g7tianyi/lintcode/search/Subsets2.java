package com.g7tianyi.lintcode.search;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 19, 2019
 *
 * @link https://www.lintcode.com/problem/subsets-ii/description
 */
public class Subsets2 {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public List<List<Integer>> subsetsWithDup(int[] values) {

      List<List<Integer>> results = new ArrayList<>();
      results.add(new LinkedList<>());

      Arrays.sort(values);
      for (int i = 1; i <= values.length; ++i) {
        backtrace(values, i, 0, new LinkedList<>(), results);
      }

      return results;
    }

    private void backtrace(
        int[] values,
        int length,
        int index,
        LinkedList<Integer> numbers,
        List<List<Integer>> results) {

      if (numbers.size() == length) {
        results.add(new ArrayList<>(numbers));
        return;
      }

      for (int i = index; i < values.length; ++i) {
        if (i == index || values[i] != values[i - 1]) {
          numbers.add(values[i]);
          backtrace(values, length, i + 1, numbers, results);
          numbers.removeLast();
        }
      }
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<int[]> c =
        values -> {
          List<List<Integer>> results = s.subsetsWithDup(values);
          for (List<Integer> result : results) {
            log.info(result);
          }
          log.info();
        };

    c.accept(new int[] {});
    c.accept(com.g7tianyi.common.Arrays.from(1));
    c.accept(com.g7tianyi.common.Arrays.from(1, 2, 2));
    c.accept(com.g7tianyi.common.Arrays.from(3, 2, 1));
    c.accept(com.g7tianyi.common.Arrays.from(1, 2, 2, 2));
  }
}
