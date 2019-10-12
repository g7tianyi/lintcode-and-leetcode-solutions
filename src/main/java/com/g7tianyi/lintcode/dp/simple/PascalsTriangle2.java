package com.g7tianyi.lintcode.dp.simple;

import com.g7tianyi.common.Strings;
import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 31, 2019
 *
 * @link https://www.lintcode.com/problem/pascals-triangle/description
 */
public class PascalsTriangle2 {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public List<Integer> getRow(int numRows) {

      List<List<Integer>> result = new ArrayList<>();
      result.add(new ArrayList<>());
      result.add(new ArrayList<>());

      int prev = 0, curr = 1;
      for (int i = 0; i <= numRows; i++) {
        List<Integer> prevLine = result.get(prev);
        List<Integer> currLine = result.get(curr);
        currLine.clear();
        for (int j = 0; j <= i; j++) {
          if (i == 0 || j == 0 || j == i) {
            currLine.add(1);
          } else {
            currLine.add(prevLine.get(j - 1) + prevLine.get(j));
          }
        }

        prev ^= 1;
        curr ^= 1;
      }

      return result.get(prev);
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Integer> c = num -> log.info(Strings.format(s.getRow(num)));

    for (int i = 0; i <= 20; i++) {
      c.accept(i);
    }
  }
}
