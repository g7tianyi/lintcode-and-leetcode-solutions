package com.g7tianyi.lintcode.math;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by g7tianyi on Nov 03, 2019
 *
 * @link https://www.lintcode.com/problem/the-number-in-words/description
 */
public class TheNumberInWords {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    private final String[] smalls = {
      "",
      "one",
      "two",
      "three",
      "four",
      "five",
      "six",
      "seven",
      "eight",
      "nine",
      "ten",
      "eleven",
      "twelve",
      "thirteen",
      "fourteen",
      "fifteen",
      "sixteen",
      "seventeen",
      "eighteen",
      "nineteen"
    };

    private final String[] tenths = {
      "", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"
    };

    private final String hundred = "hundred";
    private final String thousand = "thousand";
    private final String million = "million";
    private final String billion = "billion";

    public String convertWords(int num) {
      if (num == 0) {
        return "zero";
      }

      List<String> elems = new ArrayList<>();
      if (num >= 1000000000) {
        elems.addAll(convert(num / 1000000000));
        elems.add(billion);
        num %= 1000000000;
      }
      if (num >= 1000000) {
        elems.addAll(convert(num / 1000000));
        elems.add(million);
        num %= 1000000;
      }
      if (num >= 1000) {
        elems.addAll(convert(num / 1000));
        elems.add(thousand);
        num %= 1000;
      }
      if (num > 0) {
        elems.addAll(convert(num));
      }

      StringBuilder sb = new StringBuilder();
      for (String elem : elems) {
        sb.append(elem).append(' ');
      }
      while (sb.length() > 0 && sb.charAt(sb.length() - 1) == ' ') {
        sb.deleteCharAt(sb.length() - 1);
      }
      return sb.toString();
    }

    private List<String> convert(int num) {
      List<String> elems = new ArrayList<>();
      if (num >= 100) {
        elems.add(smalls[num / 100]);
        elems.add(hundred);
        num %= 100;
      }
      if (num > 20) {
        elems.add(tenths[num / 10]);
        elems.add(smalls[num % 10]);
      } else {
        elems.add(smalls[num]);
      }
      return elems;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.convertWords(985237550));

    log.info(s.convertWords(2147483647));
    log.info(s.convertWords(298245241));
    log.info(s.convertWords(29824521));
    log.info(s.convertWords(2984521));
    log.info(s.convertWords(984521));
    log.info(s.convertWords(10245));
    log.info(s.convertWords(5825));
    log.info(s.convertWords(125));
    log.info(s.convertWords(25));
    log.info(s.convertWords(17));
    log.info(s.convertWords(6));
    log.info(s.convertWords(0));
  }
}
