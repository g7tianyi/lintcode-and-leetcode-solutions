package com.g7tianyi.lintcode.array.sort;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by g7tianyi on Nov 03, 2019
 *
 * @link https://www.lintcode.com/problem/largest-number/description
 */
public class LargestNumber {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public String largestNumber(int[] nums) {

      if (nums == null || nums.length == 0) {
        return "";
      }

      List<String> values = new ArrayList<>();
      for (int num : nums) {
        values.add(String.valueOf(num));
      }

      values.sort(
          new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
              return (o2 + o1).compareTo(o1 + o2);
            }
          });

      StringBuilder sb = new StringBuilder();
      for (String value : values) {
        sb.append(value);
      }

      while (sb.length() > 1 && sb.charAt(0) == '0') {
        sb.deleteCharAt(0);
      }

      return sb.toString();
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.largestNumber(Arrays.from(1, 20, 23, 4, 8)));
    log.info(s.largestNumber(Arrays.from(4, 6, 65)));
    log.info(s.largestNumber(Arrays.from(0, 0, 0)));
    log.info(
        s.largestNumber(
            Arrays.from(
                25, 5, 12, 97, 3, 8, 79, 73, 38, 88, 98, 29, 84, 74, 16, 2, 67, 65, 41, 44, 88, 75,
                51, 87, 95, 90, 45, 40, 7, 53, 5, 30, 77, 5, 56, 58, 41, 51, 62, 88, 33, 69, 81, 78,
                18, 63, 82, 90, 21, 6, 12, 92, 67, 6, 81, 83, 14, 6, 76, 85, 79, 96, 41, 44, 20, 89,
                59, 58, 83, 58, 73, 1, 41, 41, 24, 55, 61, 49, 10, 42, 5, 1, 98, 30, 91, 9, 34, 5,
                84, 43, 73, 4, 22, 11, 21, 14, 1, 62, 77, 41)));
  }
}
