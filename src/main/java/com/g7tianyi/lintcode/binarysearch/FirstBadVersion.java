package com.g7tianyi.lintcode.binarysearch;

import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Oct 27, 2019
 *
 * @link https://www.lintcode.com/problem/first-bad-version/description
 */
public class FirstBadVersion {

  private static final Logger log = Logger.getInstance();

  public static class SVNRepo {
    public static boolean isBadVersion(int k) {
      return true;
    }
  }

  public class Solution {

    public int findFirstBadVersion(int n) {
      int i = 1, j = n, m;
      while (i <= j) {
        m = i + ((j - i) >> 1);
        if (SVNRepo.isBadVersion(m)) {
          if (m == 1 || !SVNRepo.isBadVersion(m - 1)) {
            return m;
          } else {
            j = m - 1;
          }
        } else {
          i = m + 1;
        }
      }
      return n;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {}
}
