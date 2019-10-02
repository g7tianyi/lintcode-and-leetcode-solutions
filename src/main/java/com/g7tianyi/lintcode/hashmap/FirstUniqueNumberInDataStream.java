package com.g7tianyi.lintcode.hashmap;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.common.Strings;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Oct 02, 2019
 *
 * @link https://www.lintcode.com/problem/first-unique-number-in-data-stream/description
 */
public class FirstUniqueNumberInDataStream {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int firstUniqueNumber(int[] values, int value) {

      Map<Integer, Queue<Integer>> myMap = new HashMap<>();

      for (int pos = 0; pos < values.length; ++pos) {
        if (!myMap.containsKey(values[pos])) {
          Queue<Integer> posQueue = new LinkedList<>();
          posQueue.offer(pos);
          myMap.put(values[pos], posQueue);
        } else {
          Queue<Integer> posQueue = myMap.get(values[pos]);
          if (posQueue.size() < 2) {
            posQueue.offer(pos);
          }
        }

        if (values[pos] == value) {
          int posMin = Integer.MAX_VALUE;
          for (Queue<Integer> posQueue : myMap.values()) {
            if (posQueue.size() == 1 && (posMin > posQueue.peek())) {
              posMin = posQueue.peek();
            }
          }
          if (posMin != Integer.MAX_VALUE) {
            return values[posMin];
          }
        }
      }
      return -1;
    }
  }

  @AllArgsConstructor
  private class Case {
    int[] values;
    int value;
  }

  private final Solution s = new Solution();

  private final Consumer<Case> c =
      aCase -> {
        log.info("%s | %d", Strings.format(aCase.values), aCase.value);
        log.info(s.firstUniqueNumber(aCase.values, aCase.value));
        log.info();
      };

  @Test
  public void test() {
    c.accept(new Case(Arrays.from(1, 2, 2, 1, 3, 4, 4, 5, 6), 5));
    c.accept(new Case(Arrays.from(1, 2, 2, 1, 3, 4, 4, 5, 6), 7));
    c.accept(new Case(Arrays.from(1, 2, 2, 1, 3, 4), 3));

    c.accept(new Case(Arrays.from(3, 6, 2, 4, 1), 4));
  }
}
