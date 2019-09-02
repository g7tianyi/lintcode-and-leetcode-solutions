package com.g7tianyi.lintcode.stack;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 02, 2019
 *
 * @link https://www.lintcode.com/problem/next-greater-element-i/description
 */
public class NextGreaterElement1 {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
      Map<Integer, Integer> map = new HashMap<>();
      Stack<Integer> stack = new Stack<>();

      int i = nums2.length - 1;
      stack.push(nums2[i--]);
      for (int curr; i >= 0; --i) {
        curr = nums2[i];
        while (!stack.isEmpty()) {
          if (stack.peek() <= curr) {
            stack.pop();
          } else {
            map.put(curr, stack.peek());
            break;
          }
        }
        stack.push(curr);
      }

      int[] result = new int[nums1.length];
      for (i = 0; i < nums1.length; ++i) {
        result[i] = map.getOrDefault(nums1[i], -1);
      }
      return result;
    }
  }

  @AllArgsConstructor
  private class Case {
    int[] nums1;
    int[] nums2;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c =
        aCase -> {
          log.info(s.nextGreaterElement(aCase.nums1, aCase.nums2));
          log.info();
        };

    c.accept(new Case(Arrays.from(4, 1, 2), Arrays.from(1, 3, 4, 2)));
    c.accept(new Case(Arrays.from(2, 4), Arrays.from(1, 2, 3, 4)));
    c.accept(new Case(Arrays.from(10, 6, 7, 11), Arrays.from(10, 6, 7, 11)));
    c.accept(new Case(Arrays.from(8, 4, 5, 10, 6, 7, 11), Arrays.from(8, 4, 5, 10, 6, 7, 11)));
  }
}
