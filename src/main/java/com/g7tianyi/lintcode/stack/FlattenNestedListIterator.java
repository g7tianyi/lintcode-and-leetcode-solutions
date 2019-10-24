package com.g7tianyi.lintcode.stack;

import com.g7tianyi.lintcode.list.NestedInteger;
import com.g7tianyi.util.Logger;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * Created by g7tianyi on Oct 24, 2019
 *
 * @link https://www.lintcode.com/problem/flatten-nested-list-iterator/description
 */
public class FlattenNestedListIterator {

  private static final Logger log = Logger.getInstance();

  public class NestedIterator implements Iterator<Integer> {

    Stack<NestedInteger> stack = new Stack<>();

    public NestedIterator(List<NestedInteger> nestedList) {
      if (nestedList == null || nestedList.size() == 0) {
        return;
      }

      for (int i = nestedList.size() - 1; i >= 0; i--) {
        stack.push(nestedList.get(i));
      }
    }

    @Override
    public Integer next() {
      return hasNext() ? stack.pop().getInteger() : null;
    }

    @Override
    public boolean hasNext() {
      while (!stack.isEmpty()) {
        NestedInteger nestedInteger = stack.peek();
        if (nestedInteger.isInteger()) {
          return true;
        }

        stack.pop();
        for (int i = nestedInteger.getList().size() - 1; i >= 0; i--) {
          stack.push(nestedInteger.getList().get(i));
        }
      }
      return false;
    }
  }
}
