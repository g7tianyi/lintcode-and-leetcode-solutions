package com.g7tianyi.lintcode.bitops;

import com.g7tianyi.util.Logger;

/**
 * Created by g7tianyi on Sep 03, 2019
 *
 * @link https://www.lintcode.com/problem/reverse-bits/description
 */
public class ReverseBits {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public long reverseBits(long n) {
      long res = 0;
      for (int i = 0; i < 32; i++) {
        res = res << 1 ^ (n >>> i & 1);
      }
      return res;
    }
  }
}
