package com.g7tianyi.lintcode.tree.traversal;

import com.g7tianyi.common.DirectedGraphNode;
import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by g7tianyi on Oct 25, 2019
 *
 * @link https://www.lintcode.com/problem/n-ary-tree-level-order-traversal/description
 */
public class NaryTreeLevelOrderTraversal {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public List<List<Integer>> levelOrder(ArrayList<DirectedGraphNode> nodes) {

      List<List<Integer>> result = new ArrayList<>();

      if (nodes == null || nodes.isEmpty()) {
        return result;
      }

      Queue<DirectedGraphNode> levelQueue = new LinkedList<>();
      levelQueue.add(nodes.get(0));
      levelQueue.add(null);

      List<Integer> level = new ArrayList<>();
      while (!levelQueue.isEmpty()) {
        DirectedGraphNode node = levelQueue.poll();
        if (node == null) {
          result.add(new ArrayList<>(level));
          level = new ArrayList<>();
          if (!levelQueue.isEmpty()) {
            levelQueue.offer(null);
          }
        } else {
          level.add(node.label);
          for (DirectedGraphNode next : node.neighbors) {
            levelQueue.offer(next);
          }
        }
      }
      return result;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {}
}
