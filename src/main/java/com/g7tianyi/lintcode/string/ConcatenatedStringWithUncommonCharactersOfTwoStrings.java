package com.g7tianyi.lintcode.string;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 26, 2019
 *
 * @link
 * https://www.lintcode.com/problem/concatenated-string-with-uncommon-characters-of-two-strings/description
 */
public class ConcatenatedStringWithUncommonCharactersOfTwoStrings {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public String concatenetedString(String s1, String s2) {
      int len1 = s1.length();
      int len2 = s2.length();

      int[] pos1 = new int[256];
      int[] pos2 = new int[256];
      for (int i = 0; i < 256; i++) {
        pos1[i] = 0;
        pos2[i] = 0;
      }

      int i = 0;
      while (i < len1 && i < len2) {
        pos1[s1.charAt(i)] = 1;
        pos2[s2.charAt(i)] = 1;
        i++;
      }

      while (i < len1) {
        pos1[s1.charAt(i)] = 1;
        i++;
      }

      while (i < len2) {
        pos2[s2.charAt(i)] = 1;
        i++;
      }

      StringBuilder sb = new StringBuilder();
      char ch;
      i = 0;
      while (i < len1) {
        ch = s1.charAt(i);
        if (pos1[ch] == 0 || pos2[ch] == 0) {
          sb.append(ch);
        }
        i++;
      }

      i = 0;
      while (i < len2) {
        ch = s2.charAt(i);
        if (pos1[ch] == 0 || pos2[ch] == 0) {
          sb.append(ch);
        }
        i++;
      }

      return sb.toString();
    }
  }

  @AllArgsConstructor
  private static class Input {

    private String s1;
    private String s2;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Input> c =
        input -> {
          log.info("[%s]", input.s1);
          log.info("[%s]", input.s2);
          log.info("[%s]", s.concatenetedString(input.s1, input.s2));
          log.info();
        };

    c.accept(new Input("aacdb", "gafd"));
    c.accept(new Input("abcs", "cxzca"));
    c.accept(new Input("aacdb", ""));
  }
}
