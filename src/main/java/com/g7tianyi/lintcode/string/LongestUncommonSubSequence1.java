package com.g7tianyi.lintcode.string;

import com.g7tianyi.util.Logger;

/**
 * Created by g7tianyi on Sep 04, 2019
 *
 * @link https://www.lintcode.com/problem/longest-uncommon-subsequence-i/description
 */
public class LongestUncommonSubSequence1 {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int findLUSlength(String a, String b) {
      return a.equals(b) ? -1 : Math.max(a.length(), b.length());
    }
  }
}
