package com.g7tianyi.lintcode.graph;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by g7tianyi on Nov 15, 2019
 *
 * @link https://www.lintcode.com/problem/graph-valid-tree/description
 */
public class GraphValidTree {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    class Node {
      int value;
      List<Node> siblings;

      public Node(int value) {
        this.value = value;
        this.siblings = new ArrayList<>();
      }
    }

    public boolean validTree(int n, int[][] edges) {

      if (n == 1) {
        return true;
      }

      Node[] nodeHash = new Node[n];
      Node node1, node0;
      for (int[] edge : edges) {
        if (nodeHash[edge[0]] == null) {
          nodeHash[edge[0]] = new Node(edge[0]);
        }
        if (nodeHash[edge[1]] == null) {
          nodeHash[edge[1]] = new Node(edge[1]);
        }

        node0 = nodeHash[edge[0]];
        node1 = nodeHash[edge[1]];
        node0.siblings.add(node1);
        node1.siblings.add(node0);
      }

      boolean[] visit = new boolean[n];
      if (isInvalid(nodeHash[0], -1, visit)) {
        return false;
      }

      for (boolean vis : visit) {
        if (!vis) {
          return false;
        }
      }
      return true;
    }

    private boolean isInvalid(Node node, int prev, boolean[] visit) {
      if (node == null) {
        return true;
      }

      visit[node.value] = true;
      for (Node sibling : node.siblings) {
        if (sibling.value != prev && (visit[sibling.value] || isInvalid(sibling, node.value, visit))) {
          return true;
        }
      }
      return false;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.validTree(1, new int[][] {}));
    log.info(s.validTree(2, new int[][] {}));
    log.info(s.validTree(4, new int[][] {{0, 1}, {3, 2}}));
    log.info(s.validTree(5, new int[][] {{0, 1}, {0, 2}, {0, 3}, {1, 4}}));
    log.info(s.validTree(5, new int[][] {{0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}}));
  }
}
