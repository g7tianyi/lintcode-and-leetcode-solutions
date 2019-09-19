package com.g7tianyi.lintcode.dp;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 19, 2019
 *
 * @link https://www.lintcode.com/problem/interleaving-string/description
 */
public class InterleavingString {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public boolean isInterleave(String s1, String s2, String s3) {
      int len1 = s1.length(), len2 = s2.length(), len3 = s3.length();
      if (len1 + len2 != len3) {
        return false;
      }
      if (len1 == 0) {
        return s3.equals(s2);
      }
      if (len2 == 0) {
        return s3.equals(s1);
      }

      // F[i, j] 表示，s1的前i个字符与s2的前j个字符，可以交叉成为s3的前i+j个字符
      boolean[][] F = new boolean[len1 + 1][len2 + 1];
      for (int i = 0; i <= len1; ++i) {
        F[i] = new boolean[len2 + 1];
        for (int j = 0; j <= len2; ++j) {
          F[i][j] = false;
        }
      }

      F[1][0] = s3.charAt(0) == s1.charAt(0);
      F[0][1] = s3.charAt(0) == s2.charAt(0);

      // 优化点：
      // 1. 当找不到一种方式可以交叉处前k个字符时，可以直接返回false
      // 2. 二维数组的空间开销可以优化，每次只需要知道前k-1个字符的组合方式即可

      char ch;
      for (int k = 1; k < len3; ++k) {
        ch = s3.charAt(k);
        for (int i = 0; i <= k; ++i) {
          if (i < len1 && k - i <= len2 && F[i][k - i] && s1.charAt(i) == ch) {
            F[i + 1][k - i] = true;
          }
          if (i < len2 && k - i <= len1 && F[k - i][i] && s2.charAt(i) == ch) {
            F[k - i][i + 1] = true;
          }
        }
      }

      return F[len1][len2];
    }
  }

  @AllArgsConstructor
  private static class Case {
    String s1;
    String s2;
    String s3;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c =
        aCase -> {
          log.info(aCase.s1);
          log.info(aCase.s2);
          log.info(aCase.s3);
          log.info(s.isInterleave(aCase.s1, aCase.s2, aCase.s3));
          log.info();
        };

    c.accept(new Case("", "", ""));
    c.accept(new Case("", "123", "ABC"));
    c.accept(new Case("aabcc", "dbbca", "aadbbcbcac"));
    c.accept(new Case("", "", "1"));
    c.accept(new Case("aabcc", "dbbca", "aadbbbaccc"));
  }
}
