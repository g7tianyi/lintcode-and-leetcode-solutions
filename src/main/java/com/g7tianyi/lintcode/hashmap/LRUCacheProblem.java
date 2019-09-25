package com.g7tianyi.lintcode.hashmap;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by g7tianyi on Sep 25, 2019
 *
 * @link https://www.lintcode.com/problem/lru-cache/description
 */
public class LRUCacheProblem {

  private static final Logger log = Logger.getInstance();

  public class LRUCache {

    private final LinkedList<Integer> access;
    private final Map<Integer, Integer> map;
    private final int capacity;

    public LRUCache(int capacity) {
      this.map = new HashMap<>();
      this.access = new LinkedList<>();
      this.capacity = capacity;
    }

    public int get(int key) {
      if (!map.containsKey(key)) {
        return -1;
      }

      access.remove((Integer) key);
      access.addLast(key);
      return map.get(key);
    }

    public void set(int key, int value) {
      if (map.containsKey(key)) {
        access.remove(key);
      } else if (map.size() == capacity) {
        Integer evicted = access.removeFirst();
        map.remove(evicted);
      }

      map.put(key, value);
      access.addLast(key);
    }
  }

  @Test
  public void test() {

    LRUCache lruCache = new LRUCache(2);
    lruCache.set(2, 1);
    lruCache.set(1, 1);
    log.info(lruCache.get(2));
    lruCache.set(4, 1);
    log.info(lruCache.get(1));
    log.info(lruCache.get(2));
  }
}
