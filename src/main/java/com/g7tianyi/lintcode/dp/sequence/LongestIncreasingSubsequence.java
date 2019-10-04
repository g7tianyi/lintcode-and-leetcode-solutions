package com.g7tianyi.lintcode.dp.sequence;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Oct 04, 2019
 *
 * @link https://www.lintcode.com/problem/longest-increasing-subsequence/description
 */
public class LongestIncreasingSubsequence {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int longestIncreasingSubsequence(int[] values) {

      if (values == null || values.length == 0) {
        return 0;
      }

      int[] F = new int[values.length]; // 可以用最大堆为维护F，这样就可以下降到O(N*logN)了
      // F[i]表示以values[i]为结尾的最长上升子序列的长度是多少
      for (int i = 0; i < values.length; ++i) {
        F[i] = 1;
        for (int j = 0; j < i; ++j) {
          if (values[j] < values[i]) {
            F[i] = Math.max(F[j] + 1, F[i]);
          }
        }
      }

      int result = F[0];
      for (int i = 1; i < values.length; ++i) {
        result = Math.max(result, F[i]);
      }
      return result;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.longestIncreasingSubsequence(Arrays.from(5, 4, 1, 2, 3)));
    log.info(s.longestIncreasingSubsequence(Arrays.from(4, 2, 4, 5, 3, 7)));
  }
}
