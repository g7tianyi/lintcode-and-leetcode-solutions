package com.g7tianyi.lintcode.array;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Nov 13, 2019
 *
 * @link https://www.lintcode.com/problem/maximum-gap/description
 */
public class MaximumGap {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    // 脑回路清奇，学到了。
    //
    // 首先我们定义maxNum和minNum表示这个数组的最大/最小元素, N表示这个数组元素的个数.
    // 那么这个数组一共有N-1个间距.
    // 这N-1个间距的平均值就是 avgGap = (maxNum - minNum) / (N - 1)，这个平均值也是答案的最小值，
    // 因为这N个元素平均分配时，最大的间距最小.
    //
    // 然后我们对这N个元素分类，分类依据就是这个元素与minNum的间距是 avgGap 的多少倍。
    // 因为这样分类, 同一组内的元素的间距必然不会是最大间距。
    // 这时我们要找的最大间距处于组与组之间, 即某一组里最小的元素与它上一组的最大的元素的间距的最大值。
    // 因此, 我们只需要维护每一组里最小与最大的元素即可。
    //
    // 设定maxNums和minNums数组, maxNums[i]表示原数组中与minNum的差为 avgGap的i倍（向下取整）的最大的元素。
    // 同理minNums[i] 表示相同含义下的最小的元素。
    //
    // 然后我们遍历 maxNums, minNums, 将第i组的最小值minNums[i]与第i-1组的最大值maxNums[i-1]做差，
    // 维护最大值就可以得到答案了。
    public int maximumGap(int[] nums) {

      if (nums == null || nums.length < 2) {
        return 0;
      }

      int max = nums[0], min = nums[0];
      for (int num : nums) {
        max = Math.max(max, num);
        min = Math.min(min, num);
      }
      if (max == min) {
        return 0;
      }

      int avgGap = (max - min) / (nums.length - 1) + 1;
      int len = (max - min) / avgGap + 1;

      long[] maxArr = new long[len];
      long[] minArr = new long[len];
      for (int i = 0; i < maxArr.length; ++i) {
        maxArr[i] = Long.MIN_VALUE;
        minArr[i] = Long.MAX_VALUE;
      }

      for (int num : nums) {
        int index = (num - min) / avgGap;
        maxArr[index] = Math.max(maxArr[index], num);
        minArr[index] = Math.min(minArr[index], num);
      }

      int result = (int) (maxArr[0] - minArr[0]), i = 0, j = 1;
      while (true) {
        while (i < maxArr.length && maxArr[i] == Long.MIN_VALUE) {
          ++i;
        }
        if (i == maxArr.length) {
          break;
        }

        while (j < minArr.length && minArr[j] == Long.MAX_VALUE) {
          ++j;
        }
        if (j == maxArr.length) {
          break;
        }

        result = Math.max(result, (int) (minArr[j] - maxArr[i]));
        ++i;
        ++j;
      }

      return result;
    }
  }

  private final Solution s = new Solution();

  private final Consumer<int[]> c =
      values -> {
        log.info(values);
        java.util.Arrays.sort(values);
        log.info(values);
        log.info(s.maximumGap(values));
        log.info();
      };

  @Test
  public void test() {
    c.accept(Arrays.from(1, 9, 5, 2, 12));
    c.accept(Arrays.from(2147483647, 0));
    c.accept(Arrays.from(2));

    for (int i = 0; i < 10; ++i) {
      c.accept(Arrays.random(10, 30));
    }
  }
}
