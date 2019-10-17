package com.g7tianyi.lintcode.hashmap;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.util.Logger;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by g7tianyi on Oct 17, 2019
 *
 * @link https://www.lintcode.com/problem/maximum-average-score/description
 */
public class MaximumAverageScore {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    private class Item {
      private int total = 0;
      private int count = 0;

      public void append(int value) {
        total += value;
        ++count;
      }

      public double value() {
        if (count == 0) {
          return 0;
        }
        return (double) total / count;
      }
    }

    public double maximumAverageScore(List<String> names, int[] grades) {

      Map<String, Item> map = new HashMap<>();

      int i = 0;
      for (String name : names) {
        if (!map.containsKey(name)) {
          map.put(name, new Item());
        }
        map.get(name).append(grades[i]);
        ++i;
      }

      double result = 0;
      for (Item item : map.values()) {
        result = Math.max(result, item.value());
      }
      return result;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(
        s.maximumAverageScore(
            Lists.newArrayList(
                "john", "xisa", "xisa", "liajd", "alice", "john", "xisa", "mark", "ted", "xlisa"),
            Arrays.from(95, 83, 33, 50, 78, 91, 80, 67, 85, 87)));
  }
}
