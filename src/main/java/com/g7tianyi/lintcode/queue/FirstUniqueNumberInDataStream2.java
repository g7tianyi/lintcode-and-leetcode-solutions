package com.g7tianyi.lintcode.queue;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by g7tianyi on Oct 02, 2019
 *
 * @link https://www.lintcode.com/problem/first-unique-number-in-data-stream-ii/description
 */
public class FirstUniqueNumberInDataStream2 {

  private static final Logger log = Logger.getInstance();

  public class DataStream {

    private class Item {

      private int value, index;

      public Item(int value, int index) {
        this.value = value;
        this.index = index;
      }
    }

    private PriorityQueue<Item> priorityQueue;
    private Map<Integer, Item> itemMap;
    private int cursor;

    public DataStream() {
      priorityQueue =
          new PriorityQueue<>(
              new Comparator<Item>() {
                @Override
                public int compare(Item item1, Item item2) {
                  return item1.index - item2.index;
                }
              });
      itemMap = new HashMap<>();
      cursor = 0;
    }

    public void add(int value) {
      if (itemMap.containsKey(value)) {
        Item item = itemMap.get(value);
        priorityQueue.remove(item);
      } else {
        Item item = new Item(value, cursor++);
        priorityQueue.offer(item);
        itemMap.put(value, item);
      }
    }

    public int firstUnique() {
      return priorityQueue.peek().value;
    }
  }

  @Test
  public void test0() {
    DataStream stream = new DataStream();
    stream.add(1);
    stream.add(2);
    log.info(stream.firstUnique());
    stream.add(1);
    log.info(stream.firstUnique());
  }

  @Test
  public void test1() {
    DataStream stream = new DataStream();
    stream.add(1);
    stream.add(2);
    stream.add(3);
    stream.add(4);
    stream.add(5);
    log.info(stream.firstUnique());
    stream.add(1);
    log.info(stream.firstUnique());
    stream.add(2);
    log.info(stream.firstUnique());
    stream.add(3);
    log.info(stream.firstUnique());
    stream.add(4);
    log.info(stream.firstUnique());
    stream.add(5);
    stream.add(6);
    log.info(stream.firstUnique());
  }
}
