package com.g7tianyi.lintcode.heap;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.PriorityQueue;

/**
 * Created by g7tianyi on Sep 05, 2019
 *
 * @link https://www.lintcode.com/problem/kth-largest-element/description
 */
public class KthLargestElement {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int kthLargestElement(int n, int[] nums) {
      PriorityQueue<Integer> pQueue = new PriorityQueue<>();
      for (int num : nums) {
        pQueue.offer(num);
        if (pQueue.size() > n) {
          pQueue.poll();
        }
      }
      return pQueue.peek();
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    log.info(s.kthLargestElement(3, new int[] {9, 3, 2, 4, 8}));
  }
}
