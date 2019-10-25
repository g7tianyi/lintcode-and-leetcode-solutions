package com.g7tianyi.lintcode.heap;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by g7tianyi on Oct 25, 2019
 *
 * @link https://www.lintcode.com/problem/top-k-frequent-words/description
 */
public class TopKFrequentWords {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    class Node {
      String word;
      int count;

      public Node(String word, int count) {
        this.word = word;
        this.count = count;
      }
    }

    public String[] topKFrequentWords(String[] words, int K) {

      Map<String, Integer> map = new HashMap<>();
      for (String word : words) {
        map.put(word, map.getOrDefault(word, 0) + 1);
      }

      PriorityQueue<Node> topQueue =
          new PriorityQueue<>(
              new Comparator<Node>() {
                @Override
                public int compare(Node o1, Node o2) {
                  if (o1.count != o2.count) {
                    return Integer.compare(o1.count, o2.count);
                  }
                  return o2.word.compareTo(o1.word);
                }
              });

      for (Map.Entry<String, Integer> entry : map.entrySet()) {
        topQueue.offer(new Node(entry.getKey(), entry.getValue()));
        if (topQueue.size() > K) {
          topQueue.poll();
        }
      }

      String[] result = new String[K];
      while (!topQueue.isEmpty()) {
        result[K-1] = topQueue.poll().word;
        --K;
      }
      return result;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(
        s.topKFrequentWords(
            new String[] {
              "yes", "lint", "code",
              "yes", "code", "baby",
              "you", "baby", "chrome",
              "safari", "lint", "code",
              "body", "lint", "code"
            },
            3));

    log.info(
        s.topKFrequentWords(
            new String[] {
              "yes", "lint", "code",
              "yes", "code", "baby",
              "you", "baby", "chrome",
              "safari", "lint", "code",
              "body", "lint", "code"
            },
            4));

    log.info(
        s.topKFrequentWords(
            new String[] {
                "yes", "lint", "code",
                "yes", "code", "baby",
                "you", "baby", "chrome",
                "safari", "lint", "code",
                "body", "lint", "code"
            },
            1));
  }
}
