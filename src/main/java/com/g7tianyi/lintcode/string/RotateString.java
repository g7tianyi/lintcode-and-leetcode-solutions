package com.g7tianyi.lintcode.string;

import com.g7tianyi.common.Strings;
import com.g7tianyi.lintcode.array.mutation.RotateArray;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 03, 2019
 *
 * @link https://www.lintcode.com/problem/rotate-string/note/193895
 */
public class RotateString {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    /** @see RotateArray ; */
    public void rotateString(char[] str, int offset) {
      if (offset < 0 || str.length == 0) {
        return;
      }

      offset %= str.length;

      char[] temp = new char[offset];
      System.arraycopy(str, str.length - offset, temp, 0, offset);
      System.arraycopy(str, 0, str, offset, str.length - offset);
      System.arraycopy(temp, 0, str, 0, offset);
    }
  }

  @AllArgsConstructor
  private class Case {
    char[] str;
    int offset;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c =
        aCase -> {
          log.info("%s", Strings.format(aCase.str));
          log.info("%d", aCase.offset);
          s.rotateString(aCase.str, aCase.offset);
          log.info("%s\n", Strings.format(aCase.str));
        };

    c.accept(new Case("".toCharArray(), 10));
    c.accept(new Case("abcdefg".toCharArray(), 3));
    c.accept(new Case("abcdefg".toCharArray(), 0));
    c.accept(new Case("abcdefg".toCharArray(), 1));
    c.accept(new Case("abcdefg".toCharArray(), 2));
    c.accept(new Case("abcdefg".toCharArray(), 10));
  }
}
