package com.g7tianyi.lintcode.dp;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Oct 04, 2019
 *
 * @link https://www.lintcode.com/problem/jump-game-ii/description
 */
public class JumpGame2 {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int jump(int[] A) {

      if (A == null || A.length == 0) {
        return 0;
      }

      int[] F = new int[A.length];
      for (int i = 0; i < A.length; ++i) {
        for (int j = 1; j <= A[i] && i + j < A.length; ++j) {
          if (F[i + j] == 0 || F[i + j] > F[i] + 1) {
            F[i + j] = F[i] + 1;
          }
        }
      }
      return F[A.length - 1];
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.jump(Arrays.from(2, 3, 1, 1, 4)));
  }
}
