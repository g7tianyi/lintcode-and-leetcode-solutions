package com.g7tianyi.lintcode.array;

import com.g7tianyi.common.Strings;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 03, 2019
 *
 * @link https://www.lintcode.com/problem/di-string-match/description
 */
public class DIStringMatch {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int[] diStringMatch(String S) {

      int[] result = new int[S.length() + 1];
      for (int i = 0; i < result.length; ++i) {
        result[i] = i;
      }

      int temp, swap;
      do {
        swap = 0;
        for (int i = 0; i < result.length - 1; ++i) {
          if ((S.charAt(i) == 'I' && result[i] > result[i + 1])
              || (S.charAt(i) == 'D' && result[i] < result[i + 1])) {
            temp = result[i];
            result[i] = result[i + 1];
            result[i + 1] = temp;

            ++swap;
          }
        }
      } while (swap != 0);

      return result;
    }
  }

  @AllArgsConstructor
  private class Case {
    String S;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c =
        aCase -> {
          log.info(aCase.S);
          log.info(Strings.format(s.diStringMatch(aCase.S)));
          log.info();
        };

    c.accept(new Case("IDID"));
    c.accept(new Case("DIID"));
    c.accept(new Case("III"));
    c.accept(new Case("DDDDD"));
  }
}
