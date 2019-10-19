package com.g7tianyi.lintcode.others;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Oct 19, 2019
 *
 * @link https://www.lintcode.com/problem/lexicographical-numbers/description
 */
public class LexicographicalNumbers {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public List<Integer> lexicalOrder(int n) {

      List<Integer> result = new ArrayList<>();

      int value = 1;
      for (int i = 1; i <= n; i++) {
        result.add(value);
        if (value * 10 <= n) { // 增加一位数
          value *= 10;
        } else if (value % 10 != 9 && value + 1 <= n) { // 逐渐往上+1
          ++value;
        } else {
          // 处理value的值类似于 19999
          while ((value / 10) % 10 == 9) {
            value /= 10;
          }
          value = value / 10 + 1;
        }
      }

      return result;
    }
  }

  // 会超时，但作为数据结构的选型我觉得挺吸引~ 😆
  public class TrieSolution {

    // 类似于Trie树
    class Node {

      private int number;

      private boolean accessed;

      private List<Node> children;

      public Node(int number) {
        this.number = number;
        this.accessed = false;
        this.children = new ArrayList<>();
      }

      public void insert(int value) {
        this.accessed = true;
        insert(toDigits(value));
      }

      private void insert(LinkedList<Integer> digits) {

        int number = digits.removeFirst();
        if (this.children.isEmpty()) {
          for (int i = 0; i < 10; ++i) {
            this.children.add(new Node(i));
          }
        }

        Node child = children.get(number);
        child.accessed = true;

        if (!digits.isEmpty()) {
          child.insert(digits);
        }
      }

      private LinkedList<Integer> toDigits(int value) {
        LinkedList<Integer> result = new LinkedList<>();
        while (value > 0) {
          result.addFirst(value % 10);
          value /= 10;
        }
        return result;
      }

      public List<Integer> values() {
        List<Integer> result = new ArrayList<>();
        traverse(this, 0, result);
        return result;
      }

      // DFS
      private void traverse(Node node, int prev, List<Integer> result) {

        int curr = prev * 10 + node.number;
        if (curr > 0) {
          result.add(curr);
        }

        for (Node child : node.children) {
          if (child.accessed) {
            traverse(child, curr, result);
          }
        }
      }
    }

    public List<Integer> lexicalOrder(int n) {
      Node root = new Node(0);
      for (int i = 1; i <= n; ++i) {
        root.insert(i);
      }
      return root.values();
    }
  }

  private final Solution s = new Solution();

  private final Consumer<Integer> c =
      n -> {
        if (n < 1000) {
          log.info(s.lexicalOrder(n));
        } else {
          s.lexicalOrder(n);
        }
      };

  @Test
  public void test() {
    c.accept(1);
    c.accept(13);
    c.accept(201);
  }
}
