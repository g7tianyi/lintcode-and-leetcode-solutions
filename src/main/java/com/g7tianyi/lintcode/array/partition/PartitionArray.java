package com.g7tianyi.lintcode.array.partition;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.common.Strings;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 20, 2019
 *
 * @link https://www.lintcode.com/problem/partition-array/description
 */
public class PartitionArray {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int partitionArray(int[] values, int pivot) {

      int i = 0, j = values.length - 1;
      while (i < j) {
        while (i < j && values[i] < pivot) {
          ++i;
        }
        if (i == j) {
          break;
        }

        while (j > i && values[j] >= pivot) {
          --j;
        }
        if (j == i) {
          break;
        }

        int temp = values[i];
        values[i] = values[j];
        values[j] = temp;
        ++i;
        --j;
      }

      if (i < values.length && values[i] < pivot) {
        ++i;
      }

      return i;
    }
  }

  @AllArgsConstructor
  private static class Case {
    int[] values;
    int pivot;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c =
        aCase -> {
          log.info("%s %d", Strings.format(aCase.values), aCase.pivot);
          log.info(s.partitionArray(aCase.values, aCase.pivot));
          log.info(aCase.values);
          log.info();
        };

    c.accept(new Case(new int[] {}, 9));
    c.accept(new Case(Arrays.from(3, 2, 2, 1), 2));

    c.accept(new Case(Arrays.from(4, 5, 2, 6, 4, 1, 5, 1, 3), 0));
    c.accept(new Case(Arrays.from(4, 5, 2, 6, 4, 1, 5, 1, 3), 1));
    c.accept(new Case(Arrays.from(4, 5, 2, 6, 4, 1, 5, 1, 3), 2));
    c.accept(new Case(Arrays.from(4, 5, 2, 6, 4, 1, 5, 1, 3), 3));
    c.accept(new Case(Arrays.from(4, 5, 2, 6, 4, 1, 5, 1, 3), 4));
    c.accept(new Case(Arrays.from(4, 5, 2, 6, 4, 1, 5, 1, 3), 5));
    c.accept(new Case(Arrays.from(4, 5, 2, 6, 4, 1, 5, 1, 3), 8));
  }
}
