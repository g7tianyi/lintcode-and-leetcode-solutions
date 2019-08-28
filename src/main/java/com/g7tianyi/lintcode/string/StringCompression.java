package com.g7tianyi.lintcode.string;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 29, 2019
 *
 * @link https://www.lintcode.com/problem/string-compression/description
 */
public class StringCompression {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public String compress(String s) {

      if (s == null) {
        return null;
      }

      StringBuilder sb = new StringBuilder();
      if (s.length() == 0) {
        return sb.toString();
      }

      char prev = s.charAt(0), curr;
      int count = 1;
      for (int i = 1; i < s.length(); ++i) {
        curr = s.charAt(i);
        if (curr == prev) {
          ++count;
        } else {
          sb.append(prev).append(count);
          prev = curr;
          count = 1;
        }
      }

      sb.append(prev).append(count);

      if (sb.length() >= s.length()) {
        return s;
      }
      return sb.toString();
    }
  }

  @AllArgsConstructor
  private static class Case {
    private String string;
    private String result;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c =
        aCase -> {
          log.info(aCase.string);
          String compressed = s.compress(aCase.string);
          log.info(compressed);
          log.info();
          Assert.assertEquals(compressed, aCase.result);
        };

    c.accept(new Case("aabcccccaaa", "a2b1c5a3"));
    c.accept(new Case("aabbcc", "aabbcc"));
  }
}
