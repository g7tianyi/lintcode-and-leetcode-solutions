package com.g7tianyi.lintcode.string;

import com.g7tianyi.lintcode.util.Log;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 26, 2019
 *
 * @link https://www.lintcode.com/problem/reverse-string/description
 */
public class ReverseString {

  private static final Log log = new Log();

  public class Solution {

    public String reverseString(String s) {
      char[] chars = s.toCharArray();
      int i = 0, j = chars.length -1;
      char ch;
      while (i < j) {
        ch = chars[i];
        chars[i] = chars[j];
        chars[j] = ch;

        ++i;
        --j;
      }
      return new String(chars);
    }
  }

  @AllArgsConstructor
  private static class Input {

    private String s;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Input> c = input -> log.info("[%s] => [%s]", input.s, s.reverseString(input.s));

    c.accept(new Input("hello"));
    c.accept(new Input("hello world"));
  }
}
