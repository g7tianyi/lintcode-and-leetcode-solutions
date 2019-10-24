package com.g7tianyi.lintcode.string;

import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Oct 24, 2019
 *
 * @link https://www.lintcode.com/problem/remove-arbitrary-space/description
 */
public class RemoveArbitrarySpace {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public String removeExtra(String s) {
      StringBuilder sb = new StringBuilder();
      char[] chars = s.toCharArray();
      char prev = ' ';
      for (char ch : chars) {
        if (ch == ' ') {
          if (prev != ' ') {
            sb.append(ch);
            prev = ch;
          }
        } else {
          sb.append(ch);
          prev = ch;
        }
      }

      while (sb.length() > 0 && sb.charAt(sb.length() - 1) == ' ') {
        sb.deleteCharAt(sb.length() - 1);
      }
      return sb.toString();
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info("[%s]", s.removeExtra("The  sky   is blue "));
    log.info("[%s]", s.removeExtra("  lower         case  "));
    log.info("[%s]", s.removeExtra("   "));
  }
}
