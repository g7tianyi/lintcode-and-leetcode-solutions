package com.g7tianyi.lintcode.array;

import com.g7tianyi.common.Strings;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 25, 2019
 *
 * @link https://www.lintcode.com/problem/subarray-sum/description
 */
public class SubArraySum {

  private static final Logger log = Logger.getInstance();

  public class Solution {
    public List<Integer> subarraySum(int[] nums) {
      // write your code here
      int len = nums.length;

      List<Integer> result = new ArrayList<>();

      Map<Integer, Integer> map = new HashMap<>();

      map.put(0, -1);

      int sum = 0;
      for (int i = 0; i < len; i++) {
        sum += nums[i];
        if (map.containsKey(sum)) {
          result.add(map.get(sum) + 1);
          result.add(i);
          return result;
        }
        map.put(sum, i);
      }

      return result;
    }
  }

  @AllArgsConstructor
  public static class Input {

    private int[] arr;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Input> runner =
        input -> {
          log.info(Strings.format(input.arr));
          log.info(Strings.format(s.subarraySum(input.arr)));
          log.info("");
        };

    runner.accept(new Input(new int[] {-3, 1, 2, -3, 4}));
    runner.accept(new Input(new int[] {-3, 1, -4, 2, -3, 4}));
  }
}
