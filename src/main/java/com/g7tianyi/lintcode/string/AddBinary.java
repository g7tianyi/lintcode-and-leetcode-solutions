package com.g7tianyi.lintcode.string;

import com.g7tianyi.common.Strings;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 26, 2019
 *
 * @link https://www.lintcode.com/problem/add-binary/description
 */
public class AddBinary {

  private static final Logger log = new Logger();

  public class Solution {

    public String addBinary(String a, String b) {

      int len1 = a.length(), len2 = b.length();

      StringBuilder sb = new StringBuilder();

      int i = len1 - 1, j = len2 - 1;
      int carry = 0, sum;
      char ch1, ch2;
      while (i >= 0 && j >= 0) {
        ch1 = a.charAt(i);
        ch2 = b.charAt(j);

        if (ch1 == '0' && ch2 == '0') {
          sum = carry;
          carry = 0;
        } else if (ch1 == '1' && ch2 == '1') {
          sum = carry;
          carry = 1;
        } else {
          sum = carry == 0 ? 1 : 0;
        }

        sb.append(sum);

        --i;
        --j;
      }

      while (i >= 0) {
        ch1 = a.charAt(i);
        if (ch1 == '0') {
          sum = carry;
          carry = 0;
        } else {
          sum = carry == 0 ? 1 : 0;
        }
        sb.append(sum);
        --i;
      }

      while (j >= 0) {
        ch2 = b.charAt(j);
        if (ch2 == '0') {
          sum = carry;
          carry = 0;
        } else {
          sum = carry == 0 ? 1 : 0;
        }
        sb.append(sum);
        --j;
      }

      if (carry == 1) {
        sb.append(carry);
      }

      sb.reverse();

      return sb.toString();
    }
  }

  @AllArgsConstructor
  private static class Input {

    private String a;
    private String b;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Input> c =
        input -> {
          String r = s.addBinary(input.a, input.b);
          int len = r.length();
          String line = Strings.from('-', len);
          log.info(Strings.prePad(input.a, len, ' '));
          log.info(Strings.prePad(input.b, len, ' '));
          log.info(Strings.prePad(line, len, ' '));
          log.info(Strings.prePad(r, len, ' '));
          log.info();
        };

    c.accept(new Input("11", "1"));
    c.accept(new Input("1001", "1"));
    c.accept(new Input("1001", "101"));
    c.accept(new Input("10", "10"));
    c.accept(new Input("0", "0"));
    c.accept(new Input("0", "1"));
  }
}
