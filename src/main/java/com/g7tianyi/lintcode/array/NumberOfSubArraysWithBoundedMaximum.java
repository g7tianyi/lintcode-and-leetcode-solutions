package com.g7tianyi.lintcode.array;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.common.Strings;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Oct 02, 2019
 *
 * @link https://www.lintcode.com/problem/number-of-subarrays-with-bounded-maximum/description
 */
public class NumberOfSubArraysWithBoundedMaximum {

  private static final Logger log = Logger.getInstance();

  public class Solution {
    public int numSubarrayBoundedMax(int[] values, int L, int R) {
      int result = 0;
      int small = 0, right = 0;
      for (int value : values) {
        if (L <= value && value <= R) {
          // 以之前小于L的某个数作为起点 + 以之前在区间内的某个数作为起点 + 自己单独作为一个子区间
          result += (small + right + 1);
          ++right;

          // 处理下面的情况：
          // ... 6 2 7 1
          //         |
          //      数到这里时候，前面的2也可以作为起点去匹配后面的1了
          right += small;
          small = 0;
        } else if (value < L) {
          // 只能以之前在区间内的某个数作为起点
          result += right;
          ++small;
        } else {
          // 一夜回到解放前
          small = 0;
          right = 0;
        }
      }
      return result;
    }
  }

  public class WA_Solution {
    public int numSubarrayBoundedMax(int[] values, int L, int R) {
      int result = 0;
      int small = 0, right = 0;
      for (int value : values) {
        if (L <= value && value <= R) {
          // 以之前小于L的某个数作为起点 + 以之前在区间内的某个数作为起点 + 自己单独作为一个子区间
          result += (small + right + 1);
          ++right;
        } else if (value < L) {
          // 只能以之前在区间内的某个数作为起点
          result += right;
          ++small;
        } else {
          // 一夜回到解放前
          small = 0;
          right = 0;
        }
      }
      return result;
    }
  }

  private final Solution s = new Solution();

  @AllArgsConstructor
  private class Case {
    private int[] values;
    private int L, R;
  }

  private final Consumer<Case> c =
      aCase -> {
        log.info("%s | [%d, %d]", Strings.format(aCase.values), aCase.L, aCase.R);
        log.info(s.numSubarrayBoundedMax(aCase.values, aCase.L, aCase.R));
      };

  @Test
  public void test() {
    c.accept(new Case(Arrays.from(6, 2, 1, 7, 1, 4), 3, 8));
    c.accept(new Case(Arrays.from(6, 2, 7, 1, 4, 9, 1, 5, 8), 3, 8));
    c.accept(new Case(Arrays.from(73, 55, 36, 5, 55, 14, 9, 7, 72, 52), 32, 69));
  }
}
