package com.g7tianyi.lintcode.array.subsequence;

import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Oct 05, 2019
 *
 * @link https://www.lintcode.com/problem/minimum-size-subarray-sum/description
 */
public class MinimumSizeSubarraySum {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    // 一开始以为子数组是不需要连续的，死也想不出来线性算法，于是就先排个序按贪心策略来解题
    // 结果看到个测试用例WA，仔细研究测试数据才发现原来必须连续，郁闷...
    // 然后就简单了，双指针问题，当两个指针内的区间和满足条件时，模拟队列行为，
    // 将前面的指针不停往后移动，尝试寻找更短的区间
    public int minimumSize(int[] values, int S) {

      if (values == null || values.length == 0) {
        return -1;
      }

      int L = values.length + 1;
      int i = 0, j, s;
      while (i < values.length) {

        // 下面的代码，有模拟队列行为的既视感呢~
        j = i;
        s = 0;
        while (j < values.length) {
          s += values[j];
          if (s >= S) {
            L = Math.min(L, j - i + 1);
            break;
          } else {
            ++j;
          }
        }

        while (i < j && s - values[i] >= S) {
          s -= values[i++];
          L = Math.min(L, j - i + 1);
        }

        ++i;
      }

      return L > values.length ? -1 : L;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.minimumSize(com.g7tianyi.common.Arrays.from(1, 1, 2, 3, 1, 5), 5));
    log.info(s.minimumSize(com.g7tianyi.common.Arrays.from(2, 3, 1, 2, 4, 3), 7));
    log.info(s.minimumSize(com.g7tianyi.common.Arrays.from(1, 2, 3, 4, 5), 100));
  }
}
