package com.g7tianyi.lintcode.string;

import com.g7tianyi.lintcode.util.Console;
import com.g7tianyi.lintcode.util.Log;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 26, 2019
 *
 * @link https://www.lintcode.com/problem/flip-game/description
 */
public class FlipGame {

  private static final Log log = new Log();

  public class Solution {

    public List<String> generatePossibleNextMoves(String s) {

      List<String> result = new ArrayList<>();

      if (s.length() < 2) {
        return result;
      }

      char prev = s.charAt(0), curr;
      for (int i = 1; i < s.length(); i++) {
        curr = s.charAt(i);
        if (prev == '+' && curr == '+') {
          StringBuilder sb = new StringBuilder(s);
          sb.setCharAt(i - 1, '-');
          sb.setCharAt(i, '-');
          result.add(sb.toString());
        }
        prev = curr;
      }

      return result;
    }
  }

  @AllArgsConstructor
  private static class Input {

    private String s;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Input> c =
        input -> {
          String ret = Console.stringify(s.generatePossibleNextMoves(input.s));
          log.info("[%s] => [%s]", input.s, ret);
        };

    c.accept(new Input("++"));
    c.accept(new Input("--"));
    c.accept(new Input("++++"));
    c.accept(new Input("---+++-+++-+"));
  }
}
