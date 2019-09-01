package com.g7tianyi.lintcode.binarysearch;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 01, 2019
 *
 * @link https://www.lintcode.com/problem/find-smallest-letter-greater-than-target/description
 */
public class FindSmallestLetterGreaterThanTarget {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public char nextGreatestLetter(char[] letters, char target) {

      // Find the right pos to insert the target character and keep the array ordered

      int former = 0, latter = letters.length - 1, middle;
      while (former <= latter) {
        middle = former + ((latter - former) >> 1);
        if (middle > former && letters[middle] > target && letters[middle - 1] < target) {
          former = middle;
          break;
        }
        if (letters[middle] > target) {
          latter = middle - 1;
        } else {
          former = middle + 1;
        }
      }
      return letters[former % letters.length];
    }
  }

  @AllArgsConstructor
  private class Case {
    char[] letters;
    char target;
    char expect;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c =
        aCase ->
            Assert.assertEquals(aCase.expect, s.nextGreatestLetter(aCase.letters, aCase.target));

    c.accept(new Case(new char[] {'c', 'f', 'j'}, 'a', 'c'));
    c.accept(new Case(new char[] {'c', 'f', 'j'}, 'c', 'f'));
    c.accept(new Case(new char[] {'c', 'f', 'j'}, 'd', 'f'));
    c.accept(new Case(new char[] {'c', 'f', 'j'}, 'g', 'j'));
    c.accept(new Case(new char[] {'c', 'f', 'j'}, 'j', 'c'));
    c.accept(new Case(new char[] {'c', 'f', 'j'}, 'k', 'c'));
    c.accept(new Case(new char[] {'c', 'c', 'c', 'c', 'f', 'f', 'f', 'f', 'j'}, 'a', 'c'));
    c.accept(new Case(new char[] {'c', 'c', 'c', 'c', 'f', 'f', 'f', 'f', 'j'}, 'c', 'f'));
    c.accept(new Case(new char[] {'c', 'c', 'c', 'c', 'f', 'f', 'f', 'f', 'j'}, 'd', 'f'));
    c.accept(new Case(new char[] {'c', 'c', 'c', 'c', 'f', 'f', 'f', 'f', 'j'}, 'g', 'j'));
    c.accept(new Case(new char[] {'c', 'c', 'c', 'c', 'f', 'f', 'f', 'f', 'j'}, 'j', 'c'));
    c.accept(new Case(new char[] {'c', 'c', 'c', 'c', 'f', 'f', 'f', 'f', 'j'}, 'k', 'c'));
  }
}
