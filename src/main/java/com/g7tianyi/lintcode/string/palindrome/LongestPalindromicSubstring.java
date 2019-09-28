package com.g7tianyi.lintcode.string.palindrome;

import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Sep 28, 2019
 *
 * @link https://www.lintcode.com/problem/longest-palindromic-substring/description
 */
public class LongestPalindromicSubstring {

  private static final Logger log = Logger.getInstance();

  // O(n)算法，枚举中心点，这种算法的均摊时间是O(n)，它退出第二层循环特别快
  public class Solution {

    class Range {
      int left, right;
    }

    public String longestPalindrome(String s) {
      int len = s.length();
      Range range = new Range();
      for (int mid = 0; mid < len; mid++) {
        check(s, mid, mid, range);
        check(s, mid, mid + 1, range);
      }
      return s.substring(range.left, range.right + 1);
    }

    public void check(String s, int left, int right, Range range) {
      int len = s.length();
      while (left <= right && left >= 0 && right < len && s.charAt(left) == s.charAt(right)) {
        ++right;
        --left;
      }

      ++left;
      --right;
      if (range.right - range.left + 1 < right - left + 1) {
        range.left = left;
        range.right = right;
      }
    }
  }

  // 动态规划解法，区间动态规划
  // 100% 数据通过测试总耗时 374 ms
  // 您的提交打败了 51.20% 的提交!
  public class DPSolution {

    public String longestPalindrome(String s) {
      if (s == null) {
        return s;
      }
      if (s.length() == 0) {
        return "";
      }

      int len = s.length();
      int[][] F = new int[len][len];
      int max = 1, mi = 0, mj = 0;
      for (int i = 0; i < len; ++i) {
        F[i][i] = 1;
        if (i + 1 < len && s.charAt(i) == s.charAt(i + 1)) {
          mi = i;
          mj = i + 1;
          F[i][i + 1] = 2;
        }
      }

      for (int l = 3; l <= len; ++l) {
        for (int i = 0; i + l <= len; ++i) {
          int j = i + l - 1;
          if (F[i + 1][j - 1] != 0 && s.charAt(i) == s.charAt(j)) {
            F[i][j] = F[i + 1][j - 1] + 2;
            if (max < F[i][j]) {
              max = F[i][j];
              mi = i;
              mj = j;
            }
          }
        }
      }

      return s.substring(mi, mj + 1);
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.longestPalindrome("abcdzdcab"));
    log.info(s.longestPalindrome("aba"));
  }
}
