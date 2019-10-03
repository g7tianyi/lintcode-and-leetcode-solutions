package com.g7tianyi.lintcode.graph;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Oct 03, 2019
 *
 * @link https://www.lintcode.com/problem/all-paths-from-source-to-target/description
 */
public class AllPathsFromSourceToTarget {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {

      List<List<Integer>> result = new ArrayList<>();

      if (graph == null || graph.length == 0) {
        return result;
      }

      boolean[] visit = new boolean[graph.length];

      dfs(graph, visit, 0, new LinkedList<>(), result);

      return result;
    }

    private void dfs(
        int[][] graph,
        boolean[] visit,
        int curr,
        LinkedList<Integer> path,
        List<List<Integer>> paths) {

      if (curr == graph.length - 1) {
        LinkedList<Integer> result = new LinkedList<>(path);
        result.addLast(graph.length - 1);
        paths.add(result);
        return;
      }

      visit[curr] = true;
      path.addLast(curr);

      for (int next : graph[curr]) {
        if (!visit[next]) {
          dfs(graph, visit, next, path, paths);
        }
      }

      visit[curr] = false;
      path.removeLast();
    }
  }

  private final Solution s = new Solution();

  private final Consumer<int[][]> c =
      graph -> {
        s.allPathsSourceTarget(graph).forEach(log::info);
        log.info();
      };

  @Test
  public void test() {
    c.accept(new int[][] {{1, 2}, {3}, {3}, {}});
    c.accept(new int[][] {{1, 3}, {2}, {3}, {}});
  }
}
