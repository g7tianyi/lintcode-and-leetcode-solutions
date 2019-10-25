package com.g7tianyi.lintcode.tree.traversal;

import com.g7tianyi.common.UndirectedGraphNode;
import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by g7tianyi on Oct 25, 2019
 *
 * @link https://www.lintcode.com/problem/n-ary-tree-preorder-traversal/description
 */
public class NaryTreePreorderTraversal {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public List<Integer> preorder(UndirectedGraphNode root) {

      return loop(root);
    }

    private List<Integer> loop(UndirectedGraphNode root) {

      List<Integer> result = new ArrayList<>();
      if (root == null) {
        return result;
      }

      Stack<UndirectedGraphNode> stack = new Stack<>();
      stack.push(root);
      while (!stack.isEmpty()) {
        UndirectedGraphNode node = stack.pop();
        result.add(node.label);
        if (node.neighbors != null) {
          for (int i = node.neighbors.size() - 1; i >= 0; --i) { // 一定要逆序入栈
            stack.push(node.neighbors.get(i));
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

      result.add(root.label);
      if (root.neighbors != null && !root.neighbors.isEmpty()) {
        for (UndirectedGraphNode next : root.neighbors) {
          recursion(next, result);
        }
      }
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {}
}
