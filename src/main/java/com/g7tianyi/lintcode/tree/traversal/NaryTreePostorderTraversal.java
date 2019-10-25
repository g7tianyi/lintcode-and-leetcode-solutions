package com.g7tianyi.lintcode.tree.traversal;

import com.g7tianyi.common.UndirectedGraphNode;
import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by g7tianyi on Oct 25, 2019
 *
 * @link https://www.lintcode.com/problem/n-ary-tree-postorder-traversal/description
 */
public class NaryTreePostorderTraversal {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public List<Integer> postorder(UndirectedGraphNode root) {

      return recursion(root);
    }

    private List<Integer> loop(UndirectedGraphNode root) {

      LinkedList<Integer> result = new LinkedList<>();
      if (root == null) {
        return result;
      }

      Stack<UndirectedGraphNode> stack = new Stack<>();
      stack.push(root);
      while (!stack.isEmpty()) {
        UndirectedGraphNode node = stack.pop();
        result.addFirst(node.label);
        if (root.neighbors != null) {
          for (UndirectedGraphNode next : node.neighbors) { // 顺序入栈
            stack.push(next);
          }
        }
      }

      return result;
    }

    private List<Integer> recursion(UndirectedGraphNode root) {

      List<Integer> result = new ArrayList<>();

      if (root == null) {
        return result;
      }

      recursion(root, result);

      return result;
    }

    private void recursion(UndirectedGraphNode root, List<Integer> result) {

      if (root == null) {
        return;
      }

      if (root.neighbors != null) {
        for (UndirectedGraphNode next : root.neighbors) {
          recursion(next, result);
        }
      }
      result.add(root.label);
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {}
}
