package com.g7tianyi.lintcode.string;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 26, 2019
 *
 * @link https://www.lintcode.com/problem/reverse-string-ii/description
 */
public class ReverseString2 {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public String reverseStringII(String s, int k) {
      // Write your code here.

      StringBuilder sb = new StringBuilder(s);

      int len = s.length(), i = 0, j;
      while (i < len) {
        j = i + (k << 1) - 1;
        if (j < len) {
          reverseString(sb, i, i + k - 1);

        } else {
          if (j - k < len) {
            reverseString(sb, i, i + k - 1);
          } else {
            j = len - 1;
            reverseString(sb, i, j);
          }
        }
        i = j + 1;
      }

      return sb.toString();
    }

    private void reverseString(StringBuilder sb, int i, int j) {
      char ch;
      while (i < j) {
        ch = sb.charAt(i);
        sb.setCharAt(i, sb.charAt(j));
        sb.setCharAt(j, ch);
        ++i;
        --j;
      }
    }
  }

  @AllArgsConstructor
  private static class Input {

    private String s;
    private int k;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Input> c =
        input -> log.info("%d [%s] => [%s]", input.k, input.s, s.reverseStringII(input.s, input.k));

    c.accept(new Input("abcdefg", 2));
    c.accept(new Input("abcdef", 2));
    c.accept(new Input("ace", 4));
    c.accept(new Input("wfwpzbcdttmlblgyxczvqgcysrslsshhnquuxezknnkzfmnrcirtdbylfi", 10));
  }
}
