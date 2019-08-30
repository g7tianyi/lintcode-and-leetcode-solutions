package com.g7tianyi.lintcode.string;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 30, 2019
 *
 * @link https://www.lintcode.com/problem/longest-word-in-dictionary/description
 */
public class LongestWordInDictionary {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    class Trie {
      char ch;
      boolean isWord;
      Trie[] children;

      public Trie() {
        this('#');
      }

      public Trie(char ch) {
        this.ch = ch;
        this.children = new Trie[26];
        for (int i = 0; i < children.length; ++i) {
          children[i] = null;
        }
      }

      public void insert(String word, int from) {
        char ch = word.charAt(from);
        int index = ch - 97;
        if (children[index] == null) {
          children[index] = new Trie(ch);
        }

        if (from == word.length() - 1) {
          children[index].isWord = true;
        } else {
          children[index].insert(word, from + 1);
        }
      }

      public String longestWord() {
        String result = "", child;
        for (Trie next : children) {
          if (next != null && next.isWord) {
            child = next.longestWord();
            if (result.length() < child.length()) {
              result = child;
            }
          }
        }
        if (ch != '#') {
          return ch + result;
        }
        return result;
      }
    }

    // 链表更节省内存，但是要按顺序排列，编程麻烦点
    class TrieWA {
      char ch;
      boolean isWord;
      List<TrieWA> children;

      public TrieWA() {
        this('#');
      }

      public TrieWA(char ch) {
        this.ch = ch;
        this.children = new LinkedList<>();
      }

      public void insert(String word, int from) {
        TrieWA node = null;
        char ch = word.charAt(from);
        for (TrieWA next : children) {
          if (next.ch == ch) {
            node = next;
            break;
          }
        }

        if (node == null) {
          node = new TrieWA(ch);
          children.add(node);
        }

        if (from == word.length() - 1) {
          node.isWord = true;
        } else {
          node.insert(word, from + 1);
        }
      }

      public String longestWord() {
        String result = "", child;
        for (TrieWA node : children) {
          if (node != null && node.isWord) {
            child = node.longestWord();
            if (result.length() < child.length()) {
              result = child;
            }
          }
        }
        if (ch != '#') {
          return ch + result;
        }
        return result;
      }
    }

    public String longestWord(String[] words) {
      Trie trie = new Trie();
      for (String word : words) {
        trie.insert(word, 0);
      }
      return trie.longestWord();
    }
  }

  @AllArgsConstructor
  private static class Input {
    private String[] words;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Input> c = input -> log.info(s.longestWord(input.words));

    c.accept(new Input(new String[] {"w", "wo", "wor", "worl", "world"}));
    c.accept(new Input(new String[] {"a", "banana", "app", "appl", "ap", "apply", "apple"}));
  }
}
