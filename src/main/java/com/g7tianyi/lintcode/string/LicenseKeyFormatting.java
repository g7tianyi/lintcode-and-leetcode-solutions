package com.g7tianyi.lintcode.string;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 03, 2019
 *
 * @link https://www.lintcode.com/problem/license-key-formatting/description
 */
public class LicenseKeyFormatting {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public String licenseKeyFormatting(String S, int K) {

      S = S.replaceAll("-", "").toUpperCase();

      StringBuilder sb = new StringBuilder();
      for (int i = S.length() - 1, j = 0; i >= 0; --i) {
        sb.append(S.charAt(i));
        ++j;
        if (j == K) {
          sb.append('-');
          j = 0;
        }
      }

      if (sb.length() > 0 && sb.charAt(sb.length() - 1) == '-') {
        sb.deleteCharAt(sb.length() - 1);
      }

      sb.reverse();

      return sb.toString();
    }
  }

  @AllArgsConstructor
  private class Case {
    String S;
    int K;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c = aCase -> log.info(s.licenseKeyFormatting(aCase.S, aCase.K));

    c.accept(new Case("5F3Z-2e-9-w", 4));
    c.accept(new Case("2-5g-3-J", 2));
  }
}
