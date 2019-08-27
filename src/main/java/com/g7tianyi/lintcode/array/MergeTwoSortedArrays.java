package com.g7tianyi.lintcode.array;

import com.g7tianyi.lintcode.util.Console;
import com.g7tianyi.lintcode.util.Log;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 24, 2019
 *
 * @link https://www.lintcode.com/problem/merge-two-sorted-arrays/description
 */
public class MergeTwoSortedArrays {

  private static final Log log = new Log();

  public class Solution {

    public int[] mergeSortedArray(int[] A, int[] B) {
      int[] result = new int[A.length + B.length];
      int a = 0, b = 0, r = 0;
      while (a < A.length && b < B.length) {
        if (A[a] < B[b]) {
          result[r++] = A[a++];
        } else {
          result[r++] = B[b++];
        }
      }

      while (a < A.length) {
        result[r++] = A[a++];
      }

      while (b < B.length) {
        result[r++] = B[b++];
      }

      return result;
    }
  }

  @AllArgsConstructor
  public static class Input {

    private int[] A;
    private int[] B;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Input> runner =
        input -> {
          Console.log(input.A);
          Console.log(input.B);
          Console.log(s.mergeSortedArray(input.A, input.B));
          log.info("");
        };

    {
      int[] A = {1};
      int[] B = {1};
      runner.accept(new Input(A, B));
    }

    {
      int[] A = {1, 2, 3, 4};
      int[] B = {2, 4, 5, 6};
      runner.accept(new Input(A, B));
    }
  }
}
