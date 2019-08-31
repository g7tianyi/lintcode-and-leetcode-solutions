package com.g7tianyi.lintcode.others;

import com.g7tianyi.util.Logger;

/**
 * Created by g7tianyi on Sep 1, 2019
 *
 * @link https://www.lintcode.com/problem/max-of-3-numbers/description
 */
public class MaxOf3Numbers {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int maxOfThreeNumbers(int num1, int num2, int num3) {
      return Math.max(Math.max(num1, num2), num3);
    }
  }
}
