package com.g7tianyi.lintcode.search;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Oct 12, 2019
 *
 * @link https://www.lintcode.com/problem/combinations/description
 */
public class Combinations {

  private static final Logger log = Logger.getInstance();

  public class Solution {
    public List<List<Integer>> combine(int n, int k) {
      List<List<Integer>> result = new ArrayList<>();
      if (k <= 0 || n <= 0 || k > n) {
        return result;
      }
      search(1, n, k, new LinkedList<>(), result);
      return result;
    }

    private void search(
        int index, int maxIndex, int size, LinkedList<Integer> path, List<List<Integer>> result) {
      if (path.size() == size) {
        result.add(new ArrayList<>(path));
        return;
      }

      for (int i = index; i <= maxIndex - (size - path.size()) + 1; ++i) {
        path.addLast(i);
        search(i + 1, maxIndex, size, path, result);
        path.removeLast();
      }
    }
  }

  private final Solution s = new Solution();

  @AllArgsConstructor
  private class Input {
    private int n, k;
  }

  private final Consumer<Input> c =
      input -> {
        List<List<Integer>> result = s.combine(input.n, input.k);
        result.forEach(log::info);
        log.info();
      };

  @Test
  public void test() {
    c.accept(new Input(5, 3));
    c.accept(new Input(4, 2));
    c.accept(new Input(4, 1));
  }
}
