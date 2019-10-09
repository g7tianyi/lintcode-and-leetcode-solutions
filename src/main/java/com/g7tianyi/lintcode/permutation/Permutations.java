package com.g7tianyi.lintcode.permutation;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 06, 2019
 *
 * @link https://www.lintcode.com/problem/permutations/description
 */
public class Permutations {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public List<List<Integer>> permute(int[] nums) {

      boolean[] used = new boolean[nums.length];
      for (int i = 0; i < nums.length; ++i) {
        used[i] = false;
      }

      List<List<Integer>> result = new ArrayList<>();
      backtrace(nums, used, new LinkedList<>(), result);
      return result;
    }

    private void backtrace(
        int[] nums, boolean[] used, List<Integer> selections, List<List<Integer>> result) {

      if (selections.size() == nums.length) {
        result.add(new LinkedList<>(selections));
      }

      for (int i = 0; i < nums.length; ++i) {
        if (!used[i]) {
          selections.add(nums[i]);
          used[i] = true;
          backtrace(nums, used, selections, result);
          used[i] = false;
          selections.remove(selections.size() - 1);
        }
      }
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<int[]> c = aCase -> {
      log.info(s.permute(aCase));
    };

    c.accept(Arrays.from(1,2,3));
  }
}
