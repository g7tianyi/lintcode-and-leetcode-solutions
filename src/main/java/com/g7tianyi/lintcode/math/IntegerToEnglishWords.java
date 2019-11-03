package com.g7tianyi.lintcode.math;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by g7tianyi on Nov 03, 2019
 *
 * @link https://www.lintcode.com/problem/integer-to-english-words/description
 */
public class IntegerToEnglishWords {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    private final String[] smalls = {
      "",
      "One",
      "Two",
      "Three",
      "Four",
      "Five",
      "Six",
      "Seven",
      "Eight",
      "Nine",
      "Ten",
      "Eleven",
      "Twelve",
      "Thirteen",
      "Fourteen",
      "Fifteen",
      "Sixteen",
      "Seventeen",
      "Eighteen",
      "Nineteen"
    };

    private final String[] tenths = {
      "", "",
        "Twenty",
        "Thirty",
        "Forty",
        "Fifty",
        "Sixty",
        "Seventy",
        "Eighty",
        "Ninety"
    };

    private final String hundred = "Hundred";
    private final String thousand = "Thousand";
    private final String million = "Million";
    private final String billion = "Billion";

    public String numberToWords(int num) {
      if (num == 0) {
        return "Zero";
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
      return sb.toString().replaceAll("\\s+", " ");
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
    log.info(s.numberToWords(680901192));
    log.info(s.numberToWords(985237550));
    log.info(s.numberToWords(123));
    log.info(s.numberToWords(12345));
    log.info(s.numberToWords(1234567));
    log.info(s.numberToWords(0));
  }
}
