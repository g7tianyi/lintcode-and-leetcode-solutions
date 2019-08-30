package com.g7tianyi.lintcode.hash;

import com.g7tianyi.common.Strings;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 30, 2019
 *
 * @link https://www.lintcode.com/problem/subdomain-visit-count/description
 */
public class SubDomainVisitCount {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public List<String> subdomainVisits(String[] domains) {

      Map<String, Integer> map = new HashMap<>();

      for (String s : domains) {
        String[] elems = s.split("\\s+");
        Integer count = Integer.valueOf(elems[0]);
        String[] paths = elems[1].split("\\.");

        String key = null;
        for (int i = paths.length - 1; i >= 0; --i) {
          if (key != null) {
            key = paths[i] + "." + key;
          } else {
            key = paths[i];
          }
          map.put(key, map.getOrDefault(key, 0) + count);
        }
      }

      List<String> result = new ArrayList<>();
      for (Map.Entry<String, Integer> entry : map.entrySet()) {
        result.add(entry.getValue() + " " + entry.getKey());
      }
      return result;
    }
  }

  @AllArgsConstructor
  private static class Case {

    private String[] elems;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Case> c =
        aCase -> {
          log.info(Strings.format(aCase.elems));
          log.info(Strings.format(s.subdomainVisits(aCase.elems)));
        };

    c.accept(
        new Case(
            new String[] {
              "900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"
            }));
    c.accept(new Case(new String[] {"9001 discuss.lintcode.com"}));
  }
}
