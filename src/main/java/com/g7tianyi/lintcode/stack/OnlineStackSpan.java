package com.g7tianyi.lintcode.stack;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Oct 01, 2019
 *
 * @link https://www.lintcode.com/problem/online-stock-span/description
 */
public class OnlineStackSpan {

  private static final Logger log = Logger.getInstance();

  public class StockSpanner {

    private int curr = -1;
    private List<Integer> prices = new ArrayList<>();
    private List<Integer> skipList = new ArrayList<>();

    public StockSpanner() {}

    public int next(int price) {
      ++curr;
      prices.add(price);

      // F[i] 表示下标区间[F[i], i]内的数字都小于等于prices[i]
      // 换句话说，区间[F[i], i]内最高的报价就是prices[i]
      // 如果prices[i]小于等于当前报价，那么就跳到F[i]之前的一天继续检查
      if (skipList.isEmpty()) {
        skipList.add(curr);
        return 1;
      } else {
        int prev = curr - 1, last = curr;
        while (prev >= 0 && prices.get(prev) <= price) {
          last = skipList.get(prev);
          prev = last - 1;
        }
        skipList.add(last);
        return curr - last + 1;
      }
    }
  }

  private final Consumer<int[]> c =
      elems -> {
        StockSpanner stockSpanner = new StockSpanner();
        StringBuilder sb = new StringBuilder();
        for (int elem : elems) {
          sb.append(stockSpanner.next(elem)).append(' ');
        }
        log.info(sb.toString());
      };

  @Test
  public void test() {
    c.accept(Arrays.from(100, 80, 60, 70, 60, 75, 85, 120));
    c.accept(Arrays.from(50, 80, 80, 70, 90, 75, 85));
  }
}
