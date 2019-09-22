package com.g7tianyi.lintcode.stack;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 22, 2019
 *
 * @link https://www.lintcode.com/problem/expression-add-operators/description
 */
public class ExpressionAddOperators {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    private int value;
    private String num;
    private char[] paths;
    private int pathIndex;
    private List<String> results;

    public List<String> addOperators(String num, int value) {

      this.num = num;
      this.value = value;
      this.paths = new char[(num.length() << 1) + 1];
      this.pathIndex = 0;
      this.results = new ArrayList<>();

      return resolve();
    }

    private List<String> resolve() {
      if (num != null && num.length() > 0) {
        paths[pathIndex++] = num.charAt(0);
        resolve(1);
      }
      return results;
    }

    private void resolve(int index) {
      if (index == num.length()) {
        evaluate();
        return;
      }

      char ch = num.charAt(index);

      paths[pathIndex++] = '+';
      paths[pathIndex++] = ch;
      resolve(index + 1);
      pathIndex -= 2;

      paths[pathIndex++] = '-';
      paths[pathIndex++] = ch;
      resolve(index + 1);
      pathIndex -= 2;

      paths[pathIndex++] = '*';
      paths[pathIndex++] = ch;
      resolve(index + 1);
      pathIndex -= 2;

      paths[pathIndex++] = ch;
      resolve(index + 1);
      pathIndex -= 1;
    }

    private void evaluate() {

      paths[pathIndex++] = '+'; // 我就是很讨厌写if-else

      int result = 0;
      int prev = 0, curr, temp;
      int i = 0, j = 1;
      char ch, operator = '+';
      while (j < pathIndex) {
        ch = paths[j];
        if (ch >= '0' && ch <= '9') {
          ++j;
          continue;
        }

        if (paths[i] == '0' && j - i > 1) { // 123 + 045 的情况，045是不合法的
          --pathIndex;
          return;
        }

        curr = 0;
        for (int k = i; k < j; ++k) {
          curr = curr * 10 + (paths[k] - '0');
        }

        if (operator == '+') {
          result += curr;
          prev = curr;
        } else if (operator == '-') {
          result -= curr;
          prev = -curr;
        } else {
          temp = prev * curr;
          result -= prev;
          result += temp;
          prev = temp;
        }

        operator = ch;
        i = j + 1;
        j = i + 1;
      }

      --pathIndex;
      if (result == value) {
        results.add(new String(paths, 0, pathIndex));
      }
    }
  }

  // Memory Limit Exceeded
  // 93% 数据通过测试 总耗时 1410 ms
  public class Solution_MLE2 {

    private int value;
    private String num;
    private StringBuilder paths;
    private List<String> results;

    public List<String> addOperators(String num, int value) {

      this.num = num;
      this.value = value;
      this.paths = new StringBuilder();
      this.results = new ArrayList<>();

      return resolve();
    }

    private List<String> resolve() {
      if (num != null && num.length() > 0) {
        paths.append(num.charAt(0) - '0');
        resolve(1);
      }
      return results;
    }

    private void resolve(int index) {
      if (index == num.length()) {
        evaluateExpression();
      } else {
        loopForward(index);
      }
    }

    private void loopForward(int index) {

      int curr = num.charAt(index) - '0';

      paths.append('+').append(curr);
      resolve(index + 1);
      paths.delete(paths.length() - 2, paths.length());

      paths.append('-').append(curr);
      resolve(index + 1);
      paths.delete(paths.length() - 2, paths.length());

      paths.append('*').append(curr);
      resolve(index + 1);
      paths.delete(paths.length() - 2, paths.length());

      paths.append(curr);
      resolve(index + 1);
      paths.delete(paths.length() - 1, paths.length());
    }

    private void evaluateExpression() {

      String expression = paths.toString() + "+"; // 我就是很讨厌写if-else

      Stack<Integer> stack = new Stack<>();
      int i = 0, j = 1, curr, prev;
      char ch, operator = '+';
      while (j < expression.length()) {
        ch = expression.charAt(j);
        if (ch >= '0' && ch <= '9') {
          ++j;
          continue;
        }

        if (expression.charAt(i) == '0' && j - i > 1) { // 123 + 045 的情况，045是不合法的
          return;
        }

        curr = 0;
        for (int k = i; k < j; ++k) {
          curr = curr * 10 + (expression.charAt(k) - '0');
        }

        if (operator == '+') {
          stack.push(curr);
        } else if (operator == '-') {
          stack.push(-curr);
        } else {
          prev = stack.pop();
          stack.push(prev * curr);
        }

        operator = ch;
        i = j + 1;
        j = i + 1;
      }

      int result = 0;
      for (Integer v : stack) {
        result += v;
      }
      if (result == value) {
        results.add(paths.toString());
      }
    }
  }

  // Memory Limit Exceeded
  // 86% 数据通过测试总耗时 1449 ms
  public class Solution_MLE1 {

    private int value;
    private String num;
    private StringBuilder sb;
    private List<String> results;

    public List<String> addOperators(String num, int value) {

      this.num = num;
      this.value = value;
      this.sb = new StringBuilder();
      this.results = new ArrayList<>();

      return resolve();
    }

    private List<String> resolve() {
      if (num != null && num.length() > 0) {
        sb.append(num.charAt(0) - '0');
        resolve(1);
      }
      return results;
    }

    private void resolve(int index) {
      if (index == num.length()) {
        evaluateExpression();
      } else {
        loopForward(index);
      }
    }

    private void loopForward(int index) {

      int curr = num.charAt(index) - '0';

      sb.append('+').append(curr);
      resolve(index + 1);
      sb.delete(sb.length() - 2, sb.length());

      sb.append('-').append(curr);
      resolve(index + 1);
      sb.delete(sb.length() - 2, sb.length());

      sb.append('*').append(curr);
      resolve(index + 1);
      sb.delete(sb.length() - 2, sb.length());

      sb.append(curr);
      resolve(index + 1);
      sb.delete(sb.length() - 1, sb.length());
    }

    private void evaluateExpression() {

      List<Integer> nodes = parseExpression();
      if (nodes != null) {
        evaluateExpression(nodes);
      }
    }

    private List<Integer> parseExpression() {

      String expression = sb.toString() + "="; // 我就是很讨厌写if-else

      List<Integer> nodes = new ArrayList<>();
      int i = 0, j = 1;
      while (j < expression.length()) {
        char ch = expression.charAt(j);
        if (ch >= '0' && ch <= '9') {
          ++j;
          continue;
        }

        if (expression.charAt(i) == '0' && j - i > 1) { // 123 + 045 的情况，045是不合法的
          return null;
        }

        int v = 0;
        for (int k = i; k < j; ++k) {
          v = v * 10 + (expression.charAt(k) - '0');
        }
        nodes.add(v);
        if (ch == '+') {
          nodes.add(-1);
        } else if (ch == '-') {
          nodes.add(-2);
        } else {
          nodes.add(-3);
        }

        i = j + 1;
        j = i + 1;
      }

      return nodes;
    }

    private void evaluateExpression(List<Integer> nodes) {
      Stack<Integer> stack = new Stack<>();
      int op = -1; // -1: +; -2: -; -3: *
      for (Integer curr : nodes) {
        if (curr < 0) {
          op = curr;
          continue;
        }

        if (op == -1) { // +
          stack.push(curr);
        } else if (op == -2) { // -
          stack.push(-curr);
        } else {
          int prev = stack.pop();
          stack.push(prev * curr);
        }
      }

      int result = 0;
      for (Integer v : stack) {
        result += v;
      }
      if (result == value) {
        results.add(sb.toString());
      }
    }
  }

  @AllArgsConstructor
  private static class Case {
    private String num;
    private int val;
    private boolean print;
  }

  private final Solution s = new Solution();

  private final Consumer<Case> c =
      aCase -> {
        log.info("%s | %d", aCase.num, aCase.val);
        List<String> result = s.addOperators(aCase.num, aCase.val);
        if (aCase.print) {
          log.info(result);
        }
        log.info();
      };

  @Test
  public void test() {
    c.accept(new Case("123", 6, true)); // 最简单情况1
    //    c.accept(new Case("123", 0, true)); // 最简单情况1，减号
    c.accept(new Case("105", 5, true)); // 最简单情况2，不添加任何符号
    //    c.accept(new Case("1234", 24, true)); // 测试连乘： 1*2*3*4
    //    c.accept(new Case("923", 3, true)); // 测试乘法前面的减法： 9-2*3
    //    c.accept(new Case("101", 2, true)); // 测试1+01不会出现
  }

  @Test
  public void test0() {
    c.accept(new Case(null, 1, true));
    c.accept(new Case("1", 0, true));
    c.accept(new Case("1", 1, true));
    c.accept(new Case("", 1, true));
  }

  @Test
  public void test1() {
    c.accept(new Case("1234567890", 25, false));
    c.accept(new Case("1111111111", 34, false));
    c.accept(new Case("000000000000", 0, false));
  }
}
