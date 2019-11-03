package com.g7tianyi.lintcode.array.intersection;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by g7tianyi on Nov 03, 2019
 *
 * @link https://www.lintcode.com/problem/intersection-of-two-arrays-ii/description
 */
public class IntersectionOfTwoArrays2 {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    class Count {
      int c1, c2;

      public Count() {
        this.c1 = 0;
        this.c2 = 0;
      }
    }

    public int[] intersection(int[] arr1, int[] arr2) {
      Map<Integer, Count> map = new HashMap<>();
      for (int elem : arr1) {
        Count count = map.getOrDefault(elem, new Count());
        ++count.c1;
        map.put(elem, count);
      }
      for (int elem : arr2) {
        Count count = map.getOrDefault(elem, new Count());
        ++count.c2;
        map.put(elem, count);
      }

      List<Integer> elems = new ArrayList<>();
      for (Map.Entry<Integer, Count> entry : map.entrySet()) {
        Count count = entry.getValue();
        Integer num = entry.getKey();
        int min = Math.min(count.c1, count.c2);
        while (min > 0) {
          elems.add(num);
          --min;
        }
      }

      int i = 0;
      int[] result = new int[elems.size()];
      for (Integer elem : elems) {
        result[i++] = elem;
      }
      return result;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.intersection(Arrays.from(1, 2, 2, 1), Arrays.from(2, 2)));
    log.info(s.intersection(Arrays.from(1, 1, 2), Arrays.from(1)));
  }
}
