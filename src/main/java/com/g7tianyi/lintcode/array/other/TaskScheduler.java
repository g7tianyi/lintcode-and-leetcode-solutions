package com.g7tianyi.lintcode.array.other;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.Arrays;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Oct 02, 2019
 *
 * @link https://www.lintcode.com/problem/task-scheduler/description
 */
public class TaskScheduler {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int leastInterval(char[] tasks, int latency) {
      int[] counts = new int[26];
      int types = 0;
      for (char ch : tasks) {
        if (counts[ch - 'A'] == 0) {
          ++types;
        }
        ++counts[ch - 'A'];
      }

      int[] values = new int[types];
      int[] runs = new int[types];
      int pos = 0;
      for (int count : counts) {
        if (count > 0) {
          values[pos] = count;
          runs[pos] = -1;
          ++pos;
        }
      }
      Arrays.sort(values);

      int result = 0;
      int curr = 0, zero;
      while (true) {
        zero = 0;
        for (pos = types - 1; pos >= 0; --pos) {
          if (values[pos] == 0) {
            ++zero;
          } else if (runs[pos] == -1 || curr - runs[pos] > latency) {
            // runs[pos]表示任务上一次被分配的时间
            runs[pos] = curr;
            --values[pos];
            break;
          }
        }

        if (zero == types) {
          break;
        } else {
          ++result;
        }

        // 一个重要的优化应该是：怎么让curr可以直接"跳"到下一次可以分配任务的时刻
        ++curr;
      }

      return result;
    }
  }

  private final Solution s = new Solution();

  @AllArgsConstructor
  private class Case {
    private String s;
    private int latency;
  }

  private final Consumer<Case> c =
      aCase -> {
        log.info(aCase.s);
        log.info(s.leastInterval(aCase.s.toCharArray(), aCase.latency));
      };

  @Test
  public void test() {
    c.accept(new Case("AAABBB", 2));
    c.accept(new Case("BBBBAAACC", 3));
  }
}
