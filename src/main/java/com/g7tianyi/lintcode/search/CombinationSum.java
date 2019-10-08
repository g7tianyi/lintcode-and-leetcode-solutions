package com.g7tianyi.lintcode.search;

import com.g7tianyi.common.Strings;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Oct 08, 2019
 *
 * @link https://www.lintcode.com/problem/combination-sum/description
 */
public class CombinationSum {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {

      List<List<Integer>> result = new ArrayList<>();
      if (candidates == null || candidates.length == 0) {
        return result;
      }

      int[] values = dedupArray(candidates);

      Arrays.sort(values);

      dfs(values, 0, 0, target, new LinkedList<>(), result);

      return result;
    }

    private void dfs(
        int[] values,
        int index,
        int curr,
        int target,
        LinkedList<Integer> path,
        List<List<Integer>> result) {

      if (curr == target) {
        result.add(new ArrayList<>(path));
        return;
      }

      for (int i = index; i < values.length; ++i) {
        if (curr + values[i] <= target) {
          path.addLast(values[i]);
          dfs(values, i, curr + values[i], target, path, result);
          path.removeLast();
        } else {
          break;
        }
      }
    }

    private int[] dedupArray(int[] candidates) {
      Set<Integer> set = new HashSet<>();
      for (int candidate : candidates) {
        set.add(candidate);
      }
      int i = 0;
      int[] values = new int[set.size()];
      for (Integer value : set) {
        values[i++] = value;
      }
      return values;
    }
  }

  private final Solution s = new Solution();

  @AllArgsConstructor
  private class Case {
    private int[] values;
    private int target;
  }

  private final Consumer<Case> c =
      aCase -> {
        log.info("%s | %d", Strings.format(aCase.values), aCase.target);
        List<List<Integer>> result = s.combinationSum(aCase.values, aCase.target);
        for (List<Integer> path : result) {
          log.info(path);
        }
        log.info();
      };

  @Test
  public void test() {

    c.accept(new Case(com.g7tianyi.common.Arrays.from(2, 3, 6, 7), 7));
    c.accept(new Case(com.g7tianyi.common.Arrays.from(1), 3));
  }
}
