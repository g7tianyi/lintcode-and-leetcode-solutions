package com.g7tianyi.lintcode.hashmap;

import com.g7tianyi.common.Lists;
import com.g7tianyi.common.Strings;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 02, 2019
 *
 * @link https://www.lintcode.com/problem/kill-process/description
 */
public class KillProcess {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {

      Map<Integer, List<Integer>> map = new HashMap<>();
      for (int i = 0; i < ppid.size(); ++i) {
        Integer currentPPID = ppid.get(i);
        List<Integer> children = map.getOrDefault(currentPPID, new ArrayList<>());
        children.add(pid.get(i));
        map.put(currentPPID, children);
      }

      Queue<Integer> myQueue = new LinkedList<>();
      myQueue.offer(kill);

      List<Integer> result = new ArrayList<>();

      while (!myQueue.isEmpty()) {
        Integer currentPID = myQueue.poll();
        result.add(currentPID);

        if (map.containsKey(currentPID)) {
          List<Integer> children = map.get(currentPID);
          for (Integer childPID : children) {
            myQueue.offer(childPID);
          }
        }
      }

      return result;
    }
  }

  @AllArgsConstructor
  private class Case {
    List<Integer> pid;
    List<Integer> ppid;
    int kill;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c =
        aCase -> log.info("%s", Strings.format(s.killProcess(aCase.pid, aCase.ppid, aCase.kill)));

    c.accept(new Case(Lists.from(1, 3, 10, 5), Lists.from(3, 0, 5, 3), 5));
    c.accept(new Case(Lists.from(1, 2, 3), Lists.from(0, 1, 1), 2));
  }
}
