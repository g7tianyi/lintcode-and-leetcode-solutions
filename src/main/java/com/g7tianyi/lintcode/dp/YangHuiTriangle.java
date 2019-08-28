package com.g7tianyi.lintcode.dp;

import com.g7tianyi.common.Strings;
import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 25, 2019
 *
 * @link https://www.lintcode.com/problem/yang-hui-triangle/description
 */
public class YangHuiTriangle {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public List<List<Integer>> calcYangHuisTriangle(int n) {

      List<List<Integer>> result = new ArrayList<>();

      for (int i = 0; i < n; i++) {
        List<Integer> line = new ArrayList<>();
        for (int j = 0; j <= i; j++) {
          if (i == 0 || j == 0 || j == i) {
            line.add(1);
          } else {
            line.add(result.get(i - 1).get(j - 1) + result.get(i - 1).get(j));
          }
        }
        result.add(line);
      }

      return result;
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Integer> c =
        num -> {
          log.info("%d阶杨辉三角形", num);
          List<List<Integer>> matrix = s.calcYangHuisTriangle(num);
          StringBuilder sb = new StringBuilder();
          for (List<Integer> line : matrix) {
            sb.append(Strings.format(line)).append("\n");
          }
          log.info(sb.toString());
        };

    for (int i = 0; i <= 20; i++) {
      c.accept(i);
    }
  }
}
