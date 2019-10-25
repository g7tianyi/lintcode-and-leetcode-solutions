package com.g7tianyi.lintcode.string.trie;

import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Oct 25, 2019
 *
 * @link https://www.lintcode.com/problem/add-and-search-word-data-structure-design/description
 */
public class AddAndSearchWordDataStructureDesign {

  private static final Logger log = Logger.getInstance();

  public class WordDictionary {

    private WordDictionary[] children;
    private boolean isWord;

    public WordDictionary() {
      this.children = new WordDictionary[26];
      this.isWord = false;
    }

    public void addWord(String word) {
      WordDictionary node = this;
      int index = 0;
      while (index < word.length()) {
        char ch = word.charAt(index);
        if (node.children[ch - 'a'] == null) {
          node.children[ch - 'a'] = new WordDictionary();
        }
        node = node.children[ch - 'a'];
        ++index;
      }
      node.isWord = true;
    }

    public boolean search(String word) {
      return search(this, word, 0);
    }

    private boolean search(WordDictionary wd, String word, int index) {
      if (index == word.length()) {
        return wd.isWord;
      }

      char ch = word.charAt(index);
      if (ch != '.') {
        if (wd.children[ch - 'a'] == null) {
          return false;
        } else {
          return search(wd.children[ch - 'a'], word, index + 1);
        }
      } else {
        for (WordDictionary next : wd.children) {
          if (next != null && search(next, word, index + 1)) {
            return true;
          }
        }
        return false;
      }
    }
  }

  @Test
  public void test1() {
    WordDictionary wd = new WordDictionary();
    wd.addWord("ran");
    wd.addWord("rune");
    wd.addWord("runner");
    wd.addWord("runs");
    wd.addWord("add");
    wd.addWord("adds");
    wd.addWord("adder");
    wd.addWord("addee");

    log.info(wd.search("r.n"));
    log.info(wd.search("ru.n.e"));
    log.info(wd.search("add"));
    log.info(wd.search("add."));
    log.info(wd.search("adde."));
    log.info(wd.search(".an."));
    log.info(wd.search("...s"));
    log.info(wd.search("....e."));
    log.info(wd.search("......."));
    log.info(wd.search("..n.r"));
  }

  @Test
  public void test() {
    WordDictionary wd = new WordDictionary();
    wd.addWord("bae");
    wd.addWord("bad");
    wd.addWord("dad");
    wd.addWord("mad");
    log.info(wd.search("pad"));
    log.info(wd.search("bad"));
    log.info(wd.search(".ad"));
    log.info(wd.search("b.."));
  }
}
