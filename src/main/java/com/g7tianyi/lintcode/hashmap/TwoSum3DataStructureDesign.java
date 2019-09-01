package com.g7tianyi.lintcode.hashmap;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 02, 2019
 *
 * @link https://www.lintcode.com/problem/two-sum-iii-data-structure-design/description
 */
public class TwoSum3DataStructureDesign {

  private static final Logger log = Logger.getInstance();

  public class TwoSum {

    private Map<Integer, Integer> map;

    public TwoSum() {
      this.map = new HashMap<>();
    }

    public void add(int number) {
      Integer count = map.getOrDefault(number, 0);
      map.put(number, count + 1);
    }

    public boolean find(int sum) {

      Set<Integer> keys = map.keySet();
      for (Integer key : keys) {
        if ((sum - key == key && map.getOrDefault(key, 0) > 1)
            || (sum - key != key && map.getOrDefault(sum - key, 0) > 0)) {
          return true;
        }
      }
      return false;
    }
  }

  @AllArgsConstructor
  private class Case {}

  @Test
  public void test() {

    TwoSum twoSum = new TwoSum();
    twoSum.add(1);
    twoSum.add(3);
    twoSum.add(5);

    log.info(twoSum.find(4));
    log.info(twoSum.find(7));

    twoSum.add(1);
    twoSum.add(1);
    log.info(twoSum.find(2));
    log.info(twoSum.find(3));
  }
}
