package com.g7tianyi.lintcode.string;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 03, 2019
 *
 * @link https://www.lintcode.com/problem/valid-word-square/description
 */
public class ValidWordSquare {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public boolean validWordSquare(String[] words) {
      for (int i = 0; i < words.length; ++i) {
        for (int j = 0; j < words[i].length(); ++j) {
          if (j >= words.length
              || i >= words[j].length()
              || words[j].charAt(i) != words[i].charAt(j)) {
            return false;
          }
        }
      }
      return true;
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<String[]> c = words -> log.info(s.validWordSquare(words));

    c.accept(new String[] {"abcd", "bnrt", "crmy", "dtye"});

    c.accept(new String[] {"abcd", "bnrt", "crm", "dt"});

    c.accept(new String[] {"ball", "area", "read", "lady"});

    c.accept(new String[] {"ball", "area", "lea", "lad"});
  }
}
