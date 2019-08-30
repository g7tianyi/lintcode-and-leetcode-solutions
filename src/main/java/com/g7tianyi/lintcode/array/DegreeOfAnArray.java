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
 * Created by g7tianyi on Aug 31, 2019
 *
 * @link https://www.lintcode.com/problem/degree-of-an-array/description
 */
public class DegreeOfAnArray {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int findShortestSubArray(int[] elems) {

      Map<Integer, List<Integer>> valuePositions = new HashMap<>();
      for (int pos = 0; pos < elems.length; ++pos) {
        List<Integer> positions = valuePositions.getOrDefault(elems[pos], new ArrayList<>());
        positions.add(pos);
        valuePositions.put(elems[pos], positions);
      }

      int maxDegree = 0, result = Integer.MAX_VALUE;
      for (List<Integer> positions : valuePositions.values()) {
        if (positions == null) {
          continue;
        }

        if (positions.size() < maxDegree) {
          continue;
        }

        maxDegree = positions.size();
        positions.sort(Integer::compareTo);
        for (int i = 1; i < positions.size(); ++i) {
          if (result > positions.get(i) - positions.get(i - 1)) {
            result = positions.get(i) - positions.get(i - 1) + 1;
          }
        }
      }

      return result;
    }
  }

  @AllArgsConstructor
  public static class Case {

    private int[] elems;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> runner =
        aCase -> {
          log.info(Strings.format(aCase.elems));
          log.info(s.findShortestSubArray(aCase.elems));
          log.info();
        };

    runner.accept(new Case(new int[] {1, 2, 2, 3, 1}));
    runner.accept(new Case(new int[] {2, 2}));
  }
}
