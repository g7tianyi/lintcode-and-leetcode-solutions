package com.g7tianyi.lintcode.string;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 26, 2019
 *
 * @link https://www.lintcode.com/problem/reverse-words-in-a-string-iii/description
 */
public class ReverseString3 {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public String reverseWords(String s) {

      char[] chars = s.toCharArray();

      int i = 0;
      while (i < chars.length && chars[i] == ' ') {
        i++;
      }

      int j = i + 1;
      while (j < chars.length) {
        while (j < chars.length && chars[j] != ' ') {
          j++;
        }
        if (j <= chars.length) {
          reverseBetween(chars, i, j - 1);
        }

        i = j;
        while (i < chars.length && chars[i] == ' ') {
          i++;
        }
        j = i +1;
      }

      return new String(chars);
    }

    private void reverseBetween(char[] chars, int i, int j) {
      char ch;
      while (i < j) {
        ch = chars[i];
        chars[i] = chars[j];
        chars[j] = ch;

        ++i;
        --j;
      }
    }
  }

  @AllArgsConstructor
  private static class Input {

    private String s;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Input> c = input -> log.info("[%s] => [%s]", input.s, s.reverseWords(input.s));

    c.accept(new Input("hello"));
    c.accept(new Input("hello world"));
    c.accept(new Input(" hello world"));
    c.accept(new Input(" hello    world"));
    c.accept(new Input(" hello    world"));
    c.accept(new Input(" hello    world  "));
    c.accept(new Input("s'teL ekat edoCteeL tsetnoc"));
  }
}
