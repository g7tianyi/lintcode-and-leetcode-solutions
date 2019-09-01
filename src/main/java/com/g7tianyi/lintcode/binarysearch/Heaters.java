package com.g7tianyi.lintcode.binarysearch;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 1, 2019
 *
 * @link https://www.lintcode.com/problem/heaters/description
 */
public class Heaters {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int findRadius(int[] houses, int[] heaters) {

      Arrays.sort(heaters);

      int len = heaters.length, result = 0;

      for (int house : houses) {
        int pos = binarySearch(heaters, house);
        // When pos is 0, there's no previous heaters
        int distanceToPrev = (pos == 0) ? Integer.MAX_VALUE : house - heaters[pos - 1];
        int distanceToNext = (pos == len) ? Integer.MAX_VALUE : heaters[pos] - house;
        result = Math.max(result, Math.min(distanceToNext, distanceToPrev));
      }

      return result;
    }

    private int binarySearch(int[] elems, int target) {
      int former = 0, latter = elems.length - 1;
      while (former <= latter) {
        int middle = former + ((latter - former) >> 1);
        if (elems[middle] < target) {
          former = middle + 1;
        } else {
          latter = middle - 1;
        }
      }
      // return 'former' to get the index of the one who is greater than 'target'
      return former;
    }
  }

  @AllArgsConstructor
  public static class Case {

    private int[] houses;
    private int[] heaters;
    private int result;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> runner =
        aCase -> Assert.assertEquals(aCase.result, s.findRadius(aCase.houses, aCase.heaters));

    runner.accept(new Case(new int[] {1, 2, 3}, new int[] {2}, 1));
    runner.accept(new Case(new int[] {1, 2, 3, 4}, new int[] {1, 4}, 1));
  }
}
