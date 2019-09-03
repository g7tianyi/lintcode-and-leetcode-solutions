package com.g7tianyi.lintcode.string.reverse;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 03, 2019
 *
 * @link https://www.lintcode.com/problem/reverse-vowels-of-a-string/description
 */
public class ReverseVowelsOfString {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    private final char[] vowels =
        new char[] {
          'a', 'e', 'i', 'o', 'u',
          'A', 'E', 'I', 'O', 'U'
        };

    public String reverseVowels(String s) {

      List<Integer> vowelIndices = new ArrayList<>();
      char[] chars = s.toCharArray();
      for (int i = 0; i < chars.length; ++i) {
        if (isVowel(chars[i])) {
          vowelIndices.add(i);
        }
      }

      StringBuilder sb = new StringBuilder(s);
      int i = 0, j = vowelIndices.size() - 1, si, sj;
      while (i < j) {
        si = vowelIndices.get(i);
        sj = vowelIndices.get(j);

        char ch = sb.charAt(si);
        sb.setCharAt(si, sb.charAt(sj));
        sb.setCharAt(sj, ch);

        ++i;
        --j;
      }

      return sb.toString();
    }

    private boolean isVowel(char ch) {
      for (char vowel : vowels) {
        if (ch == vowel) {
          return true;
        }
      }
      return false;
    }
  }

  @AllArgsConstructor
  private class Case {
    String string;
    String expect;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c =
        aCase -> {
          String result = s.reverseVowels(aCase.string);
          log.info(aCase.string);
          log.info(result);
          Assert.assertEquals(aCase.expect, result);
        };

    c.accept(new Case("hello", "holle"));
    c.accept(new Case("lintcode", "lentcodi"));
    c.accept(new Case("abcabc", "abcabc"));
  }
}
