package com.g7tianyi.lintcode.dp;

import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Aug 25, 2019
 *
 * @link https://www.lintcode.com/problem/johns-backyard-garden/description
 */
public class JohnsBackyardGarden {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    // F(n) = F(n-3) || F(n-7)
    public String isBuild(int n) {
      boolean[] arr = new boolean[n + 1];
      int upper = Math.min(8, n);
      for (int i = 0; i < upper; i++) {
        arr[i] = false;
      }
      if (arr.length > 3) {
        arr[3] = true;
      }
      if (arr.length > 7) {
        arr[7] = true;
      }

      for (int i = 4; i <= n; i++) {
        if (arr[i - 3] || (i - 7 >= 0 && arr[i - 7])) {
          arr[i] = true;
        }
      }

      if (arr[n]) {
        return "YES";
      } else {
        return "NO";
      }
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    log.info(s.isBuild(3));
    log.info(s.isBuild(10));
    log.info(s.isBuild(5));
    log.info(s.isBuild(13));
  }
}
