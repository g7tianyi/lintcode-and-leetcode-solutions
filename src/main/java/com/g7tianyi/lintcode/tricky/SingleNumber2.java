package com.g7tianyi.lintcode.tricky;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 20, 2019
 *
 * @link https://www.lintcode.com/problem/single-number-ii/description
 */
public class SingleNumber2 {

  private static final Logger log = Logger.getInstance();

  /**
   * 方法1：非常Tricky，统计二级制位上1的出现次数
   *
   * <p>方法2：位数一个大小为4的窗口，当计数为3时踢出窗口，当窗口已满时，将这个数字先移动到尾巴上
   *
   * <p>但是这两种方式都非常Tricky，如果不是写库函数，工程中写这样的代码，要么注释和测试很充分，要么就Fire吧，😆，
   */
  public class Solution {

    public int singleNumberII(int[] values) {
      int[] count = new int[32];
      for (int i = 0; i < 32; ++i) {
        count[i] = 0;
      }

      for (int value : values) {
        for (int i = 0, j = 1; i < 32; ++i) {
          if ((value & j) != 0) {
            ++count[i];
          }
          j <<= 1;
        }
      }

      int result = 0;
      for (int i = 0; i < 32; ++i) {
        if (count[i] % 3 == 1) {
          result += (1 << i);
        }
      }

      return result;
    }

    // 其实看不明白怎么可能内存溢出
    public int singleNumberII_MLE(int[] values) {
      if (values.length == 1) {
        return values[0];
      }

      Map<Integer, Integer> win = new HashMap<>();
      int index = 0, swapIndex, value, count;
      while (index < values.length) {
        value = values[index];
        if (!win.containsKey(value)) {
          if (win.size() < 4) {
            win.put(value, 0);
          } else {
            swapIndex = values.length - 1;
            while (swapIndex > index && !win.containsKey(values[swapIndex])) {
              --swapIndex;
            }
            if (swapIndex == index) {
              return value;
            }

            values[index] = values[swapIndex];
            values[swapIndex] = value;
            value = values[index];
          }
        }

        count = win.get(value);
        if (count != 2) {
          win.put(value, count + 1);
        } else {
          win.remove(value);
        }

        log.info(win.size());

        ++index;
      }

      for (Map.Entry<Integer, Integer> entry : win.entrySet()) {
        if (entry.getValue() == 1) {
          return entry.getKey();
        }
      }

      return -1;
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<int[]> c = values -> log.info(s.singleNumberII(values));

    c.accept(Arrays.from(2, 1, 2, 2));
    c.accept(Arrays.from(1, 1, 2, 3, 3, 3, 2, 2, 4, 1));
    c.accept(Arrays.from(1, 2, 3, 4, 5, 1, 2, 3, 4, 3, 2, 1, 4));
    c.accept(Arrays.from(1, 2, 3, 5, 4, 1, 2, 3, 4, 3, 2, 1, 4));
    c.accept(Arrays.from(1, 2, 3, 5, 7, 4, 1, 2, 7, 3, 4, 3, 2, 1, 4, 6, 5, 7, 5));
  }
}
