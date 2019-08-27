package com.g7tianyi.lintcode.math;

import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 28, 2019
 *
 * @link https://www.lintcode.com/problem/hash-function/description
 */
public class HashFunction {

  public class Solution {

    // 中国剩余定理，同余定理
    // (A * B ) % C = ((A % C) * (B % C)) % C
    public int hashCode(char[] chars, int size) {
      long total = 0, pow = 1, part;
      for (int i = chars.length - 1; i >= 0; i--) {
        part = ((int) chars[i]) * pow;
        part %= size; // 防止溢出
        total += part;
        pow = (pow * 33) % size; // 防止溢出
      }
      return (int) (total % size);
    }
  }

  @AllArgsConstructor
  public static class Case {

    private char[] chars;

    private int size;

    private int expected;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c =
        aCase -> Assert.assertEquals(aCase.expected, s.hashCode(aCase.chars, aCase.size));

    c.accept(new Case("abcd".toCharArray(), 1000, 978));
    c.accept(new Case("abcd".toCharArray(), 100, 78));
    c.accept(new Case("abcdefghijklmnopqrstuvwxyz".toCharArray(), 2607, 1673));
  }
}
