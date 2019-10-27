package com.g7tianyi.lintcode.permutation;

import com.g7tianyi.util.Logger;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Oct 27, 2019
 *
 * @link https://www.lintcode.com/problem/previous-permutation/description
 */
public class PreviousPermutation {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public List<Integer> previousPermuation(List<Integer> nums) {

      int[] result = new int[nums.size()];
      for (int i = 0; i < result.length; ++i) {
        result[i] = nums.get(i);
      }

      int prev = result[result.length - 1];
      for (int i = result.length - 2; i >= 0; --i) {
        if (result[i] > prev) {
          // 在后面找到小于当前元素的最大的数字，然后与当前元素交换，
          // 最后，后面的数字需要降序排序
          int j = i + 1;
          for (int k = i + 2; k < result.length; ++k) {
            if (result[k] < result[i] && result[k] > result[j]) {
              j = k;
            }
          }
          swap(result, i, j);
          Arrays.sort(result, i + 1, result.length);
          reverse(result, i + 1, result.length - 1);
          return asList(result);
        }

        prev = result[i];
      }

      reverse(result, 0, result.length - 1);
      return asList(result);
    }

    private List<Integer> asList(int[] values) {
      List<Integer> result = new ArrayList<>();
      for (int value : values) {
        result.add(value);
      }
      return result;
    }

    private void reverse(int[] values, int i, int j) {
      int temp;
      while (i < j) {
        temp = values[i];
        values[i] = values[j];
        values[j] = temp;
        ++i;
        --j;
      }
    }

    private void swap(int[] values, int i, int j) {
      int temp = values[i];
      values[i] = values[j];
      values[j] = temp;
    }
  }

  private final Solution s = new Solution();

  private final Consumer<List<Integer>> c =
      values -> {
        log.info(s.previousPermuation(values));
        log.info(values);
        log.info();
      };

  @Test
  public void test() {
    c.accept(Lists.newArrayList(1, 2, 3, 4, 5));
    c.accept(Lists.newArrayList(1, 4, 2, 3, 5));
    c.accept(Lists.newArrayList(1, 3, 2, 3));
    c.accept(Lists.newArrayList(2, 1, 1));
  }
}
