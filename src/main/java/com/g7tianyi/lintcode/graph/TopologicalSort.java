package com.g7tianyi.lintcode.graph;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.*;

/**
 * Created by g7tianyi on Oct 23, 2019
 *
 * @link https://www.lintcode.com/problem/topological-sorting/description
 */
public class TopologicalSort {

  private static final Logger log = Logger.getInstance();

  private class DirectedGraphNode {
    int label;
    ArrayList<DirectedGraphNode> neighbors;

    DirectedGraphNode(int x) {
      label = x;
      neighbors = new ArrayList<>();
    }
  }

  public class Solution {

    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
      ArrayList<DirectedGraphNode> result = new ArrayList<>();
      Map<DirectedGraphNode, Integer> map = new HashMap<>();
      for (DirectedGraphNode node : graph) {
        for (DirectedGraphNode neighbor : node.neighbors) {
          if (map.containsKey(neighbor)) {
            map.put(neighbor, map.get(neighbor) + 1);
          } else {
            map.put(neighbor, 1);
          }
        }
      }

      Queue<DirectedGraphNode> myQueue = new LinkedList<>();
      for (DirectedGraphNode node : graph) {
        if (!map.containsKey(node)) {
          myQueue.offer(node);
          result.add(node);
        }
      }
      while (!myQueue.isEmpty()) {
        DirectedGraphNode node = myQueue.poll();
        for (DirectedGraphNode n : node.neighbors) {
          map.put(n, map.get(n) - 1);
          if (map.get(n) == 0) {
            result.add(n);
            myQueue.offer(n);
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
