package com.g7tianyi.lintcode.array;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 22, 2019
 *
 * @link https://www.lintcode.com/problem/find-the-duplicate-number/description
 */
public class FindTheDuplicateNumber {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    // WA后上个洗手间，回来发现原来就是猜数字嘛...
    // 可以联想到这一题：https://www.lintcode.com/problem/guess-number-higher-or-lower/description
    // 当然也不仅仅是那么简单，具体过程如下：
    // 对于中间数mid，计算数组中大于、小于和等于mid的数字各自有多少个
    // 如果等于mid的数字超过1个，直接返回结果
    // 否则，根据抽屉原理：
    // 如果小于mid的数字的个数大于mid，下一轮在左区间搜索，即 upper = mid - 1
    // 如果大于mid的数字的个数大于mid，下一轮在右区间搜索，即 lower = mid + 1
    public int findDuplicate(int[] values) {
      int lower = 1, upper = values.length - 1, middle;
      int small, equal;
      while (lower <= upper) {
        middle = lower + ((upper - lower) >> 1);
        small = equal = 0;
        for (int val : values) {
          if (val == middle) {
            ++equal;
          } else if (val < middle) {
            ++small;
          }
        }

        if (equal > 1) {
          return middle;
        }

        if (small >= middle) {
          upper = middle - 1;
        } else {
          lower = middle + 1;
        }
      }

      return -1;
    }

    // 下面的想法应该还不错，但是没有考虑到数字不一定连续的情况，我也是郁闷，WA了...
    public int findDuplicate_WA(int[] values) {

      long max = Long.MIN_VALUE;
      long len = values.length;
      long sum = 0;
      for (int val : values) {
        sum += val;
        if (max < val) {
          max = val;
        }
      }

      // 给定序列: 1 2 3 4 4 4 5
      // 期待序列: 1 2 3 4 5 6 7
      // 重复的数字是4，次数是2
      //
      // 重复次数怎么算出来的呢？看下图：
      //               _____
      //    1 2 3 4 5 | 4 4 |
      //    1 2 3 4 5 | 6 7 |
      //               ˉˉˉˉˉ
      // 最大数是5，理想中的最大数应该是7，相当于原本是6和7的位置被那个重复的数字占用了
      long count = len - max; // 重复次数

      // 怎么算出来这个数字呢？
      //     __________  _____
      //    | 1 2 3 4 5 | 4 4 |
      //     ˉˉˉˉˉˉˉˉˉˉ  ˉˉˉˉˉ
      // 用整个输入数组的和，减去最大数字为结尾的序列的和，就是重复数字的和，然后除一下出现次数
      return (int) ((sum - sequenceSum(max)) / count);
    }

    private long sequenceSum(long max) {
      return (max * (1 + max)) >> 1;
    }
  }

  private final Solution s = new Solution();

  private final Consumer<int[]> c =
      values -> {
        log.info(values);
        log.info(s.findDuplicate(values));
        log.info();
      };

  @Test
  public void test() {
    c.accept(Arrays.from(1, 1, 2));
    c.accept(Arrays.from(1, 2, 2));
    c.accept(Arrays.from(1, 1, 2, 3, 4, 5));
    c.accept(Arrays.from(1, 2, 3, 4, 5, 5));
  }

  @Test
  public void testJumpCase() {
    c.accept(Arrays.from(1, 2, 5, 5, 5, 5));
    c.accept(Arrays.from(1, 2, 3, 3, 3, 3, 3));
    c.accept(Arrays.from(1, 1, 1, 2, 7, 4, 3, 1, 1, 8));
  }

  @Test
  public void testEdgeCase() {
    c.accept(Arrays.from(1, 1, 1, 1, 1, 1));
    c.accept(Arrays.from(1, 1));
  }
}
