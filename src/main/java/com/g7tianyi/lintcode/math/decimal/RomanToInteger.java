package com.g7tianyi.lintcode.math.decimal;

import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by g7tianyi on Nov 03, 2019
 *
 * @link https://www.lintcode.com/problem/roman-to-integer/description
 */
public class RomanToInteger {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    class Node {
      char ch;
      int num;
      List<Node> children;

      Node(char ch, int num) {
        this.ch = ch;
        this.num = num;
        this.children = new ArrayList<>();
      }

      public Node addChildren(Node node) {
        children.add(node);
        return this;
      }

      public String toString() {
        return String.format("(%c, %d)", ch, num);
      }
    }

    private final Node root = new Node('@', 0);

    public int romanToInt(String s) {

      init();

      char[] chars = s.toCharArray();

      int ret = 0;
      Node curr = root, next;
      for (char ch : chars) {
        next = walk(curr, ch);
        if (next == null) {
          ret += curr.num;
          next = walk(root, ch);
        }
        curr = next;
      }
      ret += curr.num;

      return ret;
    }

    private Node walk(Node curr, char ch) {
      for (Node node : curr.children) {
        if (node.ch == ch) {
          return node;
        }
      }
      return null;
    }

    private void init() {
      Node I = new Node('I', 1);
      Node II = new Node('I', 2);
      Node III = new Node('I', 3);
      Node IV = new Node('V', 4);
      Node IX = new Node('X', 9);
      I.addChildren(II).addChildren(IV).addChildren(IX);
      II.addChildren(III);
      root.addChildren(I);

      Node V = new Node('V', 5);
      Node VI = new Node('I', 6);
      Node VII = new Node('I', 7);
      Node VIII = new Node('I', 8);
      V.addChildren(VI);
      VI.addChildren(VII);
      VII.addChildren(VIII);
      root.addChildren(V);

      Node X = new Node('X', 10);
      Node XX = new Node('X', 20);
      Node XXX = new Node('X', 30);
      Node XL = new Node('L', 40);
      Node XC = new Node('C', 90);
      X.addChildren(XX).addChildren(XL).addChildren(XC);
      XX.addChildren(XXX);
      root.addChildren(X);

      Node L = new Node('L', 50);
      Node LX = new Node('X', 60);
      Node LXX = new Node('X', 70);
      Node LXXX = new Node('X', 80);
      L.addChildren(LX);
      LX.addChildren(LXX);
      LXX.addChildren(LXXX);
      root.addChildren(L);

      Node C = new Node('C', 100);
      Node CC = new Node('C', 200);
      Node CCC = new Node('C', 300);
      Node CD = new Node('D', 400);
      Node CM = new Node('M', 900);
      C.addChildren(CC).addChildren(CD).addChildren(CM);
      CC.addChildren(CCC);
      root.addChildren(C);

      Node D = new Node('D', 500);
      Node DC = new Node('C', 600);
      Node DCC = new Node('C', 700);
      Node DCCC = new Node('C', 800);
      D.addChildren(DC);
      DC.addChildren(DCC);
      DCC.addChildren(DCCC);
      root.addChildren(D);

      Node M = new Node('M', 1000);
      Node MM = new Node('M', 2000);
      Node MMM = new Node('M', 3000);
      M.addChildren(MM);
      MM.addChildren(MMM);
      root.addChildren(M);
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.romanToInt("IV"));
    log.info(s.romanToInt("XCIX"));
    log.info(s.romanToInt("CXXVIII")); // 128
    log.info(s.romanToInt("MMMCMXCIX")); // 3999
  }
}
