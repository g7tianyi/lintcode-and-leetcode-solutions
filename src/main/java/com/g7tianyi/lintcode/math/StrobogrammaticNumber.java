package com.g7tianyi.lintcode.math;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 02, 2019
 *
 * @link https://www.lintcode.com/problem/strobogrammatic-number/description
 */
public class StrobogrammaticNumber {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public boolean isStrobogrammatic(String num) {

      Map<Character, Character> map = new HashMap<>();
      map.put('0', '0');
      map.put('1', '1');
      map.put('6', '9');
      map.put('8', '8');
      map.put('9', '6');

      int i = 0, j = num.length() - 1;
      while (i <= j) {
        char s = num.charAt(i), e = num.charAt(j);
        if (map.getOrDefault(s, '#') != e) {
          return false;
        }

        ++i;
        --j;
      }
      return true;
    }
  }

  @AllArgsConstructor
  private class Case {
    String num;
    boolean expect;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c =
        aCase -> {
          boolean result = s.isStrobogrammatic(aCase.num);
          log.info("%s => %s", aCase.num, result);
          Assert.assertEquals(aCase.expect, result);
        };

    c.accept(new Case("1", true));
    c.accept(new Case("2", false));
    c.accept(new Case("69", true));
    c.accept(new Case("688", false));
    c.accept(new Case("689", true));
  }
}
