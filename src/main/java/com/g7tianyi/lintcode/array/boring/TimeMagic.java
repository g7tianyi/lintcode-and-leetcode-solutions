package com.g7tianyi.lintcode.array.boring;

import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Oct 24, 2019
 *
 * @link https://www.lintcode.com/problem/time-magic/description
 */
public class TimeMagic {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public char[] timeMagic(char[] newTime) {

      if (newTime[0] == '?') {
        if (newTime[1] == '?') {
          newTime[0] = '2';
          newTime[1] = '3';
        } else if (newTime[1] < '4') {
          newTime[0] = '2';
        } else {
          newTime[0] = '1';
        }
      }

      if (newTime[1] == '?') {
        if (newTime[0] < '2') {
          newTime[1] = '9';
        } else {
          newTime[1] = '3';
        }
      }

      if (newTime[3] == '?') {
        newTime[3] = '5';
      }

      if (newTime[4] == '?') {
        newTime[4] = '9';
      }

      return newTime;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.timeMagic("?4:5?".toCharArray()));
    log.info(s.timeMagic("0?:??".toCharArray()));
    log.info(s.timeMagic("??:??".toCharArray()));
    log.info(s.timeMagic("?5:??".toCharArray()));
    log.info(s.timeMagic("?2:??".toCharArray()));
    log.info(s.timeMagic("?0:1?".toCharArray()));
    log.info(s.timeMagic("?0:?3".toCharArray()));
  }
}
