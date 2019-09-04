package com.g7tianyi.lintcode.string;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 04, 2019
 *
 * @link https://www.lintcode.com/problem/count-binary-substrings/description
 */
public class CountBinarySubstrings {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    // BORING QUESTION...
    // https://www.cnblogs.com/grandyang/p/7716150.html
    public int countBinarySubstrings(String s) {
      int result = 0, prev = 0, curr = 1, n = s.length();
      for (int i = 1; i < n; ++i) {
        if (s.charAt(i) == s.charAt(i - 1)) {
          ++curr;
        } else {
          prev = curr;
          curr = 1;
        }
        if (prev >= curr) {
          ++result;
        }
      }
      return result;
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<String> c =
        str -> {
          log.info(str);
          log.info(s.countBinarySubstrings(str));
          log.info();
        };

    c.accept("00110011");
    c.accept("10101");
  }
}
