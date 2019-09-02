package com.g7tianyi.lintcode.string.reverse;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 25, 2019
 *
 * @link https://www.lintcode.com/problem/reverse-words-in-a-string/description
 */
public class ReverseWordsInAString {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public String reverseWords(String s) {

      StringBuilder sb = new StringBuilder();
      int i = 0, len = s.length();
      while (true) {
        while (i < len && s.charAt(i) == ' ') {
          i++;
        }
        if (i >= len) {
          break;
        }

        int j = i;
        StringBuilder wb = new StringBuilder();
        while (j < len && s.charAt(j) != ' ') {
          wb.append(s.charAt(j));
          j++;
        }
        reverseWord(wb);
        sb.append(wb).append(' ');

        i = j;
      }

      if (sb.length() > 0 && sb.charAt(sb.length() - 1) == ' ') {
        sb.deleteCharAt(sb.length() - 1);
      }

      reverseWord(sb);

      return sb.toString();
    }

    // 相当于自己实现一下 sb.reverse()
    private void reverseWord(StringBuilder sb) {
      int i = 0, j = sb.length() - 1;
      while (i < j) {
        char ch = sb.charAt(i);
        sb.setCharAt(i, sb.charAt(j));
        sb.setCharAt(j, ch);
        i++;
        j--;
      }
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<String> c = str -> log.info("[%s] => [%s]", str, s.reverseWords(str));

    c.accept("the sky is blue");
    c.accept("hello world");
    c.accept("hello  world");
    c.accept("   hello  world");
    c.accept("hello  world   ");
    c.accept("   hello world    ");
    c.accept("   hello  world    ");
    c.accept("       ");
  }
}
