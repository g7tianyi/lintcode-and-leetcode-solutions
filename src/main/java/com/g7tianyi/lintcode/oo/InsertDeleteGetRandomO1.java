package com.g7tianyi.lintcode.oo;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.*;

/**
 * Created by g7tianyi on Oct 09, 2019
 *
 * @link https://www.lintcode.com/problem/insert-delete-getrandom-o1/description
 */
public class InsertDeleteGetRandomO1 {

  private static final Logger log = Logger.getInstance();

  public class RandomizedSet {

    private final Random random = new Random();

    private final Map<Integer, Integer> map;

    private final List<Integer> list;

    public RandomizedSet() {
      map = new HashMap<>();
      list = new ArrayList<>();
    }

    public boolean insert(int val) {
      if (map.containsKey(val)) {
        return false;
      }
      list.add(val);
      map.put(val, list.size() - 1);
      return true;
    }

    public boolean remove(int val) {
      if (!map.containsKey(val)) {
        return false;
      }

      // 待删除的value在数组中所处的问题
      Integer index = map.remove(val);
      if (index == list.size() - 1) {
        // 只有一个元素，或被删除的元素就是列表的最后一个元素
        list.remove(list.size() - 1);
      } else {
        // 找数组最后一个元素的所对应的值，与当前位置交换
        Integer lastIndexKey = list.get(list.size() - 1);
        list.set(index, lastIndexKey);
        map.put(lastIndexKey, index);
        list.remove(list.size() - 1);
      }

      return true;
    }

    public int getRandom() {
      int index = random.nextInt(list.size());
      return list.get(index);
    }
  }

  @Test
  public void test() {
    // 初始化空集set
    RandomizedSet randomSet = new RandomizedSet();

    // 1插入set中。返回正确因为1被成功插入
    log.info(randomSet.insert(1));

    // 返回错误因为2不在set中
    log.info(randomSet.remove(2));

    // 2插入set中，返回正确，set现在有[1,2]。
    log.info(randomSet.insert(2));

    // getRandom 应该随机的返回1或2。
    log.info(randomSet.getRandom());

    // 从set中移除1，返回正确。set现在有[2]。
    log.info(randomSet.remove(1));

    // 2已经在set中，返回错误。
    log.info(randomSet.insert(2));

    // 因为2是set中唯一的数字，所以getRandom总是返回2。
    log.info(randomSet.getRandom());
  }
}
