package com.g7tianyi.lintcode.array;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.common.Strings;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 28, 2019
 *
 * @link https://www.lintcode.com/problem/remove-duplicates-from-sorted-array-ii/description
 */
public class RemoveDuplicatesFromSortedArray2 {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int removeDuplicates(int[] elems) {
      if (elems == null) {
        return 0;
      }

      int len = elems.length;
      if (len < 3) {
        return len;
      }

      int i = 1, j = 1, dup = 1;
      while (j < len) {
        if (elems[j] != elems[j - 1]) {
          elems[i] = elems[j];
          ++i;
          dup = 1;
        } else {
          ++dup;
          if (dup < 3) {
            elems[i] = elems[j];
            ++i;
          }
        }
        ++j;
      }

      return i;
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
    c.accept(new TestCase(new int[] {1, 1, 1, 2, 2, 3}));
    c.accept(new TestCase(new int[] {1, 1, 1}));
    c.accept(new TestCase(Arrays.sortedArrayOf(10, 5)));
    c.accept(new TestCase(Arrays.sortedArrayOf(11, 6)));
    c.accept(new TestCase(Arrays.sortedArrayOf(12, 4)));
  }
}
