package com.g7tianyi.lintcode.string;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 03, 2019
 *
 * @link https://www.lintcode.com/problem/count-and-say/description
 */
public class CountAndSay {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    // 1, 11, 21, 1211, 111221, ...
    public String countAndSay(int n) {

      String s = "1";

      if (n == 1) {
        return s;
      }

      StringBuilder sb;
      int i = 1;
      while (i < n) {
        sb = new StringBuilder();
        char prev = s.charAt(0), curr;
        int count = 1, j = 1;
        while (j < s.length()) {
          curr = s.charAt(j);
          if (curr == prev) {
            ++count;
          } else {
            sb.append(count).append(prev);
            prev = curr;
            count = 1;
          }
          ++j;
        }

        sb.append(count).append(prev);
        s = sb.toString();
        ++i;
      }

      return s;
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Integer> c = num -> log.info("%d => %s", num, s.countAndSay(num));

    for (int i = 1; i < 20; ++i) {
      c.accept(i);
    }
  }
}
