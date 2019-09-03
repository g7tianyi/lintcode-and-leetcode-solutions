package com.g7tianyi.lintcode.string;

import com.g7tianyi.util.Logger;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by g7tianyi on Sep 03, 2019
 *
 * @link https://www.lintcode.com/problem/compare-strings/description
 */
public class CompareStrings {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public boolean compareStrings(String A, String B) {

      int[] posA = new int[26], posB = new int[26];
      for (int i = 0; i < 26; ++i) {
        posA[i] = 0;
        posB[i] = 0;
      }

      for (int i = 0; i < A.length(); ++i) {
        ++posA[A.charAt(i) - 'A'];
      }
      for (int i = 0; i < B.length(); ++i) {
        ++posB[B.charAt(i) - 'A'];
      }

      for (int i = 0; i < B.length(); ++i) {
        if (posA[i] < posB[i]) {
          return false;
        }
      }

      return true;
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Assert.assertTrue(s.compareStrings("ABCD", "ACD"));
    Assert.assertFalse(s.compareStrings("ABCD", "AABC"));
  }
}
