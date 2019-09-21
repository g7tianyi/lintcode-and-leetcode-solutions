package com.g7tianyi.lintcode.dp;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.*;

/**
 * Created by g7tianyi on Sep 21, 2019
 *
 * @link https://www.lintcode.com/problem/k-sum/description
 */
public class KSum {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    // F[K, S, T]表示从下标S开始的所有数字里，用K个数字得到T一共有多少种可能
    // F[K, S, T] = Sum(F(K-1, i+1, T-Vi))
    public int kSum(int[] values, int K, int T) {

      if (K == 0 && T == 0) {
        return 1;
      }

      int len = values.length;
      if (len == 0 || K > len) {
        return 0;
      }

      Arrays.sort(values);

      // 轮转数组，第一维表示选择k个数字，第二维表示从下标s开始一直到最末
      // 最后的HashMap的Key表示k个数字组合起来的和(summary)，即T；Value表示可能性
      List<List<Map<Integer, Integer>>> F = new ArrayList<>();
      F.add(new ArrayList<>());
      F.add(new ArrayList<>());
      for (int i = 0; i < len; ++i) {
        Map<Integer, Integer> m = new HashMap<>();
        for (int j = i; j < len; ++j) {
          m.put(values[j], 1);
        }
        F.get(0).add(m);
        F.get(1).add(new HashMap<>());
      }

      int curr = 1, prev = 0;
      int minT = values[0];

      List<Map<Integer, Integer>> lCurr, lPrev;
      for (int k = 2; k <= K; ++k) {
        lCurr = F.get(curr);
        for (Map<Integer, Integer> m : lCurr) {
          m.clear();
        }
        lPrev = F.get(prev);
        minT += values[k - 1];

        for (int t = minT; t <= T; ++t) { // t: target
          int sum = 0;
          for (int s = len - k, remain; s >= 0; --s) {
            remain = t - values[s];
            sum += lPrev.get(s + 1).getOrDefault(remain, 0);
            lCurr.get(s).put(t, sum);
          }
        }

        curr ^= 1;
        prev ^= 1;
      }

      return F.get(prev).get(0).getOrDefault(T, 0);
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    log.info(s.kSum(com.g7tianyi.common.Arrays.from(1, 2, 5, 8, 11, 13), 3, 32));

    log.info(s.kSum(com.g7tianyi.common.Arrays.from(1, 2, 3, 4, 5), 3, 6));
    log.info(s.kSum(com.g7tianyi.common.Arrays.from(1, 2, 3, 4, 5), 2, 5));
    log.info(s.kSum(com.g7tianyi.common.Arrays.from(1, 2, 3, 4), 2, 5));
    log.info(s.kSum(com.g7tianyi.common.Arrays.from(1), 1, 5));
    log.info(s.kSum(com.g7tianyi.common.Arrays.from(1), 1, 1));
    log.info(s.kSum(new int[] {}, 0, 1));
    log.info(s.kSum(new int[] {}, 0, 0));
  }
}
