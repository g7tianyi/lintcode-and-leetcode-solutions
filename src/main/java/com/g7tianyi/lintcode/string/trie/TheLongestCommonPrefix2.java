package com.g7tianyi.lintcode.string.trie;

import com.g7tianyi.common.Lists;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 02, 2019
 *
 * @link https://www.lintcode.com/problem/the-longest-common-prefix-ii/description
 */
public class TheLongestCommonPrefix2 {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    class Trie {
      char ch;
      List<Trie> children;

      public Trie() {
        this(' ');
      }

      public Trie(char ch) {
        this.ch = ch;
        this.children = new LinkedList<>();
      }

      public void insert(String word) {
        // Drat.. Recursion will throw StackOverflowError..
        // insert(word, 0);

        int i = 0, len = word.length();
        Trie curr = this, next = null;
        while (i < len) {
          char ch = word.charAt(i);
          for (Trie node : curr.children) {
            if (node.ch == ch) {
              next = node;
              break;
            }
          }
          if (next == null) {
            next = new Trie(ch);
            curr.children.add(next);
          }
          curr = next;
          next = null;
          ++i;
        }
      }

      private void insert(String word, int index) {

        Trie node = null;
        char ch = word.charAt(index);
        for (Trie next : children) {
          if (next.ch == ch) {
            node = next;
            break;
          }
        }

        if (node == null) {
          node = new Trie(ch);
          children.add(node);
        }

        if (index != word.length() - 1) {
          node.insert(word, index + 1);
        }
      }

      public int longestCommonPrefix(String word) {
        // Drat.. Recursion might throw StackOverflowError..
        // return longestCommonPrefix(word, 0);

        int i = 0, len = word.length();
        Trie curr = this, next = null;
        while (i < len) {
          char ch = word.charAt(i);
          for (Trie node : curr.children) {
            if (node.ch == ch) {
              next = node;
              break;
            }
          }
          if (next == null) {
            return i;
          }

          curr = next;
          next = null;
          ++i;
        }
        return len;
      }

      private int longestCommonPrefix(String word, int index) {

        if (index == word.length()) {
          return index;
        }

        Trie node = null;
        char ch = word.charAt(index);
        for (Trie next : children) {
          if (next.ch == ch) {
            node = next;
            break;
          }
        }

        if (node == null) {
          return index;
        }

        return node.longestCommonPrefix(word, index + 1);
      }
    }

    public int the_longest_common_prefix(List<String> words, String s) {
      Trie root = new Trie();
      for (String word : words) {
        root.insert(word);
      }
      return root.longestCommonPrefix(s);
    }
  }

  @AllArgsConstructor
  private class Case {
    private List<String> words;
    private String s;
    private int expect;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c =
        aCase ->
            Assert.assertEquals(aCase.expect, s.the_longest_common_prefix(aCase.words, aCase.s));

    c.accept(new Case(Lists.from("abcba", "acc", "abwsf"), "abse", 2));
    c.accept(new Case(Lists.from("aaa", "bbb", "aabb"), "aaab", 3));
    c.accept(new Case(Lists.from("aaa", "bbb", "aabb"), "aabb", 4));
    c.accept(new Case(Lists.from("aaaa", "abbb", "aabb"), "aab", 3));
    c.accept(new Case(Lists.from("aaaa", "abbb", "aabb"), "ac", 1));
  }
}
