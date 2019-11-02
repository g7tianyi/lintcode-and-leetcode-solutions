package com.g7tianyi.lintcode.string.match;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.*;

/**
 * Created by g7tianyi on Nov 02, 2019
 *
 * @link https://www.lintcode.com/problem/anagrams/description
 */
public class Anagrams {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public List<String> anagrams(String[] words) {

      List<String> result = new ArrayList<>();
      if (words == null || words.length == 0) {
        return result;
      }

      Map<String, List<String>> map = new HashMap<>();
      for (String word : words) {
        char[] chars = word.toCharArray();
        Arrays.sort(chars);
        String w = new String(chars);
        List<String> anagrams = map.getOrDefault(w, new ArrayList<>());
        anagrams.add(word);
        map.put(w, anagrams);
      }

      for (List<String> ss : map.values()) {
        if (ss.size() > 1) {
          result.addAll(ss);
        }
      }
      return result;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.anagrams(new String[] {"lint", "intl", "inlt", "code"}));
    log.info(s.anagrams(new String[] {"ab", "ba", "cd", "dc", "e"}));
  }
}
