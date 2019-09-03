package com.g7tianyi.lintcode.string.match;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 03, 2019
 *
 * @link https://www.lintcode.com/problem/implement-strstr/description
 */
public class ImplementStrstr {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int strStr(String source, String target) {
      char[] sChars = source.toCharArray();
      char[] tChars = target.toCharArray();

      for (int i = 0; i <= sChars.length - tChars.length; ++i) {
        boolean match = true;
        for (int j = 0; j < tChars.length; ++j) {
          if (tChars[j] != sChars[i + j]) {
            match = false;
            break;
          }
        }
        if (match) {
          return i;
        }
      }
      return -1;
    }
  
  }
  
  @AllArgsConstructor
  private class Case {
    String source;
    String target;
  }
  
   @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c = aCase -> log.info(s.strStr(aCase.source, aCase.target));

    c.accept(new Case("1234abc", "abc"));
    c.accept(new Case("1234abc", "1abc"));

  }
}
