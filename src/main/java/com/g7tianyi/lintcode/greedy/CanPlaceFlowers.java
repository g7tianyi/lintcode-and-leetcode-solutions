package com.g7tianyi.lintcode.array;

import com.g7tianyi.common.Strings;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.HashMap;
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

    class ElemInfo {
      int posMin;
      int posMax;
      int degree;

      public ElemInfo() {
        this.posMin = -1;
        this.posMax = -1;
        this.degree = 0;
      }

      public void addPosition(int pos) {
        if (this.posMin == -1) {
          this.posMin = pos;
        } else {
          this.posMax = pos;
        }
        ++this.degree;
      }

      public int size() {
        return this.posMax - this.posMin + 1;
      }
    }

    public int findShortestSubArray(int[] elems) {

      Map<Integer, ElemInfo> valuePositions = new HashMap<>();
      for (int pos = 0; pos < elems.length; ++pos) {
        ElemInfo elemInfo = valuePositions.getOrDefault(elems[pos], new ElemInfo());
        elemInfo.addPosition(pos);
        valuePositions.put(elems[pos], elemInfo);
      }

      int maxDegree = 0, result = Integer.MAX_VALUE;
      for (ElemInfo elemInfo : valuePositions.values()) {
        if (maxDegree > elemInfo.degree) {
          continue;
        }

        maxDegree = elemInfo.degree;
        result = elemInfo.size();
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
    runner.accept(new Case(new int[] {1, 2, 2, 3, 1}));
    runner.accept(new Case(new int[] {1, 2, 2, 3, 1, 4, 2}));
  }
}
