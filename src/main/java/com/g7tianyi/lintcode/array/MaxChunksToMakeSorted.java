package com.g7tianyi.lintcode.array;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Oct 18, 2019
 *
 * @link https://www.lintcode.com/problem/max-chunks-to-make-sorted/description
 */
public class MaxChunksToMakeSorted {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    // 每个数应该在的位置就是它本身的值
    // i作为下标往前走，每次更新j
    // 当i走到j的时候就分成一块
    public int maxChunksToSorted(int[] arr) {

      int result = 0;
      if (arr == null || arr.length == 0) {
        return result;
      }

      for (int i = 0, j = arr[0]; i < arr.length; ++i) {
        if (arr[i] > j) {
          j = arr[i];
        } else if (i == j) {
          ++j;
          ++result;
        }
      }
      return result;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.maxChunksToSorted(Arrays.from(1, 0, 2, 3, 4)));
    log.info(s.maxChunksToSorted(Arrays.from(4, 3, 2, 1, 0)));
    log.info(s.maxChunksToSorted(Arrays.from(0, 1, 2, 3, 4)));
    log.info(s.maxChunksToSorted(Arrays.from(0, 1, 3, 2)));
    log.info(s.maxChunksToSorted(Arrays.from(1, 3, 2, 0, 4, 5)));
  }
}
