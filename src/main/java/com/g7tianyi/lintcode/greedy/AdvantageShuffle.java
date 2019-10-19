package com.g7tianyi.lintcode.greedy;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by g7tianyi on Oct 19, 2019
 *
 * @link https://www.lintcode.com/problem/advantage-shuffle/description
 */
public class AdvantageShuffle {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    class Item {
      private int value;
      private int index;

      public Item(int value, int index) {
        this.value = value;
        this.index = index;
      }
    }

    public int[] advantageCount(int[] A, int[] B) {

      Arrays.sort(A);

      PriorityQueue<Item> pQueue =
          new PriorityQueue<>(
              new Comparator<Item>() {
                @Override
                public int compare(Item o1, Item o2) {
                  return Integer.compare(o2.value, o1.value);
                }
              });

      for (int i = 0; i < B.length; ++i) {
        pQueue.offer(new Item(B[i], i));
      }

      int a = 0, b = A.length - 1;
      int[] result = new int[A.length];
      while (!pQueue.isEmpty()) {
        Item item = pQueue.poll();
        int j = search(A, a, b, item.value);
        result[item.index] = A[j];

        if (j == a) {
          ++a;
        } else if (j == b) {
          --b;
        } else {
          System.arraycopy(A, j + 1, A, j, b - j);
          --b;
        }
      }

      return result;
    }

    // 贪心策略
    // 首先基于二分查找来找到最小的、大于value的数
    // 如果找不到这样的数字，就用最小的数字来顶替
    private int search(int[] values, int i, int j, int value) {
      int k = i;
      while (i <= j) {
        int m = i + ((j - i) >> 1);
        if (values[m] > value) {
          if (m == i || values[m - 1] <= value) {
            return m;
          }
          j = m - 1;
        } else {
          i = m + 1;
        }
      }
      return k;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {

    log.info(
        s.advantageCount(
            com.g7tianyi.common.Arrays.from(2, 7, 11, 15),
            com.g7tianyi.common.Arrays.from(1, 10, 4, 11)));

    log.info(
        s.advantageCount(
            com.g7tianyi.common.Arrays.from(12, 24, 8, 32),
            com.g7tianyi.common.Arrays.from(13, 25, 32, 11)));
  }
}
