package com.g7tianyi.lintcode.dp.sequence;

import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Oct 04, 2019
 *
 * @link https://www.lintcode.com/problem/longest-common-subsequence/description
 */
public class LongestCommonSubsequence {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int longestCommonSubsequence(String A, String B) {

      if (A == null || B == null || A.length() == 0 || B.length() == 0) {
        return 0;
      }

      // F(i, j)表示A的前i个字符与B的前j个字符的最长公共子序列的长度
      // F(i, j) = MAX( F(i-1, j-1) + 1 if A[i] == B[j],
      //                F(i-1, j),
      //                F(i, j-1))
      int[][] F = new int[A.length()][B.length()];
      for (int i = 0; i < A.length(); ++i) {
        for (int j = 0; j < B.length(); ++j) {
          F[i][j] = Math.max(i > 0 ? F[i - 1][j] : 0, j > 0 ? F[i][j - 1] : 0);
          if (A.charAt(i) == B.charAt(j)) {
            F[i][j] = Math.max(F[i][j], i > 0 && j > 0 ? F[i - 1][j - 1] + 1 : 1);
          }
        }
      }
      return F[A.length() - 1][B.length() - 1];
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.longestCommonSubsequence("A", "B"));
    log.info(s.longestCommonSubsequence("AAA", "ABA"));
    log.info(s.longestCommonSubsequence("ABCD", "EDCA"));
    log.info(s.longestCommonSubsequence("ABCD", "EACB"));
  }
}
