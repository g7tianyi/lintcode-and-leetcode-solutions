package com.g7tianyi.lintcode.graph;

import com.g7tianyi.common.UndirectedGraphNode;
import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * Created by g7tianyi on Nov 14, 2019
 *
 * @link https://www.lintcode.com/problem/clone-graph/description
 */
public class CloneGraph {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {

      if (node == null) {
        return null;
      }

      Queue<UndirectedGraphNode> oldQueue = new LinkedList<>();
      Queue<UndirectedGraphNode> newQueue = new LinkedList<>();

      oldQueue.offer(node);
      UndirectedGraphNode copy = new UndirectedGraphNode(node.label);
      newQueue.offer(copy);

      Map<Integer, UndirectedGraphNode> map = new HashMap<>();
      map.put(copy.label, copy);

      while (!oldQueue.isEmpty()) {
        UndirectedGraphNode oldNode = oldQueue.poll();
        UndirectedGraphNode newNode = newQueue.poll();

        for (UndirectedGraphNode oldNeighbor : oldNode.neighbors) {
          UndirectedGraphNode newNeighbor;
          if (!map.containsKey(oldNeighbor.label)) {
            newNeighbor = new UndirectedGraphNode(oldNeighbor.label);
            map.put(newNeighbor.label, newNeighbor);
            oldQueue.offer(oldNeighbor);
            newQueue.offer(newNeighbor);
          } else {
            newNeighbor = map.get(oldNeighbor.label);
          }

          newNode.neighbors.add(newNeighbor);
        }
      }

      return copy;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    UndirectedGraphNode node1 = new UndirectedGraphNode(1);
    UndirectedGraphNode node2 = new UndirectedGraphNode(2);
    UndirectedGraphNode node3 = new UndirectedGraphNode(4);

    node1.neighbors.add(node2);
    node1.neighbors.add(node3);

    node2.neighbors.add(node1);
    node2.neighbors.add(node3);

    node3.neighbors.add(node1);
    node3.neighbors.add(node2);

    s.cloneGraph(node1);
  }
}
