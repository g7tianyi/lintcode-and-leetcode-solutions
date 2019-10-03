package com.g7tianyi.lintcode.search;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.LinkedList;

/**
 * Created by g7tianyi on Oct 03, 2019
 *
 * @link https://www.lintcode.com/problem/find-the-missing-number-ii/description
 */
public class FindTheMissingNumber2 {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int findMissing2(int num, String str) {
      return new Resolver(str, num).resolve();
    }

    private class Resolver {

      private LinkedList<Integer> path;
      private boolean[] exist;
      private String str;
      private int num;

      private int result;

      public Resolver(String str, int num) {
        this.str = str;
        this.num = num;
        this.exist = new boolean[num + 1];

        this.path = new LinkedList<>();
      }

      public int resolve() {
        search(0);
        return result;
      }

      private boolean search(int index) {
        if (index == str.length()) {
          int count = 0;
          for (int i = 1; i < exist.length; ++i) {
            if (!exist[i]) {
              result = i;
              ++count;
              if (count > 1) {
                break;
              }
            }
          }
          return count == 1;
        }

        if (str.charAt(index) == '0') {
          return false;
        }

        int prev = 0, curr;
        for (int pos = index; pos < str.length(); ++pos) {
          curr = prev * 10 + str.charAt(pos) - '0';
          if (curr > num) {
            break;
          }

          if (!exist[curr]) {
            exist[curr] = true;
            path.addLast(curr);
            if (search(pos + 1)) {
              log.info(path);
              return true;
            }
            path.removeLast();
            exist[curr] = false;
          }

          prev = curr;
        }

        return false;
      }
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.findMissing2(11, "111098765432"));
    // log.info(s.findMissing2(20, "19201234567891011121314151618"));
    // log.info(s.findMissing2(6, "56412"));
  }
}
