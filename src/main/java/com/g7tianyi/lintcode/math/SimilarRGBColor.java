package com.g7tianyi.lintcode.math;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 03, 2019
 *
 * @link https://www.lintcode.com/problem/similar-rgb-color/description
 */
public class SimilarRGBColor {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public String similarRGB(String color) {

      String[] options =
          new String[] {
            "00", "11", "22", "33", "44", "55", "66", "77", "88", "99", "aa", "bb", "cc", "dd",
            "ee", "ff"
          };

      if (color.startsWith("#")) {
        color = color.substring(1);
      }

      int value = Integer.MAX_VALUE, tempValue;
      String result = null, tempColor;
      for (String option : options) {
        for (String option1 : options) {
          for (String option2 : options) {
            tempColor = option + option1 + option2;
            tempValue =
                differenceSquare(tempColor.substring(0, 2), color.substring(0, 2))
                    + differenceSquare(tempColor.substring(2, 4), color.substring(2, 4))
                    + differenceSquare(tempColor.substring(4, 6), color.substring(4, 6));
            if (value > tempValue) {
              value = tempValue;
              result = tempColor;
            }
          }
        }
      }

      return "#" + result;
    }

    private int differenceSquare(String c1, String c2) {
      int diff = hexToDecimal(c1) - hexToDecimal(c2);
      return diff * diff;
    }

    private int hexToDecimal(String hex) {
      char ch;
      int num = 0;
      for (int i = 0; i < hex.length(); ++i) {
        num *= 16;
        ch = hex.charAt(i);
        if (ch >= '0' && ch <= '9') {
          num += (ch - '0');
        } else {
          num += (ch - 'a' + 10);
        }
      }
      return num;
    }
  }

  @AllArgsConstructor
  private class Case {
    String color;
    String expect;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c =
        aCase -> {
          String result = s.similarRGB(aCase.color);
          log.info("%s => %s", aCase.color, result);
          Assert.assertEquals(aCase.expect, result);
        };

    // "#09f166"
    c.accept(new Case("#09f166", "#11ee66"));
    c.accept(new Case("#010000", "#000000"));
  }
}
