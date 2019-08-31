package com.g7tianyi.lintcode.others;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 31, 2019
 *
 * @link https://www.lintcode.com/problem/guess-number-higher-or-lower/description
 */
public class GuessNumberHigherOrLower {

  private static final Logger log = Logger.getInstance();

  public class GuessGame {

    public int guess(int num) {
      return 0;
    }
  }

  public class Solution extends GuessGame {

    public int guessNumber(int n) {
      int former = 1, latter = n, middle, lottery;
      while (former <= latter) {
        middle = former + ((latter - former) >> 1);
        lottery = guess(middle);
        if (lottery == 0) {
          return middle;
        }

        if (lottery > 0) {
          former = middle + 1;
        } else {
          latter = middle - 1;
        }
      }
      return -1;
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
          log.info("%d => %s", input.num, s.guessNumber(input.num));
          log.info();
        };

    runner.accept(new Input(2019));
    runner.accept(new Input(2020));
  }
}
