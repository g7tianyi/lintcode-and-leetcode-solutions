package com.g7tianyi.lintcode.dp;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.util.Logger;
import org.junit.Test;

/**
 * Created by g7tianyi on Sep 21, 2019
 *
 * @link https://www.lintcode.com/problem/copy-books/description
 */
public class CopyBooks {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    // DP问题：
    // F(i, j)表示i个人复印前j本书的最小耗时
    // F(i, j) = MAX(F(i-1, k), books(k+1) + books(k+2) + ... + books(j))
    // 数组的局部和可以优化一下，不要每次都循环，这样复杂度就到了O(K*N)了
    // 另外也可以使用轮转数组来节省空间
    // 是否还有优化？
    // 夜深了，睡觉😪...
    public int copyBooks(int[] books, int man) {
      if (books.length == 0) {
        return 0;
      }

      for (int i = 1; i < books.length; ++i) {
        books[i] += books[i - 1];
      }

      int[][] F = new int[2][books.length];
      System.arraycopy(books, 0, F[0], 0, books.length);

      man = Math.min(man, books.length);
      int prev = 0, curr = 1;
      for (int i = 1; i < man; ++i) { // i个人
        for (int j = i; j < books.length; ++j) { // 复印前j本书
          F[curr][j] = Integer.MAX_VALUE;
          for (int k = i - 1; k < j; ++k) {
            // 相当于
            // | 前面i-1个人复印 | 我复印 |
            // | i-1本书        | i, i+1, ..., j 这些书 |
            // | i  本书        | i+1, i+2, ..., j 这些书 |
            // | i+1  本书      | i+2, i+3, ..., j 这些书 |
            // ...
            F[curr][j] = Math.min(F[curr][j], Math.max(F[prev][k], books[j] - books[k]));
          }
        }
        prev ^= 1;
        curr ^= 1;
      }

      return F[prev][books.length - 1];
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    log.info(s.copyBooks(Arrays.from(1, 2), 5));
    log.info(s.copyBooks(Arrays.from(3, 2, 4), 2));
    log.info(s.copyBooks(Arrays.from(3, 2, 4), 3));
    log.info(s.copyBooks(Arrays.from(3, 2, 2, 4, 2, 2, 3, 1), 3));
  }
}
