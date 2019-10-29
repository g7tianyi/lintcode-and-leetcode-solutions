package com.g7tianyi.lintcode.array.majority;

import com.g7tianyi.util.Logger;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Oct 28, 2019
 *
 * @link https://www.lintcode.com/problem/majority-element-ii/description
 */
public class MajorityElement2 {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int majorityNumber(List<Integer> values) {

      int len = values.size();
      if (len < 3) {
        return values.get(0);
      }

      // 循环找到三个不同的数字，然后丢弃之
      int i = 0, j;
      int v1, v2;
      while (i < len) {
        v1 = values.get(i);

        j = i + 1;
        while (j < len && values.get(j).equals(v1)) {
          ++j;
        }
        if (j == len) {
          return v1;
        }

        swap(values, i + 1, j);
        v2 = values.get(i + 1);

        j = i + 2;
        while (j < len && (values.get(j).equals(v1) || values.get(j).equals(v2))) {
          ++j;
        }
        if (j == len) {
          return occurrence(values, v1) > occurrence(values, v2) ? v1 : v2;
        }

        swap(values, i + 2, j);

        i += 3;
      }

      return values.get(i - 1);
    }

    private int occurrence(List<Integer> values, int value) {
      int result = 0;
      for (int val : values) {
        if (val == value) {
          ++result;
        }
      }
      return result;
    }

    private void swap(List<Integer> values, int i, int j) {
      if (i == j) {
        return;
      }
      Integer value = values.get(i);
      values.set(i, values.get(j));
      values.set(j, value);
    }
  }

  private final Solution s = new Solution();

  private final Consumer<List<Integer>> c =
      values -> {
        log.info(values);
        log.info(s.majorityNumber(values));
        log.info();
      };

  @Test
  public void test() {

    c.accept(Lists.newArrayList(99, 2, 99, 2, 99, 3, 3));
    c.accept(Lists.newArrayList(1, 2, 1, 2, 1, 3, 3));

    List<Integer> values = Lists.newArrayList(3, 3, 3, 3, 2, 2, 2, 1, 4, 6);
    for (int i = 0; i < 10; ++i) {
      Collections.shuffle(values);
      c.accept(values);
    }

    c.accept(Lists.newArrayList(3, 3, 3, 3, 2, 2, 2, 1, 4));
    c.accept(Lists.newArrayList(3, 3, 3, 3, 2, 2, 2, 1));
    c.accept(Lists.newArrayList(3, 3, 3, 3, 2, 2, 2));

    c.accept(Lists.newArrayList(1, 1, 1, 1, 2, 2, 3, 3, 4, 4, 4));
  }
}
