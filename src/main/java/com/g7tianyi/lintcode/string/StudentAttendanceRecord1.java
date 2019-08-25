package com.g7tianyi.lintcode.string;

import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 25, 2019
 *
 * <p>Problem link: https://www.lintcode.com/problem/student-attendance-record-i/description
 */
public class StudentAttendanceRecord1 {

  public class Solution {

    public boolean checkRecord(String s) {
      int numA = 0; // number of A
      int conL = 0; // consecutive of L
      boolean preL = false;
      for (int i = 0; i < s.length(); i++) {
        char ch = s.charAt(i);
        if (ch == 'A') {
          ++numA;
        }
        if (numA > 1) {
          return false;
        }

        if (ch == 'L') {
          if (preL) {
            ++conL;
          } else {
            conL = 1;
            preL = true;
          }

          if (conL > 2) {
            return false;
          }
        } else {
          preL = false;
        }
      }

      return true;
    }
  }

  @AllArgsConstructor
  private static class Input {

    private String s;
    private boolean expected;
  }

  @Test
  public void test() {
    Solution s = new Solution();
    Consumer<Input> c = input -> Assert.assertEquals(input.expected, s.checkRecord(input.s));
    c.accept(new Input("PPALLP", true));
    c.accept(new Input("PPALLL", false));
  }
}
