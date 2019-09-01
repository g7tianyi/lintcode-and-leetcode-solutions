package com.g7tianyi.lintcode.hashmap;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by g7tianyi on Sep 1, 2019
 *
 * @link https://www.lintcode.com/problem/unique-morse-code-words/description
 */
public class UniqueMorseCodeWords {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int uniqueMorseRepresentations(String[] words) {

      String[] hash = {
        ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--",
        "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."
      };

      Set<String> set = new HashSet<>();

      int result = 0;
      char[] chars;
      for (String word : words) {
        StringBuilder sb = new StringBuilder();
        chars = word.toCharArray();
        for (char ch : chars) {
          sb.append(hash[ch - 'a']);
        }

        String morse = sb.toString();
        if (!set.contains(morse)) {
          set.add(morse);
          ++result;
        }
      }
      return result;
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    log.info(s.uniqueMorseRepresentations(new String[] {"gin", "zen", "gig", "msg"}));
    log.info(s.uniqueMorseRepresentations(new String[] {"a", "b"}));
  }
}
