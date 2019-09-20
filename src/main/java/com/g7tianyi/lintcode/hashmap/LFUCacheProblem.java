package com.g7tianyi.lintcode.hashmap;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by g7tianyi on Sep 20, 2019
 *
 * @link https://www.lintcode.com/problem/lfu-cache/description
 */
public class LFUCacheProblem {

  private static final Logger log = Logger.getInstance();

  public class LFUCache {

    private class Entry {
      private int value;
      private int count;
    }

    private int capacity;
    private HashMap<Integer, Entry> entries;
    private TreeMap<Integer, LinkedList<Integer>> references;

    public LFUCache(int capacity) {
      this.capacity = capacity;
      this.entries = new HashMap<>();
      this.references = new TreeMap<>();
    }

    public void set(int key, int value) {
      if (!entries.containsKey(key)) {
        createNewEntry(key, value);
      } else {
        referOldEntry(key, value);
      }
    }

    public int get(int key) {
      if (entries.containsKey(key)) {
        Entry entry = entries.get(key);
        referEntry(key, entry);
        return entry.value;
      }
      return -1;
    }

    private void evictEntry() {

      Map.Entry<Integer, LinkedList<Integer>> mapEntry = references.firstEntry();

      LinkedList<Integer> cachedKeys = mapEntry.getValue();
      Integer evictedKey = cachedKeys.removeFirst(); // FIFO
      entries.remove(evictedKey);

      if (cachedKeys.isEmpty()) {
        references.remove(mapEntry.getKey()); // THIS IS REQUIRED
      }
    }

    private void referEntry(int key, Entry entry) {
      ++entry.count;
      entries.put(key, entry);

      if (references.containsKey(entry.count - 1)) {
        LinkedList<Integer> cachedKeys = references.get(entry.count - 1);
        cachedKeys.removeFirstOccurrence(key);
        if (cachedKeys.isEmpty()) {
          references.remove(entry.count - 1); // THIS IS REQUIRED
        }
      }

      if (!references.containsKey(entry.count)) {
        references.put(entry.count, new LinkedList<>());
      }
      references.get(entry.count).addLast(key);
    }

    private void referOldEntry(int key, int value) {
      Entry entry = entries.get(key);
      entry.value = value;
      referEntry(key, entry);
    }

    private void createNewEntry(int key, int value) {
      if (entries.size() == capacity) {
        evictEntry();
      }

      Entry entry = new Entry();
      entry.value = value;
      entry.count = 1;
      entries.put(key, entry);

      if (!references.containsKey(1)) {
        references.put(1, new LinkedList<>());
      }
      references.get(1).addLast(key);
    }
  }

  @Test
  public void test() {

    LFUCache lfuCache = new LFUCache(3);

    lfuCache.set(2, 2);
    lfuCache.set(1, 1);
    log.info(lfuCache.get(2));
    log.info(lfuCache.get(1));
    log.info(lfuCache.get(2));
    lfuCache.set(3, 3);
    lfuCache.set(4, 4);
    log.info(lfuCache.get(3));
    log.info(lfuCache.get(2));
    log.info(lfuCache.get(1));
    log.info(lfuCache.get(4));
  }
}
