package com.g7tianyi.lintcode.search;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by g7tianyi on Oct 03, 2019
 *
 * @link https://www.lintcode.com/problem/combination-sum-ii/description
 */
public class CombinationSum2 {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public List<List<Integer>> combinationSum2(int[] values, int target) {
      return new Resolver(values, target).resolve();
    }

    private class Resolver {

      private List<List<Integer>> result;
      private int[] values;
      private int target;

      public Resolver(int[] values, int target) {
        this.values = values;
        this.target = target;
        this.result = new ArrayList<>();
      }

      public List<List<Integer>> resolve() {
        if (values == null || values.length == 0) {
          return result;
        }

        Arrays.sort(values);
        resolve(0, 0, new LinkedList<>());
        return result;
      }

      private void resolve(int pos, int currSum, LinkedList<Integer> path) {
        for (int i = pos; i < values.length; ++i) {
          if (i > pos && values[i] == values[i - 1]) { // 去重的关键
            continue;
          }

          if (currSum + values[i] > target) { // 算是一种剪枝
            break;
          }

          path.addLast(values[i]);
          if (currSum + values[i] == target) {
            result.add(new ArrayList<>(path));
          } else {
            resolve(i + 1, currSum + values[i], path);
          }
          path.removeLast();
        }
      }
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.combinationSum2(com.g7tianyi.common.Arrays.from(1, 1, 1, 1, 2), 3));
    log.info(s.combinationSum2(com.g7tianyi.common.Arrays.from(7, 1, 2, 5, 1, 6, 10), 8));
  }
}
