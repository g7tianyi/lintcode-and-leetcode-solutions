package com.g7tianyi.lintcode.permutation;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.Arrays;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Oct 09, 2019
 *
 * @link https://www.lintcode.com/problem/next-permutation-ii/description
 */
public class NextPermutation2 {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public void nextPermutation(int[] values) {

      if (values == null || values.length < 2) {
        return;
      }

      for (int i = values.length - 2; i >= 0; --i) {
        // 如果一直是降序的就跳过
        if (values[i] >= values[i + 1]) {
          continue;
        }

        // Ai Ai+1 Ai+2 ... An
        // 在Ai+1~An中找到大于Ai的最小的数字，与Ai交换
        // 然后Ai+1~An升序排序
        int next = -1;
        for (int j = i + 1; j < values.length; ++j) {
          if (values[j] > values[i] && (next == -1 || values[next] > values[i])) {
            next = j;
          }
        }

        if (next != -1) {
          swap(values, i, next);
          Arrays.sort(values, i + 1, values.length);
          return;
        }
      }

      Arrays.sort(values);
    }

    private void swap(int[] values, int i, int j) {
      int temp = values[i];
      values[i] = values[j];
      values[j] = temp;
    }
  }

  private final Solution s = new Solution();

  private final Consumer<int[]> c =
      values -> {
        log.info(values);
        s.nextPermutation(values);
        log.info(values);
        log.info();
      };

  @Test
  public void test() {
    c.accept(com.g7tianyi.common.Arrays.from(1, 2));
    c.accept(com.g7tianyi.common.Arrays.from(2, 1));
    c.accept(com.g7tianyi.common.Arrays.from(2, 2));

    c.accept(com.g7tianyi.common.Arrays.from(1, 2, 3));
    c.accept(com.g7tianyi.common.Arrays.from(3, 2, 1));
    c.accept(com.g7tianyi.common.Arrays.from(1, 1, 5));

    int[] arr1 = com.g7tianyi.common.Arrays.from(1, 2, 3, 4);
    for (int i = 0; i < 4 * 3 * 2; ++i) {
      log.info(arr1);
      s.nextPermutation(arr1);
    }
    log.info();

    int[] arr2 = com.g7tianyi.common.Arrays.from(1, 2, 2, 3);
    for (int i = 0; i < 4 * 3; ++i) {
      log.info(arr2);
      s.nextPermutation(arr2);
    }
    log.info();

    int[] arr3 = com.g7tianyi.common.Arrays.from(1, 2, 2, 2);
    for (int i = 0; i < 4; ++i) {
      log.info(arr3);
      s.nextPermutation(arr3);
    }
  }
}
