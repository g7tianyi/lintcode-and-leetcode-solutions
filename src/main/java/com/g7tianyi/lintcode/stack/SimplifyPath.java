package com.g7tianyi.lintcode.stack;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.LinkedList;

/**
 * Created by g7tianyi on Oct 26, 2019
 *
 * @link https://www.lintcode.com/problem/simplify-path/description
 */
public class SimplifyPath {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public String simplifyPath(String path) {
      String[] splits = path.split("/");
      LinkedList<String> stack = new LinkedList<>();
      for (String split : splits) {
        if (split.isEmpty() || split.equals(".")) {
          continue;
        }
        if (split.equals("..")) {
          if (!stack.isEmpty()) {
            stack.removeLast();
          }
        } else {
          stack.addLast(split);
        }
      }

      StringBuilder sb = new StringBuilder("/");
      for (String str : stack) {
        sb.append(str).append("/");
      }

      if (sb.length() > 1 && sb.charAt(sb.length() - 1) == '/') {
        sb.deleteCharAt(sb.length() - 1);
      }
      return sb.toString();
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.simplifyPath("/home/"));
    log.info(s.simplifyPath("/home//ubuntu/asa/../"));
    log.info(s.simplifyPath("/a/./../../c/"));
    log.info(s.simplifyPath("/a/./../../../../../c/"));
    log.info(s.simplifyPath("/../"));
  }
}
