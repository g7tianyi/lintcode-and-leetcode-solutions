package com.g7tianyi.lintcode.string.match;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 03, 2019
 *
 * @link
 */
public class ValidNumber {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public boolean isNumber(String s) {
      int len = s.length();
      int i = 0, e = len - 1;
      while (i <= e && Character.isWhitespace(s.charAt(i))) {
        i++;
      }
      if (i > len - 1) {
        return false;
      }

      while (e >= i && Character.isWhitespace(s.charAt(e))) {
        e--;
      }
      // skip leading +/-
      if (s.charAt(i) == '+' || s.charAt(i) == '-') {
        i++;
      }

      boolean num = false; // is a digit
      boolean dot = false; // is a '.'
      boolean exp = false; // is a 'e'
      while (i <= e) {
        char c = s.charAt(i);
        if (Character.isDigit(c)) {
          num = true;
        } else if (c == '.') {
          if (exp || dot) {
            return false;
          }
          dot = true;
        } else if (c == 'e') {
          if (exp || !num) {
            return false;
          }
          exp = true;
          num = false;
        } else if (c == '+' || c == '-') {
          if (s.charAt(i - 1) != 'e') {
            return false;
          }
        } else {
          return false;
        }
        i++;
      }
      return num;
    }
  }
}
