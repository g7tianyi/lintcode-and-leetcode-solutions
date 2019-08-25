package com.g7tianyi.lintcode.string;

import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 25, 2019
 *
 * <p>Problem link: https://www.lintcode.com/problem/to-lower-case/description
 */
public class ToLowerCase {

  public class Solution {

    public String toLowerCase(String str) {
      char[] arr = str.toCharArray();
      char ch;
      for (int i = 0; i < arr.length; i++) {
        ch = arr[i];
        if (ch >= 'A' && ch <= 'Z') {
          arr[i] = (char) (ch + 32);
        }
      }
      return new String(arr);
    }
  }

  @AllArgsConstructor
  private static class Input {

    private String s;
    private String expected;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Input> c = input -> Assert.assertEquals(input.expected, s.toLowerCase(input.s));

    c.accept(new Input("Hello", "hello"));
    c.accept(new Input("here", "here"));
    c.accept(new Input("LOVELY", "lovely"));
  }
}
