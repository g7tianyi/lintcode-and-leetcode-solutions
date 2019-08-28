package com.g7tianyi.lintcode.dp;

import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Aug 25, 2019
 *
 * @link https://www.lintcode.com/problem/paint-fence/description
 */
public class PaintFence {

  private static final Logger log = new Logger();

  public class Solution {

    public int numWays(int n, int k) {
      // F(n) 表示第n根柱子与第n-1根柱子异色时的方案总数
      // G(n) 表示第n根柱子与第n-1根柱子同色时的方案总数
      // F(0) = k, G(0) = 0
      // F(n) = F(n-1) * (k-1) + G(n-1) * (k-1)
      // G(n) = F(n-1)
      int F = k, G = 0, H; // H 是个临时变量
      for (int i = 1; i < n; i++) {
        H = F;
        F = (F + G) * (k - 1);
        G = H;
      }
      return F + G;
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    log.info(s.numWays(3, 2));
    log.info(s.numWays(2, 2));
  }
}
