package com.g7tianyi.lintcode.hashmap;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 1, 2019
 *
 * @link https://www.lintcode.com/problem/ransom-note/description
 */
public class RansomNote {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public boolean canConstruct(String ransomNote, String magazine) {
      int[] rMap = new int[26];
      int[] mMap = new int[26];
      for (int i = 0; i < 26; ++i) {
        rMap[i] = 0;
        mMap[i] = 0;
      }

      char[] rChars = ransomNote.toCharArray();
      for (char ch : rChars) {
        ++rMap[ch - 'a'];
      }

      char[] mChars = magazine.toCharArray();
      for (char ch : mChars) {
        ++mMap[ch - 'a'];
      }

      for (int i = 0; i < 26; ++i) {
        if (mMap[i] < rMap[i]) {
          return false;
        }
      }
      return true;
    }
  }

  @AllArgsConstructor
  private static class Case {

    private String ransomNote;
    private String magazine;
    private boolean result;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c =
        aCase ->
            Assert.assertEquals(aCase.result, s.canConstruct(aCase.ransomNote, aCase.magazine));

    c.accept(new Case("aaa", "aab", false));
    c.accept(new Case("aa", "aaa", true));
  }
}
