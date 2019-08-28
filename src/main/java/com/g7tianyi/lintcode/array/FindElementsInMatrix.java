package com.g7tianyi.lintcode.array;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 28, 2019
 *
 * @link https://www.lintcode.com/problem/find-elements-in-matrix/description
 */
public class FindElementsInMatrix {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int FindElements(int[][] elems) {

      Map<Integer, Integer> map = new HashMap<>();
      for (int j = 0; j < elems[0].length; j++) {
        if (!map.containsKey(elems[0][j])) {
          map.put(elems[0][j], 1);
        }
      }

      Set<Integer> line = new HashSet<>();
      for (int i = 1; i < elems.length; ++i) {
        for (int j = 0; j < elems[i].length; ++j) {
          if (line.contains(elems[i][j])) {
            continue;
          }
          line.add(elems[i][j]);

          if (map.containsKey(elems[i][j])) {
            map.put(elems[i][j], map.get(elems[i][j]) + 1);
          }
        }
        line.clear();
      }

      for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
        if (entry.getValue() == elems.length) {
          return entry.getKey();
        }
      }

      return 0;
    }
  }

  @AllArgsConstructor
  private static class Case {
    private int[][] elems;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c =
        aCase -> {
          log.info(s.FindElements(aCase.elems));
          log.info();
        };

    c.accept(
        new Case(
            new int[][] {
              {2, 5, 3},
              {3, 2, 1},
              {1, 3, 5},
            }));

    c.accept(
        new Case(
            new int[][] {
              {2, 5, 3, 2, 3, 3},
              {3, 2, 2, 1, 2, 5},
              {1, 5, 5, 5, 5, 4},
            }));
  }
}
