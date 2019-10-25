package com.g7tianyi.lintcode.binarysearch;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by g7tianyi on Oct 25, 2019
 *
 * @link https://www.lintcode.com/problem/legal-number-statistics-ii/description
 */
public class LegalNumberStatistics2 {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int[] getAns(int[] values, int[][] qs) {

      Arrays.sort(values);

      int[] result = new int[qs.length];
      int index = 0;
      for (int[] Q : qs) {
        int l = lower(values, Q[0]);
        int u = upper(values, Q[1]);
        result[index++] = u - l + 1;
      }
      return result;
    }

    private int lower(int[] values, int value) {
      int i = 0, j = values.length - 1, m;
      while (i <= j) {
        m = i + ((j - i) >> 1);
        if (values[m] >= value) {
          if (m == 0 || values[m - 1] < value) {
            return m;
          } else {
            j = m - 1;
          }
        } else {
          i = m + 1;
        }
      }
      return i;
    }

    private int upper(int[] values, int value) {
      int i = 0, j = values.length - 1, m;
      while (i <= j) {
        m = i + ((j - i) >> 1);
        if (values[m] <= value) {
          if (m == values.length - 1 || values[m + 1] > value) {
            return m;
          } else {
            i = m + 1;
          }
        } else {
          j = m - 1;
        }
      }
      return j;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(
        s.getAns(
            new int[] {1, 2, 3, 4, 5, 6},
            new int[][] {
              {1, 2},
              {1, 5},
            }));

    log.info(
        s.getAns(
            new int[] {1, 2, 3, 3, 3, 5},
            new int[][] {
              {1, 2},
              {-1, 8},
              {-1, 3},
              {4, 5},
              {4, 8},
              {6, 9},
            }));
  }
}
