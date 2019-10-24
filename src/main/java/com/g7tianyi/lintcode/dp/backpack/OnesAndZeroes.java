package com.g7tianyi.lintcode.dp.backpack;

import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Oct 24, 2019
 *
 * @link https://www.lintcode.com/problem/ones-and-zeroes/description
 */
public class OnesAndZeroes {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int findMaxForm(String[] ss, int m, int n) {
      int[] Z = new int[ss.length];
      int[] O = new int[ss.length];
      for (int i = 0; i < ss.length; ++i) {
        for (int j = 0; j < ss[i].length(); ++j) {
          if (ss[i].charAt(j) == '0') {
            ++Z[i];
          }
        }
        O[i] = ss[i].length() - Z[i];
      }

      // F(l, i, j)表示前l个字符串中，花费i个0与j个1所能获得的最大价值
      // F(l, i, j) = MAX {
      //     F(l-1, i, j)
      //     F(l-1, i - Z(l), j - O(l)) + 1
      // }
      int[][][] F = new int[2][m + 1][n + 1];
      for (int i = 0; i < ss.length; ++i) {
        if (Z[i] <= m && O[i] <= n) {
          F[0][Z[i]][O[i]] = Math.max(F[0][Z[i]][O[i]], 1);
        }
      }

      int curr = 1, prev = 0;
      for (int l = 0; l < ss.length; ++l) {
        for (int i = 0; i <= m; ++i) {
          for (int j = 0; j <= n; ++j) {
            if (i < Z[l] || j < O[l] || Z[l] > m || O[l] > n) {
              continue;
            }
            F[curr][i][j] = Math.max(F[prev][i][j], F[prev][i - Z[l]][j - O[l]] + 1);
          }
        }
        curr ^= 1;
        prev ^= 1;
      }

      return F[prev][m][n];
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.findMaxForm(new String[] {"10", "0001", "111001", "1", "0"}, 5, 3));
    log.info(s.findMaxForm(new String[] {"10", "0001", "111001", "1", "0"}, 7, 7));
  }
}
