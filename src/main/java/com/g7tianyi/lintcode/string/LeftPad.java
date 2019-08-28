package com.g7tianyi.lintcode.string;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 25, 2019
 *
 * @link https://www.lintcode.com/problem/left-pad/description
 */
public class LeftPad {

  private static final Logger log = new Logger();

  public static class Solution {

    public static String leftPad(String originalStr, int size) {
      return leftPad(originalStr, size, ' ');
    }

    public static String leftPad(String originalStr, int size, char padChar) {
      // write your code here

      StringBuilder sb = new StringBuilder();
      sb.append(originalStr);
      sb.reverse();

      int len = size - sb.length();
      while (len > 0) {
        sb.append(padChar);
        len--;
      }

      sb.reverse();
      return sb.toString();
    }
  }

  @AllArgsConstructor
  private static class Input {

    private String s;

    private int size;

    private char padChar;
  }

  @Test
  public void test() {

    Consumer<Input> c =
        input ->
            log.info("[%s] => [%s]", input.s, Solution.leftPad(input.s, input.size, input.padChar));

    c.accept(new Input("foo", 5, ' '));
    c.accept(new Input("foobar", 6, ' '));
    c.accept(new Input("1", 2, '0'));
  }
}
