package com.g7tianyi.lintcode.search;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 25, 2019
 *
 * @link https://www.lintcode.com/problem/subsets/description
 */
public class Subsets {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public List<List<Integer>> subsets(int[] values) {
      Arrays.sort(values);
      List<List<Integer>> result = new ArrayList<>();
      backtrace(values, 0, new LinkedList<>(), result);
      return result;
    }

    public void backtrace(
        int[] values, int index, LinkedList<Integer> numbers, List<List<Integer>> results) {

      if (index == values.length) {
        results.add(new LinkedList<>(numbers));
        return;
      }

      backtrace(values, index + 1, numbers, results);

      numbers.addLast(values[index]);
      backtrace(values, index + 1, numbers, results);
      numbers.removeLast();
    }
  }

  private final Solution s = new Solution();

  Consumer<int[]> c =
      values -> {
        log.info(values);
        List<List<Integer>> subsets = s.subsets(values);
        for (List<Integer> subset : subsets) {
          log.info(subset);
        }
        log.info("----------");
      };

  @Test
  public void test() {
    c.accept(new int[] {1, 2, 3});
    c.accept(new int[] {1});
  }
}
