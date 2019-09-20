package com.g7tianyi.lintcode.heap;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 21, 2019
 *
 * @link https://www.lintcode.com/problem/find-median-from-data-stream/description
 */
public class FindMedianFromDataStream {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    // 用两个堆来维护中位数结构
    // 前半部分是最大堆，后半部分是最小堆
    // 遍历时，如果当前元素小于最大堆的堆顶元素，则放入最大堆，否则放入最小堆
    // 整个过程需要保证左半部分的元素数量与右半部分元素数量的差值为0或1
    // 这样就可以保证左半部分的最大堆的堆顶元素就是中位数
    public int[] medianII(int[] values) {

      if (values.length == 0) {
        return values;
      }

      PriorityQueue<Integer> maxHeap = // 前半截，最大堆
          new PriorityQueue<>(
              new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                  return o2 - o1;
                }
              });
      PriorityQueue<Integer> minHeap = // 后半截，最小堆
          new PriorityQueue<>(
              new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                  return o1 - o2;
                }
              });

      int[] result = new int[values.length];
      for (int i = 0, value; i < values.length; ++i) {
        value = values[i];
        if (maxHeap.isEmpty() || value < maxHeap.peek()) {
          maxHeap.offer(value);
        } else {
          minHeap.offer(value);
        }

        if (maxHeap.size() < minHeap.size()) {
          maxHeap.offer(minHeap.poll());
        } else if (maxHeap.size() - minHeap.size() > 1) {
          minHeap.offer(maxHeap.poll());
        }

        result[i] = maxHeap.peek();
      }

      return result;
    }

    // 插入排序的思路，但是插入排序时，存在元素复制的问题，导致算法最终退化为O(n^2)
    // 但似乎也不算很慢
    // 数据通过测试总耗时 398 ms
    // 您的提交打败了 64.80% 的提交!
    public int[] medianII_ON2(int[] values) {
      if (values.length == 0) {
        return values;
      }

      int[] result = new int[values.length];
      result[0] = values[0];

      for (int i = 1; i < values.length; ++i) {
        int value = values[i];
        int pos = findInsertPosition(values, i, value);
        System.arraycopy(values, pos, values, pos + 1, i - pos);
        values[pos] = value;
        // print(values, i + 1);
        result[i] = values[i >> 1];
      }
      return result;
    }

    private int findInsertPosition(int[] values, int len, int value) {
      int former = 0, latter = len - 1, middle;
      while (former <= latter) {
        middle = former + ((latter - former) >> 1);
        if (values[middle] == value) {
          return middle;
        }

        if (middle > former && values[middle] > value && values[middle - 1] < value) {
          return middle;
        }

        if (values[middle] > value) {
          latter = middle - 1;
        } else {
          former = middle + 1;
        }
      }
      return former;
    }

    private void print(int[] values, int len) {
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < len; ++i) {
        sb.append(values[i]).append(' ');
      }
      log.info(sb.toString());
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<int[]> c =
        values -> {
          log.info(values);
          log.info(s.medianII(values));
          log.info();
        };

    c.accept(Arrays.from(1, 2, 3, 4, 5));
    c.accept(Arrays.from(4, 5, 1, 3, 2, 6, 0));
  }
}
