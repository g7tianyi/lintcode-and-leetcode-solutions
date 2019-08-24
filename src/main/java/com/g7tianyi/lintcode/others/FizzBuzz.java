package com.g7tianyi.lintcode.others;

import com.g7tianyi.lintcode.util.Console;
import com.g7tianyi.lintcode.util.Log;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 23, 2019
 *
 * <p>Problem link:
 */
public class FizzBuzz {

  private static final Log log = new Log();

  public class Solution {

    public List<String> fizzBuzz(int n) {
      List<String> result = new ArrayList<>();
      for (int i = 1; i <= n; i++) {
        result.add(resolve(i));
      }
      return result;
    }

    private String resolve(int n) {
      boolean mod3 = n % 3 == 0;
      boolean mod5 = n % 5 == 0;
      if (mod3 && mod5) {
        return "fizz buzz";
      }
      if (mod3) {
        return "fizz";
      }
      if (mod5) {
        return "buzz";
      }
      return String.valueOf(n);
    }
  }

  @AllArgsConstructor
  public static class Input {

    private int num;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Input> runner =
        input -> {
          log.info(input.num + " " + Console.stringify(s.fizzBuzz(input.num)));
          log.info("");
        };

    runner.accept(new Input(15));
  }
}
