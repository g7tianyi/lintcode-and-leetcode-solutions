package com.g7tianyi.lintcode.heap;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Oct 23, 2019
 *
 * @link https://www.lintcode.com/problem/heapify/description
 */
public class Heapify {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public void heapify(int[] values) {

      // 模拟push的操作，相当于现在尾部append一个元素
      // 然后逐级上溯，确保最小堆结构
      // O(N*logN)
      for (int i = 1; i < values.length; ++i) {
        int j = i, p;
        int value = values[i];
        while (true) {
          p = (j - 1) >> 1;
          if (values[p] <= values[j]) {
            break;
          }

          values[j] = values[p];
          values[p] = value;
          j = p;
          if (j == 0) {
            break;
          }
        }
      }
    }
  }

  private final Solution s = new Solution();

  private final Consumer<int[]> c =
      values -> {
        log.info(values);
        s.heapify(values);
        log.info(values);
        log.info();
      };

  @Test
  public void test() {
    c.accept(Arrays.from(3, 2, 1, 4, 5));
    c.accept(Arrays.randomNatureNumbers(15, 100));
  }
}
