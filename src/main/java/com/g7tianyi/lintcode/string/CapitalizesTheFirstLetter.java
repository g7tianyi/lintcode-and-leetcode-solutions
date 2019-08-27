package com.g7tianyi.lintcode.string;

import com.g7tianyi.lintcode.util.Log;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 25, 2019
 *
 * @link https://www.lintcode.com/problem/capitalizes-the-first-letter/description
 */
public class CapitalizesTheFirstLetter {

  private static final Log log = new Log();

  public class Solution {

    public String capitalizesFirst(String s) {

      StringBuilder sb = new StringBuilder();
      boolean isPrevSpace = true, isCurrSpace;
      int len = s.length();
      char ch;
      for (int i = 0; i < len; i++) {
        ch = s.charAt(i);
        isCurrSpace = ch == ' ';
        if (!isCurrSpace && isPrevSpace) {
          sb.append(Character.toUpperCase(ch));
        } else {
          sb.append(ch);
        }
        isPrevSpace = isCurrSpace;
      }

      return sb.toString();
    }
  }

  @AllArgsConstructor
  private static class Input {

    private String s;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Input> c = input -> log.info("[%s] => [%s]", input.s, s.capitalizesFirst(input.s));

    c.accept(new Input("i want to get an accepted"));
    c.accept(new Input("i jidls    mdijf  i  lsidj  i p l   "));
  }
}
