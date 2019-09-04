package com.g7tianyi.lintcode.string.match;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 04, 2019
 *
 * @link https://www.lintcode.com/problem/first-position-unique-character/description
 */
public class FirstPositionUniqueCharacter {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int firstUniqChar(String s) {
      int[] pos = new int[256];
      for (int i = 0; i < 256; ++i) {
        pos[i] = -1;
      }

      for (int i = 0; i < s.length(); ++i) {
        if (pos[s.charAt(i)] == -1) { // never appears
          pos[s.charAt(i)] = i;
        } else {
          pos[s.charAt(i)] = -2; // repeated
        }
      }

      int min = -1;
      for (int i = 0; i < 256; ++i) {
        if (pos[i] >= 0) {
          if (min == -1) {
            min = pos[i];
          } else {
            min = Math.min(min, pos[i]);
          }
        }
      }
      return min;
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<String> c =
        str -> {
          log.info(str);
          log.info(s.firstUniqChar(str));
          log.info();
        };

    c.accept("lintcode");
    c.accept("lovelintcode");
    c.accept("aabbcc");
  }
}
