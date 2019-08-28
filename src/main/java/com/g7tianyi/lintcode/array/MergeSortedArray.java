package com.g7tianyi.lintcode.array;

import com.g7tianyi.common.Strings;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 27, 2019
 *
 * @link https://www.lintcode.com/problem/merge-sorted-array/description
 */
public class MergeSortedArray {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    /**
     * 1) Copy the total elements of array A to its tail
     *
     * 2) Introduce two pointers which point to array A and B respectively
     *
     * 3) Always pick up the smaller value from the two pointers and move the pointers forward
     * accordingly
     *
     * #) Watch out for the case that an array could has some elements without being picked up
     */
    public void mergeSortedArray(int[] A, int m, int[] B, int n) {

      if (n == 0) {
        return;
      }

      if (m == 0) {
        System.arraycopy(B, 0, A, 0, n);
        return;
      }

      System.arraycopy(A, 0, A, n, m);

      int i = n, j = 0, pos = 0;
      while (i < m + n && j < n) {
        if (A[i] < B[j]) {
          A[pos] = A[i];
          ++i;
        } else {
          A[pos] = B[j];
          ++j;
        }
        ++pos;
      }

      while (j < n) {
        A[pos++] = B[j++];
      }
    }
  }

  @AllArgsConstructor
  public static class Input {

    private int[] A;
    private int m;
    private int[] B;
    private int n;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Input> runner =
        input -> {
          s.mergeSortedArray(input.A, input.m, input.B, input.n);
          log.info(Strings.format(input.A));
          log.info("");
        };

    runner.accept(new Input(new int[] {1, 2, 3, -1, -1}, 3, new int[] {4, 5}, 2));
    runner.accept(new Input(new int[] {1, 2, 5, -1, -1}, 3, new int[] {3, 4}, 2));
    runner.accept(new Input(new int[] {1, 3, 9, -1, -1, -1, -1}, 3, new int[] {2, 4, 7, 11}, 4));
    runner.accept(
        new Input(new int[] {9, 10, 11, 12, 13, -1, -1, -1, -1}, 5, new int[] {4, 5, 6, 7}, 4));
    runner.accept(
        new Input(new int[] {4, 5, 6, 7, -1, -1, -1, -1, -1}, 4, new int[] {9, 10, 11, 12, 13}, 5));
    runner.accept(new Input(new int[] {4, 5, 6}, 3, new int[] {}, 0));
  }
}
