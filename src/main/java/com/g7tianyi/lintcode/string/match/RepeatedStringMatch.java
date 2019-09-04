package com.g7tianyi.lintcode.string.match;

import com.g7tianyi.util.Logger;

/**
 * Created by g7tianyi on Sep 04, 2019
 *
 * @link https://www.lintcode.com/problem/repeated-string-match/description
 */
public class RepeatedStringMatch {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int repeatedStringMatch(String A, String B) {
      int len2 = B.length(), result = 1;
      StringBuilder sb = new StringBuilder(A);
      while (sb.length() < len2) {
        sb.append(A);
        ++result;
      }
      if (sb.toString().contains(B)) {
        return result;
      }
      sb.append(A);
      return sb.toString().contains(B) ? result + 1 : -1;
    }
  }
}
