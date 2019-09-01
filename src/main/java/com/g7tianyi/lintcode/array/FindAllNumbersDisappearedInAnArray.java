package com.g7tianyi.lintcode.array;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.common.Strings;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 02, 2019
 *
 * @link https://www.lintcode.com/problem/find-all-numbers-disappeared-in-an-array/description
 */
public class FindAllNumbersDisappearedInAnArray {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public List<Integer> findDisappearedNumbers(int[] elems) {
      // write your code here
      int i = 0, curr, next;
      while (i < elems.length) {
        // Index | 0  1  2  3  4  5  6  7 |
        // Array |------------------------|
        // Array | 4  3  2  7  8  2  3  1 |
        // Array | 4  3  2 -4  8  2  3  1 |
        // Array | 4  3  2 -4  8  2 -7  1 |
        // Array | 4  3 -3 -4  8  2 -7  1 |
        // Array | 4 -2 -3 -4  8  2 -7  1 |
        // Array | 4 -2 -3 -4  8  2 -7  1 |
        // Array | ....                   |
        while (i < elems.length && elems[i] < 0) {
          ++i;
        }
        if (i == elems.length) {
          break;
        }

        curr = elems[i];
        while (curr > 0) {
          next = elems[curr - 1];
          elems[curr - 1] = -curr; // Use negative number to mark as appeared
          curr = next;
        }
        ++i;
      }

      List<Integer> result = new ArrayList<>();
      for (int j = 0; j < elems.length; ++j) {
        if (elems[j] > 0) {
          result.add(j + 1);
        }
      }
      return result;
    }
  }

  @AllArgsConstructor
  private class Case {

    int[] elems;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c =
        aCase -> {
          log.info(Strings.format(aCase.elems));
          log.info(Strings.format(s.findDisappearedNumbers(aCase.elems)));
          log.info();
        };

    c.accept(new Case(Arrays.from(10, 2, 5, 10, 9, 1, 1, 4, 3, 7)));
    c.accept(new Case(Arrays.from(4, 3, 2, 7, 8, 2, 3, 1)));
    c.accept(new Case(Arrays.from(1, 1, 1, 1, 1, 1, 1, 1)));
  }
}
