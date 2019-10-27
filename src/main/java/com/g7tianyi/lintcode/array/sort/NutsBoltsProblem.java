package com.g7tianyi.lintcode.array.sort;

import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Oct 27, 2019
 *
 * @link https://www.lintcode.com/problem/nuts-bolts-problem/description
 */
public class NutsBoltsProblem {

  private static final Logger log = Logger.getInstance();

  public class NBComparator {
    public int cmp(String a, String b) {
      return 0;
    }
  }

  public class Solution {

    public void sortNutsAndBolts(String[] nuts, String[] bolts, NBComparator compare) {
      if (nuts == null || bolts == null) {
        return;
      }
      if (nuts.length != bolts.length) {
        return;
      }

      qsort(nuts, bolts, compare, 0, nuts.length - 1);
    }

    private void qsort(String[] nuts, String[] bolts, NBComparator compare, int start, int end) {
      if (start >= end) {
        return;
      }

      int pivot = partition(nuts, bolts[start], compare, start, end);
      partition(bolts, nuts[pivot], compare, start, end);
      qsort(nuts, bolts, compare, start, pivot - 1);
      qsort(nuts, bolts, compare, pivot + 1, end);
    }

    private int partition(String[] str, String pivot, NBComparator compare, int start, int end) {
      for (int i = start; i <= end; i++) {
        if (compare.cmp(str[i], pivot) == 0 || compare.cmp(pivot, str[i]) == 0) {
          swap(str, i, start);
          break;
        }
      }

      String hole = str[start];
      int left = start;
      int right = end;
      while (left < right) {
        while (left < right
            && (compare.cmp(str[right], pivot) == 1 || compare.cmp(pivot, str[right]) == -1)) {
          right--;
        }
        str[left] = str[right];

        while (left < right
            && (compare.cmp(str[left], pivot) == -1 || compare.cmp(pivot, str[left]) == 1)) {
          left++;
        }
        str[right] = str[left];
      }
      str[left] = hole;

      return left;
    }

    private void swap(String[] str, int i, int j) {
      String temp = str[i];
      str[i] = str[j];
      str[j] = temp;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {}
}
