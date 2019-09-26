package com.g7tianyi.lintcode.search;

import com.g7tianyi.util.Logger;
import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 26, 2019
 *
 * @link https://www.lintcode.com/problem/word-ladder-ii/description
 */
public class WordLadder2 {

  private static final Logger log = Logger.getInstance();

  public static class Solution {

    private static class Node {

      public static final char NODE_TYPE_SOURCE = '0';
      public static final char NODE_TYPE_NORMAL = '1';
      public static final char NODE_TYPE_SINKER = '2';

      char type;
      String word;
      List<Node> siblings;

      int depth;
      List<List<String>> paths;
      Set<Node> sinkerPrevs;

      public Node(String word, char type) {
        this.word = word;
        this.type = type;
        this.siblings = new ArrayList<>();

        this.depth = Integer.MAX_VALUE;
        this.paths = new ArrayList<>();

        if (this.type == NODE_TYPE_SOURCE) {
          List<String> path = new ArrayList<>();
          path.add(word);
          this.paths = new ArrayList<>();
          this.paths.add(path);
          this.depth = 0;
        } else if (this.type == NODE_TYPE_SINKER) {
          this.sinkerPrevs = new HashSet<>();
        }
      }

      public boolean isSinker() {
        return this.type == NODE_TYPE_SINKER;
      }

      public void addSibling(Node sibling) {
        if (isSinker()) {
          return;
        }
        for (Node oldNode : siblings) {
          if (oldNode.word.equals(sibling.word)) {
            return;
          }
        }
        siblings.add(sibling);
      }

      public void addPathFrom(Node node) {
        if (isSinker()) {
          sinkerPrevs.add(node);
        } else {
          for (List<String> nodePath : node.paths) {
            List<String> newPath = new ArrayList<>(nodePath);
            newPath.add(word);
            paths.add(newPath);
          }
        }
      }

      public void justify() {
        siblings.sort(
            new Comparator<Node>() {
              @Override
              public int compare(Node o1, Node o2) {
                return o1.word.compareTo(o2.word);
              }
            });
      }

      @Override
      public String toString() {
        return String.format("%s", word);
      }

      @Override
      public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return word.equals(node.word);
      }

      @Override
      public int hashCode() {
        return word.hashCode();
      }
    }

    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
      Node source = buildGroup(start, end, dict);
      return breadthFirstSearch(source);
    }

    private static List<List<String>> breadthFirstSearch(Node source) {

      Queue<Node> myQueue = new LinkedList<>();

      myQueue.offer(source);
      myQueue.offer(null);

      Set<Node> enqueued = new HashSet<>();
      enqueued.add(source);

      Node sinker = null;
      while (!myQueue.isEmpty()) {
        Node node = myQueue.poll();
        if (node == null) {
          if (sinker != null) {
            break;
          }
          if (!myQueue.isEmpty()) {
            myQueue.offer(null);
          }
        } else if (node.isSinker()) {
          sinker = node;
        } else {
          for (Node sibling : node.siblings) {
            if (sibling.depth > node.depth) { // 不能往回走
              sibling.depth = node.depth + 1;
              sibling.addPathFrom(node);
              if (!enqueued.contains(sibling)) {
                enqueued.add(sibling);
                myQueue.offer(sibling);
              }
            }
          }
        }
      }

      if (sinker == null) {
        return new ArrayList<>();
      }

      List<List<String>> result = new ArrayList<>();
      for (Node node : sinker.sinkerPrevs) {
        for (List<String> paths : node.paths) {
          paths.add(sinker.word);
          result.add(paths);
        }
      }
      return result;
    }

    private static Node buildGroup(String start, String end, Set<String> dict) {

      Map<String, Node> map = new HashMap<>();
      Node source = new Node(start, Node.NODE_TYPE_SOURCE);
      Node sinker = new Node(end, Node.NODE_TYPE_SINKER);
      map.put(start, source);
      map.put(end, sinker);
      for (String w : dict) {
        if (!w.equals(start) && !w.equals(end)) {
          map.put(w, new Node(w, Node.NODE_TYPE_NORMAL));
        }
      }

      dict.add(start);
      dict.add(end);
      for (String w1 : dict) {
        for (String w2 : dict) {
          if (!w1.equals(w2) && isSibling(w1, w2)) {
            connectNode(map.get(w1), map.get(w2));
          }
        }
      }

      for (Node node : map.values()) {
        node.justify();
      }

      return source;
    }

    private static void connectNode(Node n1, Node n2) {
      n1.addSibling(n2);
      n2.addSibling(n1);
    }

    private static boolean isSibling(String w1, String w2) {
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

  private final Solution s = new Solution();

  @AllArgsConstructor
  private class Case {
    String start;
    String end;
    Set<String> dict;
  }

  private final Consumer<Case> c =
      aCase -> {
        List<List<String>> ladders = s.findLadders(aCase.start, aCase.end, aCase.dict);
        StringBuilder sb = new StringBuilder();
        for (List<String> ladder : ladders) {
          sb.append(ladder.toString()).append("\n");
        }
        log.info(sb.toString());
      };

  @Test
  public void test() {

    c.accept(new Case("hit", "cog", Sets.newHashSet("hot", "dot", "dog", "lot", "log")));
    c.accept(new Case("a", "c", Sets.newHashSet("a", "c", "b")));

    c.accept(
        new Case(
            "qa",
            "sq",
            Sets.newHashSet(
                "si", "go", "se", "cm", "so", "ph", "mt", "db", "mb", "sb", "kr", "ln", "tm", "le",
                "av", "sm", "ar", "ci", "ca", "br", "ti", "ba", "to", "ra", "fa", "yo", "ow", "sn",
                "ya", "cr", "po", "fe", "ho", "ma", "re", "or", "rn", "au", "ur", "rh", "sr", "tc",
                "lt", "lo", "as", "fr", "nb", "yb", "if", "pb", "ge", "th", "pm", "rb", "sh", "co",
                "ga", "li", "ha", "hz", "no", "bi", "di", "hi", "qa", "pi", "os", "uh", "wm", "an",
                "me", "mo", "na", "la", "st", "er", "sc", "ne", "mn", "mi", "am", "ex", "pt", "io",
                "be", "fm", "ta", "tb", "ni", "mr", "pa", "he", "lr", "sq", "ye")));
  }
}
