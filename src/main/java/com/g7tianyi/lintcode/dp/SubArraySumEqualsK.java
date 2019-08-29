package com.g7tianyi.lintcode.dp;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by g7tianyi on Aug 24, 2019
 *
 * @link https://www.lintcode.com/problem/subarray-sum-equals-k/description
 */
public class SubArraySumEqualsK {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int subarraySumEqualsK(int[] elems, int k) {
      if (elems == null || elems.length == 0) {
        return 0;
      }

      Map<Integer, Integer> map = new HashMap<>();
      map.put(0, 1);

      int sum = 0, result = 0;
      for (int elem : elems) {
        sum += elem;
        result += map.getOrDefault(sum - k, 0);
        map.put(sum, map.getOrDefault(sum, 0) + 1);
      }

      return result;
    }

    public int subarraySumEqualsKMLE(int[] elems, int k) {

      int result = elems[0] == k ? 1 : 0;
      // P(A, B)表示和为a的子数组的总数量为B
      Map<Integer, Integer> prev = new HashMap<>(), curr;
      prev.put(elems[0], 1);

      for (int i = 1; i < elems.length; ++i) {

        curr = new HashMap<>();

        Iterator<Map.Entry<Integer, Integer>> iterator = prev.entrySet().iterator();
        while (iterator.hasNext()) {
          Map.Entry<Integer, Integer> entry = iterator.next();
          iterator.remove();

          curr.put(entry.getKey() + elems[i], entry.getValue());
        }

        if (curr.containsKey(elems[i])) {
          curr.put(elems[i], curr.get(elems[i]) + 1);
        } else {
          curr.put(elems[i], 1);
        }

        if (curr.containsKey(k)) {
          result += curr.get(k);
        }

        prev = curr;
      }

      return result;
    }

    public int subarraySumEqualsKTLE(int[] elems, int k) {

      int result = 0;
      // P(i,j)表示从第i个元素到第j个元素的和
      int[] prevs = new int[elems.length];
      prevs[0] = elems[0];
      for (int i = 1; i < elems.length; ++i) {
        prevs[i] = 0;
      }
      if (prevs[0] == k) {
        ++result;
      }

      for (int i = 1; i < elems.length; ++i) {
        for (int j = 0; j <= i; ++j) {
          prevs[j] += elems[i];
          if (prevs[j] == k) {
            ++result;
          }
        }
      }

      return result;
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    log.info(
        s.subarraySumEqualsK(
            new int[] {-10, 3, 2, -6, -6, 0, 0, 0, -7, 15, -5, 5, -8, -3, -5, 1, -2, -2, 8, -8, 6},
            15));
    log.info(s.subarraySumEqualsK(new int[] {2, 1, -1, 1, 2}, 3));
    log.info(s.subarraySumEqualsK(new int[] {1, 1, 1}, 2));
  }
}
