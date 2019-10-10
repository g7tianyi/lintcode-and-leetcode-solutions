package com.g7tianyi.lintcode.dp;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Oct 10, 2019
 *
 * @link https://www.lintcode.com/problem/digital-flip/description
 */
public class DigitalFlip {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int flipDigit(int[] nums) {

      if (nums == null || nums.length < 2) {
        return 0;
      }

      // F(i,0)表示当nums[i]需要置为0时，最少需要多少次变换
      // F(i,1)表示当nums[i]需要置为1时，最少需要多少次变换
      // F(i,0) = { // 前面的数字是1或0都OK
      //  if nums[i] == 0, MIN(F(i-1, 0), F(i-1, 1))
      //  if nums[i] == 1, MIN(F(i-1, 0), F(i-1, 1)) + 1
      // }
      // F(i,1) = { // 前面的数字必须是1
      //  if nums[i] == 0, F(i-1, 1) + 1
      //  if nums[i] == 1, F(i-1, 1)
      // }
      int F0 = nums[0] == 0 ? 0 : 1;
      int F1 = nums[0] == 1 ? 0 : 1;
      for (int i = 1; i < nums.length; ++i) {
        if (nums[i] == 0) {
          F0 = Math.min(F0, F1);
          F1 = F1 + 1;
        } else {
          F0 = Math.min(F0, F1) + 1;
        }
      }
      return Math.min(F0, F1);
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.flipDigit(Arrays.from(1, 0, 0, 1, 1, 1)));
    log.info(s.flipDigit(Arrays.from(1, 0, 1, 0, 1, 0)));
    log.info(s.flipDigit(Arrays.from(1, 1, 1, 1, 0)));
    log.info(s.flipDigit(Arrays.from(0, 1, 0, 1)));
    log.info(s.flipDigit(Arrays.from(0, 1)));
    log.info(s.flipDigit(Arrays.from(1, 0)));
  }
}
