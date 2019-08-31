package com.g7tianyi.lintcode.greedy;

import com.g7tianyi.util.Logger;

/**
 * Created by g7tianyi on Aug 31, 2019
 *
 * @link https://www.lintcode.com/problem/nim-game/description
 */
public class NimGame {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    // 只要不是4的倍数就行
    public boolean canWinNim(int n) {
      return (n & 3) != 0;
    }
  }
}
