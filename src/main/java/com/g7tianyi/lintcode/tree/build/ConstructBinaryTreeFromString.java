package com.g7tianyi.lintcode.tree.build;

import com.g7tianyi.common.TreeNode;
import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Oct 26, 2019
 *
 * @link https://www.lintcode.com/problem/construct-binary-tree-from-string/description
 */
public class ConstructBinaryTreeFromString {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public TreeNode str2tree(String s) {

      if (s == null || s.isEmpty()) {
        return null;
      }

      return build(s, 0, s.length() - 1);
    }

    private TreeNode build(String s, int start, int end) {

      int num = 0;
      int sign = 1;
      char ch;
      while (start <= end) {
        ch = s.charAt(start);
        if (ch == '-') {
          sign = -1;
        } else if (ch >= '0' && ch <= '9') {
          num = num * 10 + (ch - '0');
        } else {
          break;
        }
        ++start;
      }

      TreeNode root = new TreeNode(sign * num);

      ++start;
      int prevStart = start;
      int leftBrackets = 1;
      while (start <= end) {
        ch = s.charAt(start);
        if (ch == ')') {
          --leftBrackets;
          if (leftBrackets == 0) {
            break;
          }
        } else if (ch == '(') {
          ++leftBrackets;
        }
        ++start;
      }
      if (prevStart <= start - 1) {
        root.left = build(s, prevStart, start - 1);
      }

      ++start;
      if (start < end && s.charAt(start) == '(' && s.charAt(end) == ')') {
        root.right = build(s, start + 1, end - 1);
      }

      return root;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.str2tree("-4(2(3)(1))(6(5))").serialize());
    log.info(s.str2tree("1(-122)").serialize());
    log.info(s.str2tree("122(-12)").serialize());
    log.info(s.str2tree("-122(-12)").serialize());
  }
}
