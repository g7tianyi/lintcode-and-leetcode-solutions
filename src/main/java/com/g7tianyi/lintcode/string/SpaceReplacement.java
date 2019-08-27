package com.g7tianyi.lintcode.string;

import com.g7tianyi.lintcode.util.Log;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 25, 2019
 *
 * @link https://www.lintcode.com/problem/space-replacement/description
 */
public class SpaceReplacement {

  private static final Log log = new Log();

  public class Solution {

    public int replaceBlank(char[] string, int length) {
      int spaces = 0;
      for (int i = 0; i < length; i++) {
        if (string[i] == ' ') {
          spaces++;
        }
      }
      int result = length + (spaces << 1);

      int j = result - 1;
      for (int i = length - 1; i >= 0; i--) {
        if (string[i] != ' ') {
          string[j--] = string[i];
        } else {
          string[j--] = '0';
          string[j--] = '2';
          string[j--] = '%';
        }
      }

      return result;
    }
  }

  @AllArgsConstructor
  private static class Input {

    private char[] string;
    private int length;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Input> c =
        input -> {
          int result = s.replaceBlank(input.string, input.length);
          log.info("%d, %s", result, String.valueOf(input.string));
        };

    c.accept(
        new Input(
            new char[] {
              'M', 'r', ' ', 'J', 'o', 'h', 'n', ' ', 'S', 'm', 'i', 't', 'h', '@', '@', '@', '@'
            },
            13));
  }
}
