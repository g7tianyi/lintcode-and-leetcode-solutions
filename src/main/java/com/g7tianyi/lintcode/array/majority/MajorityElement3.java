package com.g7tianyi.lintcode.array.majority;

import com.g7tianyi.util.Logger;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Oct 28, 2019
 *
 * @link https://www.lintcode.com/problem/majority-element-ii/description
 */
public class MajorityElement3 {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int majorityNumber(List<Integer> nums, int k) {
      Map<Integer, Integer> map = new HashMap<>();
      int minCount = nums.size() / k;
      for (Integer num : nums) {
        Integer count = map.getOrDefault(num, 0) + 1;
        if (count > minCount) {
          return num;
        }
        map.put(num, count);
      }
      return 0;
    }
  }

  private final Solution s = new Solution();

  @AllArgsConstructor
  private class Case {
    List<Integer> values;
    int k;
  }

  private final Consumer<Case> c =
      aCase -> {
        log.info(aCase.values);
        log.info(s.majorityNumber(aCase.values, aCase.k));
        log.info();
      };

  @Test
  public void test() {
    c.accept(new Case(Lists.newArrayList(3, 1, 2, 3, 2, 3, 3, 4, 4, 4), 3));
    c.accept(new Case(Lists.newArrayList(1, 1, 2), 3));
  }
}
