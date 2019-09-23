package com.g7tianyi.lintcode.dp;

import com.g7tianyi.common.Numbers;
import com.g7tianyi.common.Strings;
import com.g7tianyi.util.Logger;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by g7tianyi on Sep 23, 2019
 *
 * @link https://www.lintcode.com/problem/bomb-enemy/description
 */
public class BombEnemy {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    // 深搜复杂度是O(n^3)，DP复杂度O(n^2)
    // 如果我是面试官，能写好深搜的代码我也绝对录取
    public int maxKilledEnemies(char[][] G) {
      if (G == null || G.length == 0 || G[0].length == 0) {
        return 0;
      }

      int H = G.length, W = G[0].length;

      // L(i,j) 表示第i行的封闭区间[0, j-1]内，可以在位置j炸死的敌人
      // R(i,j) 表示第i行的封闭区间[j+1, W-1]内，可以在位置j炸死的敌人
      // U(i,j) 表示第i列的封闭区间[0, j-1]内，可以在位置j炸死的敌人
      // D(i,j) 表示第i列的封闭区间[j+1, H-1]内，可以在位置j炸死的敌人
      int[][] L = new int[H][W]; // 左
      int[][] R = new int[H][W]; // 右
      int[][] U = new int[H][W]; // 上
      int[][] D = new int[H][W]; // 下

      for (int i = 0; i < H; ++i) { // 第i行
        for (int j = 0; j < W; ++j) { // 考虑位置j
          L[i][j] = 0;
          if (G[i][j] != 'W' && (j > 0 && G[i][j - 1] != 'W')) { // 前面不是墙
            L[i][j] = L[i][j - 1] + (G[i][j - 1] == 'E' ? 1 : 0);
          }
        }
        for (int j = W - 1; j >= 0; --j) { // 考虑位置j
          R[i][j] = 0;
          if (G[i][j] != 'W' && (j + 1 < W && G[i][j + 1] != 'W')) { // 后面不是墙
            R[i][j] = R[i][j + 1] + (G[i][j + 1] == 'E' ? 1 : 0);
          }
        }
      }

      for (int j = 0; j < W; ++j) { // 第j列
        for (int i = 0; i < H; ++i) {
          U[i][j] = 0;
          if (G[i][j] != 'W' && (i > 0 && G[i - 1][j] != 'W')) { // 上面不是墙
            U[i][j] = U[i - 1][j] + (G[i - 1][j] == 'E' ? 1 : 0);
          }
        }
        for (int i = H - 1; i >= 0; --i) {
          D[i][j] = 0;
          if (G[i][j] != 'W' && (i + 1 < H && G[i + 1][j] != 'W')) { //  下面不是墙
            D[i][j] = D[i + 1][j] + (G[i + 1][j] == 'E' ? 1 : 0);
          }
        }
      }

      // show(L);
      // show(R);
      // show(U);
      // show(D);

      int result = 0;
      for (int i = 0; i < H; ++i) {
        for (int j = 0; j < W; ++j) {
          if (G[i][j] == '0') {
            result = Math.max(result, L[i][j] + R[i][j] + U[i][j] + D[i][j]);
          }
        }
      }
      return result;
    }

    private void show(int[][] F) {
      for (int[] row : F) {
        StringBuilder sb = new StringBuilder();
        for (int val : row) {
          sb.append(val).append(' ');
        }
        log.info(sb.toString());
      }
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < F[0].length * 2 - 1; ++i) {
        sb.append('-');
      }
      log.info(sb.toString());
    }
  }

  private final Solution s = new Solution();

  private final Consumer<List<String>> c =
      ss -> {
        char[][] G = new char[ss.size()][];
        for (int i = 0; i < ss.size(); ++i) {
          G[i] = ss.get(i).toCharArray();
          StringBuilder sb = new StringBuilder();
          for (char ch : G[i]) {
            sb.append(ch).append(' ');
          }
          log.info(sb.toString());
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < G[0].length * 2 - 1; ++i) {
          sb.append('-');
        }
        log.info(sb.toString());

        log.info(s.maxKilledEnemies(G));
        log.info();
      };

  @Test
  public void test() {
    c.accept(Lists.newArrayList("0E00", "E0WE", "0E00"));
    c.accept(Lists.newArrayList("0E00", "EEWE", "0E00"));
    c.accept(Lists.newArrayList("W00000", "0WWWE0", "W0EE0E", "00E0WE"));
    c.accept(Lists.newArrayList("00000", "00000", "00000", "00000"));
  }

  @Test
  public void test1() {
    c.accept(Lists.newArrayList("0", "0", "E", "W", "E", "E"));
    c.accept(Lists.newArrayList("0", "0", "E", "W", "E", "E", "0"));
    c.accept(Lists.newArrayList("0EE0W0E00"));
    c.accept(Lists.newArrayList(""));
    c.accept(Lists.newArrayList("0"));
  }

  @Test
  public void test2() {
    char[] chars = {'E', '0', '0', '0', 'E', 'W'};
    Function<Integer, List<String>> f =
        (Integer len) -> {
          List<String> ss = new ArrayList<>();
          int size = Numbers.nextInt(len) + 1;
          for (int i = 0; i < size; ++i) {
            ss.add(Strings.from(chars, len));
          }
          return ss;
        };
    c.accept(f.apply(6));
  }
}
