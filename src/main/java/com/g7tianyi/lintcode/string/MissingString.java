package com.g7tianyi.lintcode.string;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 29, 2019
 *
 * @link https://www.lintcode.com/problem/missing-string/description
 */
public class MissingString {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public List<String> missingString(String s1, String s2) {

      String[] split1 = s1.split("\\s+");
      String[] split2 = s2.split("\\s+");

      Map<String, Integer> map = new HashMap<>();
      for (String str : split2) {
        if (!map.containsKey(str)) {
          map.put(str, 0);
        }
        map.put(str, map.get(str) + 1);
      }

      List<String> result = new ArrayList<>();
      for (String str : split1) {
        if (!map.containsKey(str)) {
          result.add(str);
        }
      }

      return result;
      // return values.stream().map(value -> split1[value]).collect(Collectors.toList());
    }
  }

  @AllArgsConstructor
  private static class Case {

    private String s1;
    private String s2;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c =
        aCase -> {
          log.info(aCase.s1);
          log.info(aCase.s2);
          log.info(s.missingString(aCase.s1, aCase.s2));
          log.info();
        };

    c.accept(new Case("This is an example", "is example"));
    c.accept(new Case("AAA AA AA A AA", "AA AAA AA"));
    c.accept(
        new Case(
            "When the Kingsman headquarters are destroyed and the world is held hostage their journey leads them to the discovery of an allied spy organization in the US called Statesman dating back to the day they were both founded In a new adventure that tests their agents strength and wits to the limit these two elite secret organizations band together to defeat a ruthless common enemy in order to save the world something that becoming a bit of a habit for Eggsy",
            "headquarters are destroyed and the world is held hostage their journey leads them to the discovery of an allied spy organization in the US called Statesman dating back to the day they were both founded In a new adventure that tests their agents' strength and wits to the limit these two elite secret organizations band together to defeat a ruthless common enemy in order to save the world something that"));
  }
}
