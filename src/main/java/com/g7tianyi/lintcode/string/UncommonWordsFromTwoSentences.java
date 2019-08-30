package com.g7tianyi.lintcode.string;

import com.g7tianyi.common.Strings;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 30, 2019
 *
 * @link https://www.lintcode.com/problem/uncommon-words-from-two-sentences/description
 */
public class UncommonWordsFromTwoSentences {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public String[] uncommonFromSentences(String A, String B) {

      Map<String, Integer> map1 = analysis(A);
      Map<String, Integer> map2 = analysis(B);

      List<String> words = new ArrayList<>();

      for (Map.Entry<String, Integer> entry : map1.entrySet()) {
        if (entry.getValue() > 1) {
          continue;
        }

        if (!map2.containsKey(entry.getKey())) {
          words.add(entry.getKey());
        }
      }

      for (Map.Entry<String, Integer> entry : map2.entrySet()) {
        if (entry.getValue() > 1) {
          continue;
        }

        if (!map1.containsKey(entry.getKey())) {
          words.add(entry.getKey());
        }
      }

      String[] result = new String[words.size()];
      return words.toArray(result);
    }

    private Map<String, Integer> analysis(String s) {
      Map<String, Integer> map = new HashMap<>();
      String[] words = s.split("\\s+");
      for (String word : words) {

        if (map.containsKey(word)) {
          map.put(word, map.get(word) + 1);
        } else {
          map.put(word, 1);
        }
      }
      return map;
    }
  }

  @AllArgsConstructor
  private static class Input {

    private String a;
    private String b;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Input> c =
        input -> {
          log.info(Strings.format(s.uncommonFromSentences(input.a, input.b)));
          log.info();
        };

    c.accept(new Input("s z z z s", "s z ejt"));
    c.accept(new Input("this apple is sweet", "this apple is sour"));
  }
}
