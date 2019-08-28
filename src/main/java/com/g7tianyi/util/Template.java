package com.g7tianyi.util;

import lombok.AllArgsConstructor;
import org.junit.Test;

/**
 * Created by g7tianyi on Aug 23, 2019
 *
 * @link
 */
public class Template {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int resolve() {
      return 0;
    }
  }

  @AllArgsConstructor
  public static class Input {}

  @Test
  public void test() {

    Solution s = new Solution();
  }
}
