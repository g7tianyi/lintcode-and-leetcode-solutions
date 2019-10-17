package com.g7tianyi.lintcode.string;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by g7tianyi on Oct 17, 2019
 *
 * @link https://www.lintcode.com/problem/positions-of-large-groups/description
 */
public class PositionsOfLargeGroups {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public List<List<Integer>> largeGroupPositions(String S) {

      List<List<Integer>> result = new ArrayList<>();
      if (S == null || S.length() < 3) {
        return result;
      }

      int i = 0, j;
      char ch = S.charAt(i);
      for (j = 1; j < S.length(); ++j) {
        if (S.charAt(j) != ch) {
          if (j - i >= 3) {
            List<Integer> range = new ArrayList<>();
            range.add(i);
            range.add(j - 1);
            result.add(range);
          }
          i = j;
          ch = S.charAt(i);
        }
      }

      if (j - i >= 3) {
        List<Integer> range = new ArrayList<>();
        range.add(i);
        range.add(j - 1);
        result.add(range);
      }

      return result;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    List<List<Integer>> lists = s.largeGroupPositions("abcdddeeeeaabbbcddd");
    for (List<Integer> list : lists) {
      log.info(list);
    }
  }
}
