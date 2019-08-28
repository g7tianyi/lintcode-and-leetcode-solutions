package com.g7tianyi.lintcode.string;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 28, 2019
 *
 * @link https://www.lintcode.com/problem/isomorphic-strings/description
 */
public class IsomorphicStrings {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public boolean isIsomorphic(String s, String t) {

      if (s.length() != t.length()) {
        return false;
      }

      int[] hash1 = new int[256], hash2 = new int[256];
      for (int i = 0; i < 256; ++i) {
        hash1[i] = Integer.MIN_VALUE;
        hash2[i] = Integer.MIN_VALUE;
      }

      // f o o, f=>1, o=>2
      // b a r, b=>f=>1, a=>o=>2, 因为a已经映射到了o, 所以后续当r再映射到o时，相当于没戏
      char ch;
      int len = s.length();
      for (int i = 0, h1 = 0, h2 = 0; i < len; ++i) {
        ch = s.charAt(i);
        if (hash1[ch] == Integer.MIN_VALUE) {
          hash1[ch] = h1++;
        }

        ch = t.charAt(i);
        if (hash2[ch] == Integer.MIN_VALUE) {
          hash2[ch] = h2++;
        }
      }

      for (int i = 0; i < len; ++i) {
        if (hash1[s.charAt(i)] != hash2[t.charAt(i)]) {
          return false;
        }
      }

      return true;
    }
  }

  @AllArgsConstructor
  private static class Input {

    private String a;
    private String b;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Input> c =
        input -> {
          log.info(input.a);
          log.info(input.b);
          log.info(s.isIsomorphic(input.a, input.b));
          log.info();
        };

    c.accept(new Input("egg", "add"));
    c.accept(new Input("foo", "bar"));
    c.accept(new Input("paper", "title"));
    c.accept(
        new Input(
            "a`%ii,VEZQc_BSU%ObO5<sX81B/bOw+CNUd#Uav*P!Ax!#>hh,k#b/|>4ixFQZl+l!?bJjakbQbGglEb<g>Hf81m@A9GIvbd]qh?y__t+E(Iyv7zUEfZF{81VaM-0u?]tG=_fFR/XJ=X{-,oRpxE9u*VNYlM",
            "b`%ii-WE[Qc_BSV%OcO5<sX82B/cOw+CNVd#Vbv*P!Bx!#?hh-k#c/|?4ixFQ[l+l!?cJkbkcQcGhlEc<h?Hf82m@B9GIvcd]rh?y__t+E(Iyv7{VEf[F{82WbN/0u?]tG=_fFR/XJ=X{/-oRpxE9u*WNYlN"));
  }
}
