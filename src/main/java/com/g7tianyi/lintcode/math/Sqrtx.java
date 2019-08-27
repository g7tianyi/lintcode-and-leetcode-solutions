package com.g7tianyi.lintcode.math;

import com.g7tianyi.lintcode.util.Log;
import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by g7tianyi on Aug 28, 2019
 *
 * <p>Problem link: https://www.lintcode.com/problem/sqrtx/description
 */
public class Sqrtx {

  private static final Log log = new Log();

  public static class Solution {

    public int sqrt(int x) {
      if (x < 2) {
        return x;
      }

      int former = 0, latter = x >> 1, middle;
      long square;
      while (former <= latter) {
        middle = former + ((latter - former) >> 1);
        square = squareOf(middle);
        if (square == x) {
          return middle;
        }

        if (middle > former && square > x && squareOf(middle - 1) < x) {
          return middle - 1;
        }

        if (square > x) {
          latter = middle - 1;
        } else {
          former = middle + 1;
        }
      }

      return latter;
    }

    private static long squareOf(int x) {
      return ((long) x) * x;
    }
  }

  public class TLESolution {

    /* O(logX) */
    // 打表： 0 1 4 9 16 25 36 ...
    // 然后利用二分查找，寻找插入位置
    // 也不知道当时我脑子在想什么...
    public int sqrt(int x) {

      List<Integer> table = new ArrayList<>();

      int num = 0, square;
      while (true) {
        square = num * num;
        table.add(square);
        if (square > x) {
          break;
        }
        ++num;
      }

      int former = 0, latter = table.size() - 1, middle;
      while (former <= latter) {
        middle = former + ((latter - former) >> 1);
        num = table.get(middle);
        if (num == x) {
          return middle;
        }

        if (middle > former && num > x && (middle - 1) * (middle - 1) <= x) {
          return middle - 1;
        }

        if (num > x) {
          latter = middle - 1;
        } else {
          former = middle + 1;
        }
      }
      return latter;
    }
  }

  @AllArgsConstructor
  private static class TestCase {

    private int number;

    private int expected;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<TestCase> c =
        testCase -> {
          int result = s.sqrt(testCase.number);
          log.info("%d => %d", testCase.number, result);
          Assert.assertEquals(result, testCase.expected);
        };

    Function<Integer, TestCase> caseMaker = num -> new TestCase(num, (int) Math.sqrt((double) num));

    for (int i = 0; i < 26; i++) {
      c.accept(caseMaker.apply(i));
    }
    c.accept(caseMaker.apply(100));
    c.accept(caseMaker.apply(1001));
    c.accept(caseMaker.apply(Integer.MAX_VALUE));
  }
}
