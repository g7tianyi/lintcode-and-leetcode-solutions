package com.g7tianyi.lintcode.dp.interval;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 25, 2019
 *
 * @link https://www.lintcode.com/problem/frog-jump/description
 */
public class FrogJump {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public boolean canCross(int[] stones) {

      if (stones == null || stones.length == 0) {
        return false;
      } else if (stones.length == 1) {
        return stones[0] == 0;
      } else if (stones.length == 2 && stones[1] != 1) {
        return false;
      }

      // F[i]的值是一个数组
      // F[i][j] = P表示位置i可以从前面某个位置跳P步到达j
      Map<Integer, Set<Integer>> F = new HashMap<>();
      Set<Integer> zeros = new HashSet<>();
      zeros.add(0);
      F.put(0, zeros);
      Set<Integer> ones = new HashSet<>();
      ones.add(1);
      F.put(1, ones);

      int len = stones.length;
      Map<Integer, Integer> pos = new HashMap<>();
      for (int i = 2; i < len; ++i) {
        pos.put(stones[i], i);
      }

      for (int i = 1; i < len; ++i) {
        if (!F.containsKey(i)) {
          continue;
        }

        Set<Integer> baseJumps = F.get(i);
        for (Integer baseJump : baseJumps) {
          Integer destination;
          if (baseJump > 1 && pos.containsKey(stones[i] + baseJump - 1)) {
            destination = pos.get(stones[i] + baseJump - 1);
            Set<Integer> s = F.getOrDefault(destination, new HashSet<>());
            s.add(baseJump - 1);
            F.put(destination, s);
          }

          if (pos.containsKey(stones[i] + baseJump)) {
            destination = pos.get(stones[i] + baseJump);
            Set<Integer> s = F.getOrDefault(destination, new HashSet<>());
            s.add(baseJump);
            F.put(destination, s);
          }

          if (pos.containsKey(stones[i] + baseJump + 1)) {
            destination = pos.get(stones[i] + baseJump + 1);
            Set<Integer> s = F.getOrDefault(destination, new HashSet<>());
            s.add(baseJump + 1);
            F.put(destination, s);
          }
        }
      }
      return !F.getOrDefault(len - 1, new HashSet<>()).isEmpty();
    }
  }

  private final Solution s = new Solution();

  private final Consumer<int[]> c =
      stones -> {
        log.info(stones);
        log.info(s.canCross(stones));
        log.info();
      };

  @Test
  public void test() {
    c.accept(Arrays.from(0));
    c.accept(Arrays.from(0, 1));
    c.accept(Arrays.from(0, 2));
    c.accept(Arrays.from(0, 1, 3, 5, 6, 8, 12, 17));
    c.accept(Arrays.from(0, 1, 2, 3, 4, 8, 9, 11));
  }
}
