package com.g7tianyi.lintcode.math;

import com.g7tianyi.lintcode.util.Log;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by g7tianyi on Aug 27, 2019
 *
 * @link https://www.lintcode.com/problem/product-of-array-exclude-itself/description
 */
public class ProductOfArrayExcludeItself {

  private static final Log log = new Log();

  public class Solution {

    public List<Long> productExcludeItself(List<Integer> elems) {

      int len = elems.size();
      long[] prev = new long[len];
      long[] post = new long[len];
      prev[0] = 1;
      post[len - 1] = 1;

      for (int i = 1; i < len; ++i) {
        prev[i] = prev[i - 1] * elems.get(i - 1);
      }

      for (int i = len - 2; i >= 0; --i) {
        post[i] = post[i + 1] * elems.get(i + 1);
      }

      List<Long> result = new ArrayList<>();
      for (int i = 0; i < len; ++i) {
        result.add(prev[i] * post[i]);
      }
      return result;
    }
  }

  @Test
  public void test() {
    Solution s = new Solution();

    log.info(s.productExcludeItself(Lists.newArrayList(1, 2, 3)));
    log.info(s.productExcludeItself(Lists.newArrayList(2, 4, 6)));
    log.info(s.productExcludeItself(Lists.newArrayList(2, 3, 4, 5, 6, 7, 8)));
  }
}
