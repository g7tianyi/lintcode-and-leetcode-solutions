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
 * @link https://www.lintcode.com/problem/permutations-ii/description
 */
public class Permutations2 {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public List<List<Integer>> permuteUnique(int[] values) {

      boolean[] used = new boolean[values.length];
      for (int i = 0; i < values.length; ++i) {
        used[i] = false;
      }
      Arrays.sort(values);

      List<List<Integer>> result = new ArrayList<>();
      backtrace(values, used, new LinkedList<>(), result);
      return result;
    }

    private void backtrace(
        int[] values, boolean[] used, LinkedList<Integer> choices, List<List<Integer>> result) {

      if (choices.size() == values.length) {
        result.add(new ArrayList<>(choices));
        return;
      }

      Integer lastChoice = null;
      for (int i = 0; i < values.length; ++i) {
        if (used[i]) {
          continue;
        }
        if (lastChoice != null && values[i] == lastChoice) {
          continue;
        }
        lastChoice = values[i];

        used[i] = true;
        choices.add(lastChoice);
        backtrace(values, used, choices, result);
        choices.removeLast();
        used[i] = false;
      }
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<int[]> c =
        values -> {
          List<List<Integer>> result = s.permuteUnique(values);
          for (List<Integer> list : result) {
            log.info(list);
          }
          log.info();
        };

    c.accept(com.g7tianyi.common.Arrays.from(1, 1));
    c.accept(com.g7tianyi.common.Arrays.from(1, 2, 2));
    c.accept(com.g7tianyi.common.Arrays.from(1, 2, 2, 3));
    c.accept(com.g7tianyi.common.Arrays.from(1, 2, 2, 3, 3));
  }
}
