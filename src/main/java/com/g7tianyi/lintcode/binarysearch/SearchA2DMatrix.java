package com.g7tianyi.lintcode.binarysearch;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 27, 2019
 *
 * @link https://www.lintcode.com/problem/search-a-2d-matrix/description
 */
public class SearchA2DMatrix {

  private static final Logger log = new Logger();

  public class Solution {

    public boolean searchMatrix(int[][] elems, int value) {
      int m = elems.length;
      if (m == 0) {
        return false;
      }
      int n = elems[0].length;

      // 第一阶段，二分查找的本质是寻找插入的位置
      int former = 0, latter = m - 1, pivot, row = -1;
      while (former <= latter) {
        pivot = former + ((latter - former) >> 1);
        if (elems[pivot][0] == value) {
          return true;
        }

        // 这四行代码是关键
        if (pivot > former && elems[pivot][0] > value && elems[pivot - 1][0] < value) {
          row = pivot - 1;
          break;
        }

        if (elems[pivot][0] > value) {
          latter = pivot - 1;
        } else {
          former = pivot + 1;
        }
      }

      if (row == -1) {
        // latter对应的值小于value，former则是大于value
        row = latter;
      }

      if (row < 0 || row >= m) {
        return false;
      }

      former = 0;
      latter = n - 1;
      while (former <= latter) {
        pivot = former + ((latter - former) >> 1);
        if (elems[row][pivot] == value) {
          return true;
        }
        if (elems[row][pivot] > value) {
          latter = pivot - 1;
        } else {
          former = pivot + 1;
        }
      }

      return false;
    }
  }

  @AllArgsConstructor
  public static class TestCase {
    private int[][] elems;
    private int value;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<TestCase> c =
        testCase ->
            log.info("%d => %s", testCase.value, s.searchMatrix(testCase.elems, testCase.value));

    int[][] elems =
        new int[][] {
          {1, 2, 3, 4},
          {5, 6, 7, 8},
          {9, 10, 11, 12},
          {13, 14, 15, 16},
        };
    for (int i = 0; i < 18; i++) {
      c.accept(new TestCase(elems, i));
    }

    elems =
        new int[][] {
          {1, 2, 3, 4, 5},
          {6, 7, 8, 9, 10},
          {11, 12, 13, 14, 15},
          {16, 17, 18, 19, 20},
          {21, 22, 23, 24, 25},
        };
    for (int i = 0; i < 27; i++) {
      c.accept(new TestCase(elems, i));
    }
  }
}
