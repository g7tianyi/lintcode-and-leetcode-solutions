package com.g7tianyi.lintcode.array;

import com.g7tianyi.lintcode.util.Log;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

/**
 * Created by g7tianyi on Aug 27, 2019
 *
 * <p>https://www.lintcode.com/problem/majority-element/description
 */
public class MajorityElement {

  private static final Log log = new Log();

  public class Solution {

    public int majorityNumber(List<Integer> arr) {
      int len = arr.size(), i = 0, j = 1;
      if (len == 2) {
        return arr.get(i);
      }

      while (j < len) {
        if (!arr.get(i).equals(arr.get(j))) {
          ++i;
        }
        ++j;
      }

      return arr.get(i);
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    log.info(s.majorityNumber(Lists.newArrayList(1, 1, 1, 1, 2, 2, 2)));
    log.info(s.majorityNumber(Lists.newArrayList(1, 1, 1, 2, 2, 2, 2)));
    log.info(s.majorityNumber(Lists.newArrayList(1, 2, 2, 5, 2, 2, 2, 3, 1)));
  }
}
