package com.g7tianyi.lintcode.bitops;

import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Aug 30, 2019
 *
 * @link https://www.lintcode.com/problem/binary-number-with-alternating-bits/description
 */
public class BinaryNumberWithAlternatingBits {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public boolean hasAlternatingBits(int n) {
      n = n ^ (n >> 1); // 高位全部清0，低位全部变成1，注意一定要使用右移操作
      return (n & (n + 1)) == 0;
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    log.info("%s", s.hasAlternatingBits(4));
    log.info("%s", s.hasAlternatingBits(5));
    log.info("%s", s.hasAlternatingBits(7));
    log.info("%s", s.hasAlternatingBits(11));
    log.info("%s", s.hasAlternatingBits(10));
  }
}
