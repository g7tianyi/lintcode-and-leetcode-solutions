package com.g7tianyi.lintcode.array;

import com.g7tianyi.common.Strings;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 27, 2019
 *
 * @link https://www.lintcode.com/problem/remove-duplicates-from-sorted-array/description
 */
public class RemoveDuplicatesFromSortedArray {

  private static final Logger log = new Logger();

  public class Solution {

    public int removeDuplicates(int[] elems) {

      int ret = elems.length;
      if (ret == 0) {
        return 0;
      }

      int dup = 0, pos = 1;
      int prev = elems[0];
      for (int i = 1; i < elems.length; i++) {
        if (elems[i] == prev) {
          ++dup;
          continue;
        }

        elems[pos++] = elems[i];
        prev = elems[i];
        ret -= dup;
        dup = 0;
      }
      ret -= dup;

      return ret;
    }
  }

  @AllArgsConstructor
  public static class TestCase {
    private int[] elems;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<TestCase> c =
        testCase -> {
          log.info(Strings.format(testCase.elems));
          int len = s.removeDuplicates(testCase.elems);
          log.info(Strings.format(testCase.elems, len));
          log.info();
        };

    c.accept(new TestCase(new int[] {}));
    c.accept(new TestCase(new int[] {1, 1, 2}));
    c.accept(new TestCase(new int[] {1, 1, 1}));
    c.accept(new TestCase(new int[] {1, 2, 3, 3, 4}));
    c.accept(new TestCase(new int[] {1, 2, 3, 3, 3}));
    c.accept(new TestCase(new int[] {1, 2, 3, 4, 5}));
    c.accept(new TestCase(new int[] {-15, -7, -6, -1, 1, 2, 6, 11, 15, 15}));
    c.accept(
        new TestCase(
            new int[] {
              -14, -14, -13, -13, -13, -13, -13, -13, -13, -12, -12, -12, -12, -11, -10, -9, -9, -9,
              -8, -7, -5, -5, -5, -5, -4, -3, -3, -2, -2, -2, -2, -1, -1, -1, -1, -1, 0, 1, 1, 1, 1,
              2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 6, 6, 6, 6, 7, 8, 8, 8, 9, 9, 9, 10, 10, 10,
              11, 11, 11, 12, 12, 12, 13, 14, 14, 14, 14, 15, 16, 16, 16, 18, 18, 18, 19, 19, 19,
              19, 20, 20, 20, 21, 21, 21, 21, 21, 21, 22, 22, 22, 22, 22, 23, 23, 24, 25, 25
            }));
  }
}
