package com.g7tianyi.lintcode.array.twopointers;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Oct 08, 2019
 *
 * @link https://www.lintcode.com/problem/sort-colors/description
 */
public class SortColors {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    // 前后两个指针，这个应该叫什么算法？或者也就只是一种编程技巧吧
    // 在现实生活中，千万别写这样的代码，太难懂了，有的算法题的确是对实践缺少指导意义
    // 好吧，也许嵌入式领域有意义？
    public void sortColors(int[] values) {
      if (values == null || values.length == 0) {
        return;
      }

      int i = 0;
      while (i < values.length && values[i] == 0) {
        ++i;
      }

      int k = values.length - 1;
      while (k >= 0 && values[k] == 2) {
        --k;
      }

      int j = i;
      while (j <= k) {
        if (values[j] == 0) {
          swap(values, i, j);
          while (i <= k && values[i] == 0) {
            ++i;
          }
          j = Math.max(j, i);
        } else if (values[j] == 2) {
          swap(values, k, j);
          while (k >= i && values[k] == 2) {
            --k;
          }
        } else {
          ++j;
        }
      }
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
        s.sortColors(values);
        log.info(values);
        log.info();
      };

  @Test
  public void test() {
    c.accept(Arrays.from(1, 0, 1, 2));
    c.accept(Arrays.from(1, 1, 0, 2, 2, 1, 0, 0, 2, 1));
    c.accept(Arrays.from(0, 2, 2, 2, 2, 0, 2, 0, 2, 2));
    c.accept(Arrays.from(2, 2, 2, 2));
    c.accept(Arrays.from(2, 2, 2, 1));
    c.accept(Arrays.from(2, 2, 2, 0));

    for (int i = 0; i < 5; ++i) {
      c.accept(Arrays.random(10, 3));
    }

    for (int i = 0; i < 5; ++i) {
      c.accept(Arrays.random(9, 3));
    }
  }
}
