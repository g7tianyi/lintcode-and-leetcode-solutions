package com.g7tianyi.lintcode.others;

import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Oct 25, 2019
 *
 * @link https://www.lintcode.com/problem/find-the-celebrity/description
 */
public class FindTheCelebrity {

  private static final Logger log = Logger.getInstance();

  class Relation {
    boolean knows(int a, int b) {
      return true;
    }
  }

  public class Solution extends Relation {

    public int findCelebrity(int n) {
      int result = 0;
      for (int i = 1; i < n; i++) {
        if (knows(result, i)) { // result 认识了一个人，他就不再是名人了
          result = i;
        }
      }

      for (int i = 0; i < n; i++) {
        if (i != result && !knows(i, result)) {
          return -1;
        }
        if (i != result && knows(result, i)) {
          return -1;
        }
      }
      return result;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {}
}
