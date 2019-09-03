package com.g7tianyi.lintcode.string.palindrome;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 03, 2019
 *
 * @link https://www.lintcode.com/problem/largest-palindrome-product/description
 */
public class LargestPalindromeProduct {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    // Searching (DFS/BFS, etc.) and constructing, two ways of solving searching problems
    public int largestPalindrome(int n) {
      int upper = (int) Math.pow(10, n);
      int lower = upper / 10;
      for (int i = upper - 1; i > lower; --i) {
        StringBuilder sb = new StringBuilder();
        sb.append(i);
        sb.reverse();

        long value = Long.valueOf(String.valueOf(i) + sb.toString());
        for (long j = upper - 1; j * j > value; --j) {
          if (value % j == 0) {
            return (int) (value % 1337);
          }
        }
      }
      return 9; // When the input is 1...
    }
  }

  @AllArgsConstructor
  private class Case {
    int n;
    int expect;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c =
        aCase -> {
          int result = s.largestPalindrome(aCase.n);
          log.info("%d => %d", aCase.n, result);
          Assert.assertEquals(aCase.expect, result);
        };

    c.accept(new Case(2, 987));
  }
}
