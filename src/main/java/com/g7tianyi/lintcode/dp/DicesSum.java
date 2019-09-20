package com.g7tianyi.lintcode.dp;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.*;

/**
 * Created by g7tianyi on Sep 20, 2019
 *
 * @link https://www.lintcode.com/problem/dices-sum/description
 */
public class DicesSum {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    // F(i, j)，表示用i个筛子摇出数字j的可能性有多少种
    // F(i, j) = F(i-1, j-1) + F(i-1, j-2) + ... + F(i-1, j-6)
    // 相当于，第一个筛子摇出了1, 2, ..., 6，但需要注意i可能小于6的情况
    public List<Map.Entry<Integer, Double>> dicesSum(int n) {

      // 下面是为了节省内存而使用轮转数组的实现
      long[][] F = new long[2][n * 6 + 1];
      for (int i = 1; i <= 6; ++i) {
        F[0][i] = 1;
      }

      int prev = 0, curr = 1;
      for (int i = 2; i <= n; ++i) { // i个筛子
        Arrays.fill(F[curr], 0);
        for (int j = i; j <= i * 6; ++j) { // 摇出数字j
          for (int k = 1; k <= 6 && k < j; ++k) { // 先摇出 1, 2, ..., 6
            F[curr][j] += F[prev][j - k];
          }
        }
        curr ^= 1;
        prev ^= 1;
      }

      long total = 1;
      for (int i = 0; i < n; ++i) {
        total *= 6;
      }

      List<Map.Entry<Integer, Double>> result = new ArrayList<>();
      for (int value = n; value <= n * 6; ++value) {
        double probability = ((double) F[prev][value]) / ((double) total);
        Map.Entry<Integer, Double> entry = new AbstractMap.SimpleEntry<>(value, probability);
        result.add(entry);
      }
      return result;
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    log.info(s.dicesSum(15));
  }
}
