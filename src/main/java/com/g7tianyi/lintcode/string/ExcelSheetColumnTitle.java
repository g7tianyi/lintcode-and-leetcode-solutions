package com.g7tianyi.lintcode.string;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 26, 2019
 *
 * @link https://www.lintcode.com/problem/excel-sheet-column-title/description
 */
public class ExcelSheetColumnTitle {

  private static final Logger log = new Logger();

  public class Solution {

    public String convertToTitle(int num) {

      StringBuilder sb = new StringBuilder();

      long mod = 26, rem; // 要用long，神坑啊...
      char ch;
      while (num != 0) {
        rem = num % mod;

        if (rem == 0) {
          rem = mod;
          ch = 'Z';
        } else {
          ch = (char) (65 + rem / (mod / 26) - 1); // 65 即 'A'
        }

        sb.append(ch);

        num -= rem;
        mod *= 26;

        log.info("%d %d %d", num, rem, mod / 26);
      }

      sb.reverse();

      return sb.toString();
    }
  }

  @AllArgsConstructor
  private static class Input {

    private int num;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Input> c = input -> log.info("[%d] => [%s]", input.num, s.convertToTitle(input.num));

    c.accept(new Input(1));
    c.accept(new Input(26));
    c.accept(new Input(28));
    c.accept(new Input(29));
    c.accept(new Input(52));
    c.accept(new Input(25 * 26 + 26));
    c.accept(new Input(26 * 26 + 26));
    c.accept(new Input(3 * 26 * 26 * 26 + 8 * 26 * 26 + 12));
    c.accept(new Input(10086));
    c.accept(new Input(1000000000));
  }
}
