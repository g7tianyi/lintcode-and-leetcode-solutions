package com.g7tianyi.lintcode.greedy;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Oct 20, 2019
 *
 * @link https://www.lintcode.com/problem/candy/description
 */
public class Candy {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int candy(int[] ratings) {

      int[] candies = new int[ratings.length];
      java.util.Arrays.fill(candies, 1);

      boolean steady = false;
      while (!steady) {
        steady = true;
        for (int i = 0, candy; i < ratings.length; ++i) {
          candy = candies[i];
          if (i > 0 && ratings[i] > ratings[i - 1]) {
            candy = Math.max(candy, candies[i - 1] + 1);
          }

          if (i < ratings.length - 1 && ratings[i] > ratings[i + 1]) {
            candy = Math.max(candy, candies[i + 1] + 1);
          }

          if (candies[i] < candy) {
            candies[i] = candy;
            steady = false;
          }
        }
      }

      int result = 0;
      for (int candy : candies) {
        result += candy;
      }
      return result;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.candy(Arrays.from(1, 2, 4, 2, 3)));
    log.info(s.candy(Arrays.from(1, 2)));
    log.info(s.candy(Arrays.from(1, 2, 2)));
    log.info(s.candy(Arrays.from(1, 1, 1)));
  }
}
