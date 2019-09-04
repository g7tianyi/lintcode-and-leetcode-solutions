package com.g7tianyi.lintcode.math;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 04, 2019
 *
 * @link https://www.lintcode.com/problem/reach-a-number/description
 */
public class ReachANumber {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    // https://www.cnblogs.com/grandyang/p/8456022.html
    // 无聊啊真是...
    public int reachNumber(int target) {
      target = Math.abs(target);
      long n = (long) Math.ceil((-1.0 + Math.sqrt(1 + 8.0 * target)) / 2);
      long sum = n * (n + 1) / 2;
      if (sum == target) {
        return (int) n;
      }
      long res = sum - target;
      if ((res & 1) == 0) {
        return (int) n;
      }
      return (int) (n + ((n & 1) != 0 ? 2 : 1));
    }
  }

  @AllArgsConstructor
  private class Case {}

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c = aCase -> {};
  }
}
