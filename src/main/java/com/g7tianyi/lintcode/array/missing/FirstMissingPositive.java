package com.g7tianyi.lintcode.array.missing;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Oct 05, 2019
 *
 * @link https://www.lintcode.com/problem/first-missing-positive/description
 */
public class FirstMissingPositive {

  private static final Logger log = Logger.getInstance();

  public class Solution {
    // 只允许时间复杂度O(n)的算法，并且只能使用常数级别的空间。
    public int firstMissingPositive(int[] A) {
      for (int i = 0; i < A.length; ++i) {
        if (A[i] <= 0) {
          A[i] = A.length + 1;
        }
      }

      for (int i = 0, j; i < A.length; ++i) {
        j = Math.abs(A[i]); // (*)
        if (j <= A.length) {
          // 通过标记为负数表示存在，但是原始数值需要保留
          // 在(*)行标记通过取绝对值获取原始数值
          A[j - 1] = -Math.abs(A[j - 1]);
        }
      }

      for (int i = 0; i < A.length; ++i) {
        if (A[i] > 0) {
          return i + 1;
        }
      }
      return A.length + 1;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.firstMissingPositive(Arrays.from(3, 4, -1, 1)));
    log.info(s.firstMissingPositive(Arrays.from(1, 2, 0)));
    log.info(s.firstMissingPositive(Arrays.from(1)));
  }
}
