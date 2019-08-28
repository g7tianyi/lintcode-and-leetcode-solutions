package com.g7tianyi.lintcode.array;

import com.g7tianyi.common.Lists;
import com.g7tianyi.common.Strings;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.List;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 24, 2019
 *
 * @link https://www.lintcode.com/problem/recover-rotated-sorted-array/description
 */
public class RecoverRotatedSortedArray {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public void recoverRotatedSortedArray(List<Integer> elems) {

      if (elems == null || elems.isEmpty()) {
        return;
      }

      int pos = 0;
      Integer prev = elems.get(0);
      for (int i = 1; i < elems.size(); i++) {
        Integer curr = elems.get(i);
        if (curr < prev) {
          pos = i;
          break;
        }
      }

      reverseList(elems, 0, pos - 1);
      reverseList(elems, pos, elems.size() - 1);
      reverseList(elems, 0, elems.size() - 1);
    }

    // former与latter都是闭区间
    private void reverseList(List<Integer> elems, int former, int latter) {

      int posFormer = former, posLatter = latter;
      while (posFormer < posLatter) {
        Integer valueFormer = elems.get(posFormer);
        Integer valueLatter = elems.get(posLatter);

        elems.set(posFormer, valueLatter);
        elems.set(posLatter, valueFormer);

        posFormer++;
        posLatter--;
      }
    }
  }

  @AllArgsConstructor
  public static class Input {

    private List<Integer> elems;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Input> runner =
        input -> {
          log.info(Strings.format(input.elems));
          s.recoverRotatedSortedArray(input.elems);
          log.info(Strings.format(input.elems));
          log.info("");
        };

    runner.accept(new Input(Lists.from(4, 5, 6, 7, 8)));
    runner.accept(new Input(Lists.from(4, 0, 1, 2, 3)));
    runner.accept(new Input(Lists.from(4, 5, 6, 7, 3)));
    runner.accept(new Input(Lists.from(4, 5, 1, 2, 3)));
    runner.accept(new Input(Lists.from(6, 8, 9, 1, 2)));
    runner.accept(new Input(Lists.from(6, 8, 9, 1, 2, 3)));
  }
}
