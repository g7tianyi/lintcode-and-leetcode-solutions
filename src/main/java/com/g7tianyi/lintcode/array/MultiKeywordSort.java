package com.g7tianyi.lintcode.array;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 30, 2019
 *
 * @link https://www.lintcode.com/problem/multi-keyword-sort/description
 */
public class MultiKeywordSort {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int[][] multiSort(int[][] elems) {

      Comparator<int[]> comparator =
          new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
              if (o1[1] != o2[1]) {
                return Integer.compare(o2[1], o1[1]);
              }
              return Integer.compare(o1[0], o2[0]);
            }
          };

      quickSort(elems, 0, elems.length - 1, comparator);

      return elems;
    }

    public <T> void quickSort(T[] elems, int i, int j, Comparator<T> comparator) {

      if (i >= j) {
        return;
      }

      int k = partition(elems, i, j, comparator);
      quickSort(elems, i, k - 1, comparator);
      quickSort(elems, k + 1, j, comparator);
    }

    public <T> int partition(T[] elems, int i, int j, Comparator<T> comparator) {

      T pivot = elems[i];
      while (i < j) {
        while (j > i && comparator.compare(elems[j], pivot) > 0) {
          --j;
        }
        if (j > i) {
          elems[i] = elems[j];
        }

        while (i < j && comparator.compare(elems[i], pivot) <= 0) {
          ++i;
        }
        if (i < j) {
          elems[j] = elems[i];
        }
      }
      elems[j] = pivot;

      return j;
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<int[][]> c =
        elems -> {
          log.printMatrix(elems);
          log.info("--------");
          s.multiSort(elems);
          log.printMatrix(elems);
          log.info("########");
        };

    int[][] elems =
        new int[][] {
          {2, 50},
          {1, 50},
          {3, 100},
        };

    c.accept(elems);

    elems =
        new int[][] {
          {7, 66}, {4, 77}, {3, 63}, {5, 81}, {1, 88}, {9, 86}, {6, 88}, {2, 82}, {8, 55}, {10, 95}
        };
    c.accept(elems);
  }
}
