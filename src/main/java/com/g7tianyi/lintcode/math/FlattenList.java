package com.g7tianyi.lintcode.math;

import com.g7tianyi.lintcode.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by g7tianyi on Aug 27, 2019
 *
 * @link https://www.lintcode.com/problem/flatten-list/description
 */
public class FlattenList {

  private static final Log log = new Log();

  public interface NestedInteger {

    boolean isInteger();

    Integer getInteger();

    List<NestedInteger> getList();
  }

  public static class Solution {

    public List<Integer> flatten(List<NestedInteger> nestedIntegers) {

      Stack<NestedInteger> stack = new Stack<>();
      pushToStack(nestedIntegers, stack);

      List<Integer> result = new ArrayList<>();
      while (!stack.isEmpty()) {
        NestedInteger nestedInteger = stack.pop();
        if (nestedInteger.isInteger()) {
          result.add(nestedInteger.getInteger());
        } else {
          pushToStack(nestedInteger.getList(), stack);
        }
      }

      return result;
    }

    public static void pushToStack(List<NestedInteger> nestedIntegers, Stack<NestedInteger> stack) {
      for (int i = nestedIntegers.size() - 1; i >= 0; --i) {
        NestedInteger nestedInteger = nestedIntegers.get(i);
        stack.push(nestedInteger);
      }
    }
  }
}
