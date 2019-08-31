package com.g7tianyi.lintcode.math;

import com.g7tianyi.util.Logger;

/**
 * Created by g7tianyi on Aug 31, 2019
 *
 * @link https://www.lintcode.com/problem/lucky-number-eight/description
 */
public class LuckyNumberEight {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int luckyNumber(int n) {
      int result = 0;
      for (int i = 1, j; i <= n; ++i) {
        j = i;
        while (j != 0) {
          if (j % 10 == 8) {
            ++result;
            break;
          }
          j /= 10;
        }
      }
      return result;
    }
  }
}
