package com.g7tianyi.lintcode.dp;

import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by g7tianyi on Sep 23, 2019
 *
 * @link https://www.lintcode.com/problem/boolean-parenthesization/description
 */
public class BooleanParenthesization {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int countParenth(char[] symb, char[] oper) {

      if (symb == null || symb.length == 0) {
        return 0;
      }

      int len = symb.length;
      // T(i,j)表示从i到j的字符可得到True的方案总数
      // F(i,j)表示从i到j的字符可得到False的方案总数
      int[][] T = new int[len][len], F = new int[len][len];
      for (int i = 0; i < len; ++i) {
        T[i][i] = symb[i] == 'T' ? 1 : 0;
        F[i][i] = symb[i] == 'F' ? 1 : 0;
      }

      for (int l = 2; l <= len; ++l) {
        for (int i = 0, j; i + l <= len; ++i) {
          j = i + l - 1;
          T[i][j] = F[i][j] = 0;
          for (int k = i; k < j; ++k) {
            T[i][j] +=
                // 主要不要写成下面的形式：
                // oper[k] == '|' ?
                //   T[i][k] * (T[k + 1][j] + F[k + 1][j]) + T[k + 1][j] * (F[i][k] + T[i][k])
                //   : 0
                // T[k + 1][j] * T[i][k] 重复计算了一次
                (oper[k] == '|' ? T[i][k] * (T[k + 1][j] + F[k + 1][j]) + T[k + 1][j] * F[i][k] : 0)
                    + (oper[k] == '^' ? T[i][k] * F[k + 1][j] + F[i][k] * T[k + 1][j] : 0)
                    + (oper[k] == '&' ? T[i][k] * T[k + 1][j] : 0);
            F[i][j] +=
                (oper[k] == '&' ? F[i][k] * (T[k + 1][j] + F[k + 1][j]) + F[k + 1][j] * T[i][k] : 0)
                    + (oper[k] == '^' ? T[i][k] * T[k + 1][j] + F[i][k] * F[k + 1][j] : 0)
                    + (oper[k] == '|' ? F[i][k] * F[k + 1][j] : 0);
          }
        }

        // show(T, F);
      }

      return T[0][len - 1];
    }

    private void show(int[][] T, int[][] F) {
      for (int i = 0; i < T.length; ++i) {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < T[i].length; ++j) {
          sb.append(T[i][j]).append(' ');
        }
        sb.append('|').append(' ');
        for (int j = 0; j < F[i].length; ++j) {
          sb.append(F[i][j]).append(' ');
        }
        log.info(sb.toString());
      }

      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < T[0].length * 2 + 1; ++i) {
        sb.append("**");
      }
      sb.deleteCharAt(0);
      log.info(sb.toString());
    }
  }

  // 一开始以为oper是可以随便插入到TF串里面的，插入后对表达式求值
  // 囧到姥姥家去了...
  // 不过我还是会错以了，看例子以为只能加一个括号，哎
  public class Solution_WA {

    public int countParenth(char[] symb, char[] oper) {

      if (symb == null || symb.length == 0) {
        return 0;
      }

      int len = symb.length;
      if (len == 1) {
        return symb[0] == 'T' ? 1 : 0;
      }

      int[] LT = new int[len];
      int[] LF = new int[len];
      int[] RT = new int[len];
      int[] RF = new int[len];

      LT[0] = symb[0] == 'T' ? 1 : 0;
      LF[0] = symb[0] == 'F' ? 1 : 0;
      RT[len - 1] = symb[len - 1] == 'T' ? 1 : 0;
      RF[len - 1] = symb[len - 1] == 'F' ? 1 : 0;

      for (int i = 1; i < len; ++i) {
        if (symb[i] == 'T') {
          // symb oper prev result
          // T    &    T    T
          // T    |    T    T
          // T    |    F    T
          // T    ^    F    T
          //
          // T    &    F    F
          // T    ^    T    F
          LT[i] =
              (oper[i - 1] == '&' ? LT[i - 1] : 0)
                  + (oper[i - 1] == '|' ? LT[i - 1] + LF[i - 1] : 0)
                  + (oper[i - 1] == '^' ? LF[i - 1] : 0);
          LF[i] = (oper[i - 1] == '&' ? LF[i - 1] : 0) + (oper[i - 1] == '^' ? LT[i - 1] : 0);
        } else {
          // symb oper prev result
          // F    |    T    T
          // F    ^    T    T
          //
          // F    &    T    F
          // F    &    F    F
          // F    |    F    F
          // F    ^    F    F
          LT[i] = (oper[i - 1] == '|' ? LT[i - 1] : 0) + (oper[i - 1] == '^' ? LT[i - 1] : 0);
          LF[i] =
              (oper[i - 1] == '&' ? LT[i - 1] + LF[i - 1] : 0)
                  + (oper[i - 1] == '|' ? LF[i - 1] : 0)
                  + (oper[i - 1] == '^' ? LF[i - 1] : 0);
        }
      }

      for (int i = len - 2; i >= 0; --i) {
        if (symb[i] == 'T') {
          RT[i] =
              (oper[i] == '&' ? RT[i + 1] : 0)
                  + (oper[i] == '|' ? RT[i + 1] + RF[i + 1] : 0)
                  + (oper[i] == '^' ? RF[i + 1] : 0);
          RF[i] = (oper[i] == '&' ? RF[i + 1] : 0) + (oper[i] == '^' ? RT[i + 1] : 0);
        } else {
          RT[i] = (oper[i] == '|' ? RT[i + 1] : 0) + (oper[i] == '^' ? RT[i + 1] : 0);
          RF[i] =
              (oper[i] == '&' ? RT[i + 1] + RF[i + 1] : 0)
                  + (oper[i] == '|' ? RF[i + 1] : 0)
                  + (oper[i] == '^' ? RF[i + 1] : 0);
        }
      }

      int result = 0;
      for (int i = 0; i < len - 1; ++i) {
        if (oper[i] == '&') {
          result += LT[i] * RT[i + 1];
        } else if (oper[i] == '|') {
          result += LT[i] * (RT[i + 1] + RF[i]) + LF[i] * RT[i + 1];
        } else {
          result += LT[i] * RF[i + 1] + LF[i] * RT[i + 1];
        }
      }

      return result;
    }
  }

  @AllArgsConstructor
  private class Case {
    private String TF;
    private String OP;
  }

  private final Solution s = new Solution();

  private final Consumer<Case> c =
      aCase -> {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < aCase.TF.length(); ++i) {
          sb.append(aCase.TF.charAt(i)).append(' ');
          if (i < aCase.OP.length()) {
            sb.append(aCase.OP.charAt(i)).append(' ');
          }
        }
        log.info("表达式: %s", sb.toString());
        log.info("结果: %d", s.countParenth(aCase.TF.toCharArray(), aCase.OP.toCharArray()));
        log.info();
      };

  @Test
  public void test() {
    c.accept(new Case("TFFF", "^|&"));
    c.accept(new Case("TFT", "^&"));
    c.accept(new Case("TFF", "^|"));
    c.accept(new Case("T", ""));
    c.accept(new Case("F", ""));
  }
}
