package com.g7tianyi.lintcode.string;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by g7tianyi on Oct 24, 2019
 *
 * @link https://www.lintcode.com/problem/bold-words-in-string/description
 */
public class BoldWordsInString {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    class Range {
      private int start, end;

      public Range(int start, int end) {
        this.start = start;
        this.end = end;
      }
    }

    public String boldWords(String[] words, String S) {
      List<Range> ranges = new ArrayList<>();
      for (String word : words) {
        int len = word.length(), from = 0;
        while (true) {
          int index = S.indexOf(word, from);
          if (index >= 0) {
            ranges.add(new Range(index, index + len));
            from = index + 1; // 开始用 index + len，WA了，漏了word等于 "aaa" 这种情况
          } else {
            break;
          }
        }
      }

      ranges.sort(
          new Comparator<Range>() {
            @Override
            public int compare(Range o1, Range o2) {
              if (o1.start != o2.start) {
                return Integer.compare(o1.start, o2.start);
              }
              return Integer.compare(o1.end, o2.end);
            }
          });

      List<Range> mergedRange = new ArrayList<>();
      Range prev = null;
      for (Range curr : ranges) {
        if (prev == null) {
          prev = curr;
        } else {
          if (prev.end >= curr.start) {
            prev.end = Math.max(prev.end, curr.end);
          } else {
            mergedRange.add(prev);
            prev = curr;
          }
        }
      }

      if (prev != null) {
        mergedRange.add(prev);
      }

      StringBuilder sb = new StringBuilder(S);

      for (int i = mergedRange.size() - 1; i >= 0; --i) {
        Range range = mergedRange.get(i);
        sb.insert(range.end, "</b>");
        sb.insert(range.start, "<b>");
      }

      return sb.toString();
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.boldWords(new String[] {"ab", "bc"}, "aabcd"));
    log.info(
        s.boldWords(
            new String[] {
              "bcccaeb",
              "b",
              "eedcbda",
              "aeebebebd",
              "ccd",
              "eabbbdcde",
              "deaaea",
              "aea",
              "accebbb",
              "d"
            },
            "ceaaabbbedabbecbcced"));
  }
}
