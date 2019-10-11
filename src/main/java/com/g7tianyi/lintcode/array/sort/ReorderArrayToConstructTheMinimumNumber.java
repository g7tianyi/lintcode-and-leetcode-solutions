package com.g7tianyi.lintcode.array.sort;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Oct 11, 2019
 *
 * @link https://www.lintcode.com/problem/reorder-array-to-construct-the-minimum-number/description
 */
public class ReorderArrayToConstructTheMinimumNumber {

  private static final Logger log = Logger.getInstance();

  public static class Solution {

    public String minNumber(int[] values) {

      if (values == null || values.length == 0) {
        return "";
      }

      quickSort(values, 0, values.length - 1);

      StringBuilder sb = new StringBuilder();
      for (int value : values) {
        sb.append(value);
      }

      for (int i = 0; i < sb.length(); i++) {
        if (sb.charAt(i) != '0') {
          return sb.substring(i);
        }
      }
      return "0";
    }

    private static void quickSort(int[] elems, int former, int latter) {
      if (former >= latter) {
        return;
      }
      int split = partition(elems, former, latter);
      quickSort(elems, former, split - 1);
      quickSort(elems, split + 1, latter);
    }

    private static int partition(int[] elems, int former, int latter) {
      int pivot = elems[former];
      while (former < latter) {
        // 在尾部找到一个小于pivot的数字
        while (latter > former && noGreaterThan(pivot, elems[latter])) {
          --latter;
        }
        // 然后将找到的这个数字放到前面来
        if (latter > former) {
          elems[former] = elems[latter];
        }

        // 再在前面找到一个大于pivot的数字
        while (former < latter && noGreaterThan(elems[former], pivot)) {
          ++former;
        }
        // 然后将找到的这个数字放到后面去
        if (former < latter) {
          elems[latter] = elems[former];
        }
      }
      elems[latter] = pivot;
      return latter;
    }

    private static boolean noGreaterThan(int a, int b) {
      String sb = String.valueOf(b);
      return (a + sb).compareTo(sb + a) <= 0;
    }
  }

  private final Solution s = new Solution();

  private final Consumer<int[]> c =
      values -> {
        String str = s.minNumber(values);
        log.info(values);
        log.info(str);
      };

  @Test
  public void test() {
    c.accept(com.g7tianyi.common.Arrays.from(137, 3, 32, 26, 321, 29, 7, 2));
  }
}
