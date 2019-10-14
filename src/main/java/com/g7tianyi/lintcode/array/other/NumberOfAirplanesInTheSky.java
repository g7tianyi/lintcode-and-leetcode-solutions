package com.g7tianyi.lintcode.array.other;

import com.g7tianyi.common.Interval;
import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.*;

/**
 * Created by g7tianyi on Oct 15, 2019
 *
 * @link https://www.lintcode.com/problem/number-of-airplanes-in-the-sky/description
 */
public class NumberOfAirplanesInTheSky {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    class Point {
      int time, flag;
      public Point(int time, int flag) {
        this.time = time;
        this.flag = flag; // 表示起飞或降落
      }
    }

    public int countOfAirplanes(List<Interval> airplanes) {

      if (airplanes == null || airplanes.isEmpty()) {
        return 0;
      }

      List<Point> points = new ArrayList<>();
      for (Interval interval: airplanes) {
        points.add(new Point(interval.start, 1));
        points.add(new Point(interval.end, -1));
      }

      Collections.sort(points, new Comparator<Point>() {
        @Override
        public int compare(Point o1, Point o2) {
          if (o1.time != o2.time) {
            return o1.time - o2.time;
          } else {
            return o1.flag - o2.flag;
          }
        }
      });

      int result = 0, total = 0;
      for (Point point: points) {
        total += point.flag; // 有新的起飞就+1，有降落就-1
        result = Math.max(result, total);
      }
      return result;
    }
  }

  public class MLE_Solution {

    public int countOfAirplanes(List<Interval> airplanes) {
      if (airplanes == null || airplanes.isEmpty()) {
        return 0;
      }

      Map<Integer, Integer> m = new HashMap<>();
      int result = 0;
      for (Interval interval : airplanes) {
        for (int i = interval.start, j; i < interval.end; ++i) {
          j = 0;
          if (m.containsKey(i)) {
            j = m.get(i);
          }
          ++j;
          m.put(i, j);
          result = Math.max(result, j);
        }
      }
      return result;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.countOfAirplanes(Interval.froms("[1,10] [2,3] [5,8] [4,7]")));
    log.info(s.countOfAirplanes(Interval.froms("[1,2] [2,3] [3,4]")));
  }
}
