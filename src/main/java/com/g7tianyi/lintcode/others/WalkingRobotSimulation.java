package com.g7tianyi.lintcode.others;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 1, 2019
 *
 * @link https://www.lintcode.com/problem/walking-robot-simulation/description
 */
public class WalkingRobotSimulation {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int robotSim(int[] commands, int[][] obstacles) {

      Set<String> set = new HashSet<>();
      for (int[] obstacle : obstacles) {
        String key = String.format("%d-%d", obstacle[0], obstacle[1]);
        set.add(key);
      }

      // | Index | Direction | (dx, dy) |
      // |------------------------------|
      // | 0     | north     | ( 0, 1 ) |
      // | 1     | east      | ( 1, 0 ) |
      // | 2     | south     | ( 0, -1) |
      // | 3     | west      | (-1,  0) |
      int[][] deltas = {
        {0, 1},
        {1, 0},
        {0, -1},
        {-1, 0},
      };

      int d = 0; // direction
      int x = 0, y = 0;
      for (int command : commands) {
        if (command == -1) { // -1: turn right 90 degrees
          d = (d + 1) % 4;
        } else if (command == -2) { // -2: turn left 90 degrees
          d = (d + 4 - 1) % 4;
        } else {
          for (int i = 0, nx, ny; i < command; ++i) {
            nx = x + deltas[d][0];
            ny = y + deltas[d][1];
            if (set.contains(String.format("%d-%d", nx, ny))) {
              break;
            }
            x = nx;
            y = ny;
          }
        }
      }

      return x * x + y * y;
    }
  }

  @AllArgsConstructor
  public static class Case {
    private int[] commands;
    private int[][] obstacles;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> runner =
        aCase -> {
          log.info(s.robotSim(aCase.commands, aCase.obstacles));
          log.info();
        };

    runner.accept(new Case(new int[] {4, -1, 3}, new int[][] {}));
    runner.accept(new Case(new int[] {4, -1, 4, -2, 4}, new int[][] {{2, 4}}));
  }
}
