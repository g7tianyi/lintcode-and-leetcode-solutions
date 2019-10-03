package com.g7tianyi.lintcode.search;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by g7tianyi on Oct 03, 2019
 *
 * @link https://www.lintcode.com/problem/restore-ip-addresses/description
 */
public class RestoreIPAddresses {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public List<String> restoreIpAddresses(String s) {
      List<String> result = new ArrayList<>();
      if (s == null || s.length() < 4) {
        return result;
      }

      char[] raw = s.toCharArray();
      char[] ip = new char[raw.length + 3];
      search(s.toCharArray(), 0, ip, 0, 0, result);
      return result;
    }

    private void search(char[] raw, int i, char[] ip, int j, int depth, List<String> result) {
      if (depth == 3) {
        if (validate(raw, i, raw.length)) {
          System.arraycopy(raw, i, ip, j, raw.length - i);
          result.add(new String(ip));
        }
        return;
      }

      for (int l = 1; l <= 3 && i + l < raw.length; ++l) {
        if (validate(raw, i, i + l)) {
          System.arraycopy(raw, i, ip, j, l);
          ip[j + l] = '.';
          search(raw, i + l, ip, j + l + 1, depth + 1, result);
        }
      }
    }

    private boolean validate(char[] chars, int i, int j) {
      int L = j - i;
      if (L <= 0 || L > 3) {
        return false;
      }
      if (chars[i] == '0') {
        return L == 1;
      }

      int num = 0;
      for (int k = i; k < j; ++k) {
        num = num * 10 + (chars[k] - '0');
      }
      return num >= 0 && num < 256;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.restoreIpAddresses("25525511135"));
    log.info(s.restoreIpAddresses("1116512311"));
    log.info(s.restoreIpAddresses("101111020"));
    log.info(s.restoreIpAddresses(""));
    log.info(s.restoreIpAddresses("121"));
    log.info(s.restoreIpAddresses("1213"));
  }
}
