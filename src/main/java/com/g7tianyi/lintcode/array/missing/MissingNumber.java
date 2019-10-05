package com.g7tianyi.lintcode.array.missing;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Oct 05, 2019
 *
 * @link https://www.lintcode.com/problem/missing-number/description
 */
public class MissingNumber {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    // 不要弄得太复杂，简单即是美：遍历数组，对于每一个元素A[i]，
    // 如果A[i] != i，就将这个值放到对应下标上去，即
    //   1) A[i] == i；
    //   2) A[i] == A.length，
    // 对于情况2)，A[i] = -1，用-1表示暂时没有出现。
    // 整个过程相当于将每个元素都安排到属于它的位置上去，
    // 那个位置上出现空缺了——即等于-1了，就是缺失的数字了
    public int findMissing(int[] values) {

      for (int i = 0; i < values.length; ++i) {
        while (values[i] >= 0 && values[i] != i) {
          if (values[i] == values.length) {
            values[i] = -1;
            break;
          }
          int temp = values[values[i]];
          values[values[i]] = values[i];
          values[i] = temp;
        }
      }

      for (int i = 0; i < values.length; ++i) {
        if (values[i] == -1) {
          return i;
        }
      }
      return values.length;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.findMissing(Arrays.from(0, 3, 1)));
    log.info(s.findMissing(Arrays.from(2, 5, 4, 0, 1)));
    log.info(s.findMissing(Arrays.from(1, 2, 3)));
  }
}
