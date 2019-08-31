package com.g7tianyi.lintcode.array;

import com.g7tianyi.common.Strings;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 31, 2019
 *
 * @link https://www.lintcode.com/problem/relative-ranks/description
 */
public class RelativeRanks {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    private class Score {
      private int score;
      private int index;

      public Score(int score, int index) {
        this.score = score;
        this.index = index;
      }
    }

    public String[] findRelativeRanks(int[] elems) {

      List<Score> scores = new ArrayList<>();
      for (int i = 0; i < elems.length; ++i) {
        scores.add(new Score(elems[i], i));
      }

      scores.sort(
          new Comparator<Score>() {
            @Override
            public int compare(Score o1, Score o2) {
              return Integer.compare(o2.score, o1.score);
            }
          });

      String[] result = new String[elems.length];
      for (int i = 0; i < elems.length; ++i) {
        Score score = scores.get(i);
        if (i == 0) {
          result[score.index] = "Gold Medal";
        } else if (i == 1) {
          result[score.index] = "Silver Medal";
        } else if (i == 2) {
          result[score.index] = "Bronze Medal";
        } else {
          result[score.index] = String.valueOf(i + 1);
        }
      }
      return result;
    }
  }

  @AllArgsConstructor
  public static class Input {

    private int[] elems;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Input> runner =
        input -> {
          log.info(Strings.format(input.elems));
          log.info(Strings.format(s.findRelativeRanks(input.elems)));
          log.info();
        };

    runner.accept(new Input(new int[] {5, 4, 3, 2, 1}));
    runner.accept(new Input(new int[] {5, 3, 1, 2, 4}));
  }
}
