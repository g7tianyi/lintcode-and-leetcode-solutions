package com.g7tianyi.lintcode.hashmap;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.common.Strings;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 03, 2019
 *
 * @link https://www.lintcode.com/problem/fair-candy-swap/description
 */
public class FairCandySwap {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int[] fairCandySwap(int[] A, int[] B) {

      int sum1 = 0, sum2 = 0;
      Set<Integer> set1 = new HashSet<>();
      Set<Integer> set2 = new HashSet<>();

      for (int elem : A) {
        sum1 += elem;
        set1.add(elem);
      }
      for (int elem : B) {
        sum2 += elem;
        set2.add(elem);
      }

      Set<Integer> small = set2;
      int[] elems = A;
      if (sum1 < sum2) {
        small = set1;
        elems = B;
      }

      int exchange = Math.abs(sum1 - sum2) >> 1;
      int option;

      for (int elem : elems) {
        option = elem - exchange;
        if (small.contains(option)) {
          if (sum1 < sum2) {
            return new int[] {option, elem};
          } else {
            return new int[] {elem, option};
          }
        }
      }

      return new int[2];
    }
  }

  @AllArgsConstructor
  private class Case {
    int[] A;
    int[] B;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c = aCase -> log.info(Strings.format(s.fairCandySwap(aCase.A, aCase.B)));

    c.accept(new Case(Arrays.from(1, 1), Arrays.from(2, 2)));
    c.accept(new Case(Arrays.from(2), Arrays.from(1, 3)));

    c.accept(new Case(Arrays.from(1, 4, 5, 6), Arrays.from(1, 3, 5)));
    c.accept(new Case(Arrays.from(1, 3, 5), Arrays.from(1, 4, 5, 6)));
  }
}
