package com.g7tianyi.lintcode.hashmap;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.common.Strings;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 04, 2019
 *
 * @link https://www.lintcode.com/problem/find-anagram-mappings/description
 */
public class FindAnagramMappings {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int[] anagramMappings(int[] A, int[] B) {
      // Input:  A = [12, 28, 46, 32, 50]
      //         B = [50, 12, 32, 46, 28]
      // Output: [1, 4, 3, 2, 0]
      Map<Integer, Stack<Integer>> map = new HashMap<>();
      for (int i = 0; i < B.length; ++i) {
        Stack<Integer> indices = map.getOrDefault(B[i], new Stack<>());
        indices.push(i);
        map.put(B[i], indices);
      }

      int[] result = new int[A.length];
      for (int i = 0; i < A.length; ++i) {
        Stack<Integer> indices = map.get(A[i]);
        result[i] = indices.pop();
        map.put(A[i], indices);
      }
      return result;
    }
  }

  @AllArgsConstructor
  private class Case {
    int[] A;
    int[] B;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c =
        aCase -> {
          log.info(Strings.format(aCase.A));
          log.info(Strings.format(aCase.B));
          log.info(s.anagramMappings(aCase.A, aCase.B));
          log.info();
        };

    c.accept(new Case(Arrays.from(12, 28, 46, 32, 50), Arrays.from(50, 12, 32, 46, 28)));
    c.accept(new Case(Arrays.from(1, 2, 3, 4, 5), Arrays.from(5, 4, 3, 2, 1)));
  }
}
