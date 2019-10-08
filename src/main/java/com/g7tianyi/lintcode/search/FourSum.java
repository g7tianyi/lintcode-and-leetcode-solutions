package com.g7tianyi.lintcode.search;

import com.g7tianyi.common.Strings;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Oct 09, 2019
 *
 * @link https://www.lintcode.com/problem/4sum/description
 */
public class FourSum {

  private static final Logger log = Logger.getInstance();

  // 总耗时 2688 ms
  public class Solution {

    public List<List<Integer>> fourSum(int[] numbers, int target) {

      if (numbers == null || numbers.length == 0) {
        return new ArrayList<>();
      }

      Arrays.sort(numbers);

      return new Resolver(numbers, target).resolve();
    }

    private class Resolver {
      List<List<Integer>> result;
      LinkedList<Integer> path;
      int[] numbers;
      int target;

      public Resolver(int[] numbers, int target) {
        this.numbers = numbers;
        this.target = target;

        this.result = new ArrayList<>();
        this.path = new LinkedList<>();
      }

      List<List<Integer>> resolve() {
        resolve(0, 0, 0);
        return result;
      }

      void resolve(int index, int sum, int depth) {
        if (depth == 4) {
          if (sum == target) {
            result.add(new ArrayList<>(path));
          }
          return;
        }

        for (int i = index; i < numbers.length; ++i) {
          if (i > index && numbers[i] == numbers[i - 1]) {
            continue;
          }
          path.addLast(numbers[i]);
          resolve(i + 1, sum + numbers[i], depth + 1);
          path.removeLast();
        }
      }
    }
  }

  // 总耗时 2204 ms
  public class DummySolution {

    public List<List<Integer>> fourSum(int[] numbers, int target) {

      List<List<Integer>> result = new ArrayList<>();
      if (numbers == null || numbers.length == 0) {
        return result;
      }

      Arrays.sort(numbers);
      for (int i = 0; i < numbers.length; ++i) {
        if (i > 0 && numbers[i] == numbers[i - 1]) {
          continue;
        }
        for (int j = i + 1; j < numbers.length; ++j) {
          if (j > i + 1 && numbers[j] == numbers[j - 1]) {
            continue;
          }
          for (int k = j + 1; k < numbers.length; ++k) {
            if (k > j + 1 && numbers[k] == numbers[k - 1]) {
              continue;
            }
            for (int l = k + 1; l < numbers.length; ++l) {
              if (l > k + 1 && numbers[l] == numbers[l - 1]) {
                continue;
              }
              int value = numbers[i] + numbers[j] + numbers[k] + numbers[l];
              if (value == target) {
                List<Integer> path = new ArrayList<>();
                path.add(numbers[i]);
                path.add(numbers[j]);
                path.add(numbers[k]);
                path.add(numbers[l]);
                result.add(path);
              } else if (value > target) {
                break;
              }
            }
          }
        }
      }

      return result;
    }
  }

  private final Solution s = new Solution();

  @AllArgsConstructor
  private final class Case {
    private int[] numbers;
    private int target;
  }

  private final Consumer<Case> c =
      aCase -> {
        log.info("%s | %d", Strings.format(aCase.numbers), aCase.target);
        List<List<Integer>> result = s.fourSum(aCase.numbers, aCase.target);
        for (List<Integer> path : result) {
          log.info(path);
        }
        log.info();
      };

  @Test
  public void test() {
    c.accept(new Case(com.g7tianyi.common.Arrays.from(1, 0, -1, 0, -2, 2), 0));
    c.accept(
        new Case(
            com.g7tianyi.common.Arrays.from(
                -8, -0, -7, -101, -123, -1, -2, 1, 1, 4, -2, 0, -1, 0, -1111, 0, -1, -2, -3, -4, -5,
                -6, -100, -98, -111, -11),
            -111));
  }
}
