package com.g7tianyi.lintcode.search;

import com.g7tianyi.util.Logger;
import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.*;

/**
 * Created by g7tianyi on Sep 21, 2019
 *
 * @link https://www.lintcode.com/problem/word-ladder/description
 */
public class WordLadder {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    private class Node {
      boolean sinker;
      List<Node> siblings;

      public Node(boolean sinker) {
        this.sinker = sinker;
        this.siblings = new ArrayList<>();
      }
    }

    public int ladderLength(String start, String end, Set<String> dict) {
      Node source = buildGroup(start, end, dict);
      return breadthFirstSearch(source);
    }

    private int breadthFirstSearch(Node source) {

      Queue<Node> myQueue = new LinkedList<>();
      Set<Node> visited = new HashSet<>();

      myQueue.offer(source);
      myQueue.offer(null);
      visited.add(source);

      int result = 1;
      while (!myQueue.isEmpty()) {
        Node node = myQueue.poll();
        if (node == null) {
          ++result;
          if (!myQueue.isEmpty()) {
            myQueue.offer(null);
          }
          continue;
        }

        if (node.sinker) {
          return result;
        }

        for (Node sibling : node.siblings) {
          if (!visited.contains(sibling)) {
            myQueue.offer(sibling);
            visited.add(sibling);
          }
        }
      }

      return 0;
    }

    private Node buildGroup(String start, String end, Set<String> dict) {

      Map<String, Node> map = new HashMap<>();
      Node source = new Node(false);
      Node sinker = new Node(true);
      map.put(start, source);
      map.put(end, sinker);
      for (String w : dict) {
        if (w.equals(start) || w.equals(end)) {
          continue;
        }
        map.put(w, new Node(false));
      }

      if (isSibling(start, end)) {
        connectNode(map.get(start), map.get(end));
      }

      for (String w1 : dict) {
        if (isSibling(w1, start)) {
          connectNode(map.get(w1), map.get(start));
        }
        if (isSibling(w1, end)) {
          connectNode(map.get(w1), map.get(end));
        }
        for (String w2 : dict) {
          if (!w1.equals(w2) && isSibling(w1, w2)) {
            connectNode(map.get(w1), map.get(w2));
          }
        }
      }

      return source;
    }

    private void connectNode(Node n1, Node n2) {
      n1.siblings.add(n2);
      n2.siblings.add(n1);
    }

    private boolean isSibling(String w1, String w2) {
      int d = 0;
      for (int i = 0; i < w1.length(); ++i) {
        if (w1.charAt(i) == w2.charAt(i)) {
          continue;
        }
        ++d;
        if (d > 1) {
          return false;
        }
      }
      return d == 1;
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    log.info(s.ladderLength("hit", "cog", Sets.newHashSet("hot", "dot", "dog", "hit", "lot", "log")));
    log.info(s.ladderLength("aa", "cc", Sets.newHashSet("ab", "ac")));
    log.info(s.ladderLength("aa", "cc", Sets.newHashSet("ab", "bc", "cd")));
    log.info(s.ladderLength("a", "c", Sets.newHashSet("a", "b", "c")));
  }
}
