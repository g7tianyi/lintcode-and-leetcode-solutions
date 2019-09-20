package com.g7tianyi.lintcode.tricky;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 20, 2019
 *
 * @link https://www.lintcode.com/problem/single-number-iii/description
 */
public class SingleNumber3 {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    // https://www.lintcode.com/problem/single-number-iii/note/174305
    // 这个想法的确是很酷
    public List<Integer> singleNumberIII(int[] values) {
      int x = 0;
      for (int value : values) {
        x ^= value;
      }

      int y = x & (-x);
      int r1 = 0, r2 = 0;
      for (int value : values) {
        if ((value & y) != 0) {
          r1 ^= value;
        } else {
          r2 ^= value;
        }
      }

      List<Integer> result = new ArrayList<>();
      result.add(r1);
      result.add(r2);
      return result;
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<int[]> c = values -> log.info(s.singleNumberIII(values));

    c.accept(Arrays.from(1, 2, 2, 3, 4, 4, 5, 3));
    c.accept(Arrays.from(1, 1, 2, 3, 4, 4));
  }
}
